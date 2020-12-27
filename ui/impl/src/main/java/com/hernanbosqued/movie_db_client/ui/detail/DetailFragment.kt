package com.hernanbosqued.movie_db_client.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.VideoResultModel
import com.hernanbosqued.movie_db_client.repo.Constants
import com.hernanbosqued.movie_db_client.ui.BaseFragment
import com.hernanbosqued.movie_db_client.ui.R
import com.hernanbosqued.movie_db_client.ui.Utils
import com.hernanbosqued.movie_db_client.ui.databinding.LayoutDetailBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import kotlinx.android.synthetic.main.layout_detail.*

class DetailFragment : BaseFragment<DetailFragment.Callbacks>() {

    private val viewModel: DetailViewModel by viewModels()

    private val binding: LayoutDetailBinding by lazy {
        LayoutDetailBinding.inflate(LayoutInflater.from(context), null, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val model = arguments?.getSerializable("model") as CarouselItemModel
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
        viewModel.model = model
    }

    private fun registerObservers() {
        viewModel.state().observe(viewLifecycleOwner) { state ->
            when (state) {
                is DetailState.Ranking -> setRanking(state.ranking)
                is DetailState.Poster -> setPoster(state.poster)
                is DetailState.Video -> setVideo(state.data)
                is DetailState.Message -> showMessage(state.message)
            }
        }
    }

    private fun setPoster(posterPath: String?) {
        posterPath?.let {
            val absolutePath = Constants.IMAGE_BASE_URL + posterPath
            Utils.setImage(binding.posterImage, null, null, absolutePath, roundedCorners = false)
        }
    }

    private fun setRanking(ranking: String) {
        binding.rankingText.text = Utils.getSpan(getString(R.string.ranking), ranking, ContextCompat.getColor(requireContext(), R.color.colorAccent))
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun setVideo(data: VideoResultModel) {
        if (data.site.equals("youtube", ignoreCase = true) && !data.key.isNullOrEmpty()) {
            lifecycle.addObserver(binding.youtube)
            binding.youtube.enableAutomaticInitialization = true
            binding.youtube.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(data.key!!, 0f)
                    youTubePlayer.play()
                }
            })
        }
    }

    interface Callbacks

    override val dummyCallback: Callbacks
        get() = object : Callbacks {
        }
}
package com.hernanbosqued.movie_db_client.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.VideoResultModel
import com.hernanbosqued.movie_db_client.repo.Constants
import com.hernanbosqued.movie_db_client.ui.BaseFragment
import com.hernanbosqued.movie_db_client.ui.R
import com.hernanbosqued.movie_db_client.ui.Utils
import com.hernanbosqued.movie_db_client.ui.carousel.CarouselViewModel
import com.hernanbosqued.movie_db_client.ui.databinding.FragmentDetailBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailFragment : BaseFragment<DetailFragment.Callbacks>() {

    private val viewModel: DetailViewModel by viewModels()

    private val compositeDisposable = CompositeDisposable()

    private val binding: FragmentDetailBinding by lazy {
        DataBindingUtil.setContentView(this.requireActivity(), getLayout())
    }

    override fun getLayout(): Int {
        return R.layout.fragment_detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model: CarouselItemModel = arguments?.getSerializable("model") as CarouselItemModel

       // viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        registerObservers()

        binding.viewModel = viewModel
        viewModel.start(model)
    }

    private fun registerObservers() {
        compositeDisposable.add(viewModel.state().subscribe(this::handleState))
    }

    private fun handleState(state: DetailState) {
        when (state) {
            is DetailState.Ranking -> setRanking(state.ranking)
            is DetailState.Poster -> setPoster(state.poster)
            is DetailState.Video -> setVideo(state.data)
            is DetailState.Message -> showMessage(state.message)
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

    override fun onDestroy() {
        if (compositeDisposable.isDisposed.not()) {
            compositeDisposable.dispose()
        }
        super.onDestroy()
    }

    interface Callbacks

    override val dummyCallback: Callbacks
        get() = object : Callbacks {
        }
}
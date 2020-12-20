package com.hernanbosqued.movie_db_client.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.VideoResultModel
import com.hernanbosqued.movie_db_client.repo.Constants
import com.hernanbosqued.movie_db_client.ui.BaseFragment
import com.hernanbosqued.movie_db_client.ui.R
import com.hernanbosqued.movie_db_client.ui.Utils
import com.hernanbosqued.movie_db_client.ui.databinding.FragmentDetailBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : BaseFragment<DetailFragment.Callbacks>(), DetailContract.View {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: DetailViewModel

    interface Callbacks

    override fun getLayout(): Int {
        return R.layout.fragment_detail
    }

    private val binding: FragmentDetailBinding by lazy {
        DataBindingUtil.setContentView(this.requireActivity(), getLayout())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model: CarouselItemModel = arguments?.getSerializable("model") as CarouselItemModel
        //  presenter.bindModel(model)

        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.viewModel = viewModel
        viewModel.start(model)
    }

    override fun onResume() {
        super.onResume()
        //      presenter.bindView(this)
    }

    override fun onPause() {
        super.onPause()
        //     presenter.unbindView()
    }

    override val dummyCallback: Callbacks
        get() = object : Callbacks {
        }

    override fun setTitle(name: String) {
        title_text.text = name
    }

    override fun setOverview(overview: String) {
        overview_text.text = overview
    }

    override fun setPoster(posterPath: String?) {
        posterPath?.let {
            val absolutePath = Constants.IMAGE_BASE_URL + posterPath
            Utils.setImage(poster_image, null, null, absolutePath, roundedCorners = false)
        }
    }

    override fun setRanking(ranking: String) {
        this.ranking_text.text = Utils.getSpan(getString(R.string.ranking), ranking, ContextCompat.getColor(requireContext(), R.color.colorAccent))
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun setVideo(data: VideoResultModel) {
        if (data.site.equals("youtube", ignoreCase = true) && !data.key.isNullOrEmpty()) {
            lifecycle.addObserver(youtube)
            youtube.enableAutomaticInitialization = true

            youtube.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(data.key!!, 0f)
                    youTubePlayer.play()
                }
            })
        }
    }

    override fun showEmpty() {
        alert.visibility = View.VISIBLE
    }

    override fun hideEmpty() {
        alert.visibility = View.INVISIBLE
    }

    override fun showVideo() {
        youtube.visibility = View.VISIBLE
    }
}
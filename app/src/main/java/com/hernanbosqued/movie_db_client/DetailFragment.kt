package com.hernanbosqued.movie_db_client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.domain.model.VideoModel
import com.hernanbosqued.repo.Constants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import kotlinx.android.synthetic.main.fragment_details.*
import java.util.*

class DetailFragment : BaseFragment<DetailFragment.Callbacks>(), DetailContract.View {
    interface Callbacks

    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = DetailPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareYoutube()

        if (savedInstanceState == null) {
            val model = activity?.intent?.getSerializableExtra("model") as ResultModel?
            model?.let { presenter.setModel(it) }
        }
    }

    private fun prepareYoutube() {
        lifecycle.addObserver(youtube);
        youtube.getPlayerUiController().showFullscreenButton(true)
        youtube.enableAutomaticInitialization = true
    }

    override fun onResume() {
        super.onResume()
        presenter.bindView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unbindView()
    }

    override val dummyCallbacks: Callbacks
        get() = object : Callbacks {
        }

    override fun setTitle(name: String) {
        title_textview.text = name
    }

    override fun setOverview(overview: String) {
        overview_textview.text = overview
    }

    override fun setPoster(posterPath: String) {
        val path = Constants.IMAGE_BASE_URL + posterPath
        Utils.setImage(poster_imageview, null, path, false)
    }

    override fun setRanking(ranking: String) {
        ranking_textview.text = ranking
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun setVideo(data: VideoModel) {
        val result = data.results?.first()

        result?.let {
            if( it.site.equals("youtube", ignoreCase = true) ){
                youtube.getPlayerUiController().setVideoTitle(it.name)
                youtube.getYouTubePlayerWhenReady(object: YouTubePlayerCallback{
                    override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(it.key,0f)
                        youTubePlayer.play()
                    }
                })
            }
        }
    }
}

package com.hernanbosqued.movie_db_client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hernanbosqued.domain.model.ResultModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_details.*

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
            val id = activity?.intent?.getIntExtra("id", -1)?:-1
            presenter.getDetails(id)
        }
    }

    private fun prepareYoutube() {
        lifecycle.addObserver(youtube);

        youtube.getPlayerUiController().showFullscreenButton(true)
        youtube.enableAutomaticInitialization = true

        youtube.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.cueVideo("OvzJ1cm4dpY", 0f)
                youTubePlayer.play()
            }
        })
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
}

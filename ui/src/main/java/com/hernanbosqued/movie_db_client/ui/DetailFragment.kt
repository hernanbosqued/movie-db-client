package com.hernanbosqued.movie_db_client.ui

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.hernanbosqued.movie_db_client.domain.model.ResultModel
import com.hernanbosqued.movie_db_client.domain.model.VideoModel
import com.hernanbosqued.movie_db_client.repo.Constants
import com.hernanbosqued.movie_db_client.repo.RepositoryImpl
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import kotlinx.android.synthetic.main.fragment_details.*

class DetailFragment : BaseFragment<DetailFragment.Callbacks>(), DetailContract.View {
    private lateinit var presenter: DetailPresenter

    interface Callbacks

    override fun getLayout(): Int {
        return R.layout.fragment_details
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = DetailPresenter(this, RepositoryImpl(context!!))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = arguments?.getSerializable("model") as ResultModel?
        model?.let { presenter.setModel(it) }

        youtube.visibility = View.INVISIBLE
        empty_view.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        presenter.bindView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unbindView()
    }
    private fun prepareYoutube() {
        lifecycle.addObserver(youtube)
        youtube.enableAutomaticInitialization = true
    }

    override val dummyCallbacks: Callbacks
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
            Utils.setImage(poster_image, null, null, absolutePath, showAnimation = false, roundedCorners = false)
        }
    }

    override fun setRanking(ranking: String) {

        if (ranking.isNotEmpty()) {
            val span = SpannableStringBuilder(getString(R.string.ranking))
            span.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(context!!, R.color.colorAccent)
                ), 0, span.length - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            span.setSpan(StyleSpan(Typeface.BOLD), 0, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            this.ranking_text.text = span.append(" $ranking")
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun setVideo(data: VideoModel) {
        val result = data.results?.first()

        result?.let {
            if (it.site.equals("youtube", ignoreCase = true)) {
                prepareYoutube()

                youtube.visibility = View.VISIBLE
                empty_view.visibility = View.INVISIBLE
                youtube.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                    override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(it.key, 0f)
                        youTubePlayer.play()
                    }
                })
            }
        }
    }
}

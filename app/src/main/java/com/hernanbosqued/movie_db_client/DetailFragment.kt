package com.hernanbosqued.movie_db_client

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
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.domain.model.VideoModel
import com.hernanbosqued.repo.Constants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
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

        val model = arguments?.getSerializable("model") as ResultModel?
        model?.let { presenter.setModel(it) }

        youtube.visibility = View.INVISIBLE
        empty_view.visibility = View.VISIBLE
    }

    private fun prepareYoutube() {
        lifecycle.addObserver(youtube);
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

    override fun onDestroy() {
        super.onDestroy()
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

    override fun setPoster(path: String) {
        val absolutePath = Constants.IMAGE_BASE_URL + path
        Utils.setImage(poster_imageview, null, absolutePath, showAnimation = false, roundedCorners = false)
    }

    override fun setRanking(ranking: String) {
        val span = SpannableStringBuilder(getString(R.string.ranking))
        span.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorAccent)), 0, span.length - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(StyleSpan(Typeface.BOLD), 0, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.ranking_textview.text = span.append(" $ranking")
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

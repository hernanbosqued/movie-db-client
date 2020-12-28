package com.hernanbosqued.movie_db_client.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.EpisodeModel
import com.hernanbosqued.movie_db_client.domain.SeasonModel
import com.hernanbosqued.movie_db_client.repo.Constants
import com.hernanbosqued.movie_db_client.ui.BaseFragment
import com.hernanbosqued.movie_db_client.ui.R
import com.hernanbosqued.movie_db_client.ui.Utils
import com.hernanbosqued.movie_db_client.ui.databinding.LayoutDetailBinding
import kotlinx.android.synthetic.main.layout_detail.*

class DetailFragment : BaseFragment<DetailFragment.Callbacks>() {

    private val viewModel: DetailViewModel by viewModels()

    private val binding: LayoutDetailBinding by lazy {
        LayoutDetailBinding.inflate(LayoutInflater.from(context), null, false)
    }

    private val adapter = EpisodesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val model = arguments?.getSerializable("model") as CarouselItemModel
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        registerObservers()
        viewModel.model = model
    }

    private fun prepareRecyclerView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        layoutManager.isItemPrefetchEnabled = true
        layoutManager.recycleChildrenOnDetach = true
        binding.episodes.layoutManager = layoutManager
        binding.episodes.adapter = adapter
    }

    private fun registerObservers() {
        viewModel.state().observe(viewLifecycleOwner) { state ->
            when (state) {
                is DetailState.Ranking -> setRanking(state.ranking)
                is DetailState.Poster -> setPoster(state.poster)
                is DetailState.Message -> showMessage(state.message)
                is DetailState.Seasons -> showSeasons(state.seasons)
                is DetailState.Episodes -> showEpisodes(state.episodes)
            }
        }
    }

    private fun showSeasons(seasons: List<SeasonModel>) {
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.seasonSelected(tab.tag as Int)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        seasons.forEach { season ->
            tabs.addTab(tabs.newTab().setText(season.name).setTag(season.seasonNumber))
        }

        tabs.getTabAt(0)?.select()
    }

    private fun showEpisodes(episodes: List<EpisodeModel>) {
        adapter.replace(episodes)
        binding.tabs.visibility = View.VISIBLE
        binding.episodes.visibility = View.VISIBLE
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

    interface Callbacks

    override val dummyCallback: Callbacks
        get() = object : Callbacks {
        }
}
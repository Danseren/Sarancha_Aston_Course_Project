package ru.aston.sarancha_aston_course_project.view.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import ru.aston.sarancha_aston_course_project.App.Companion.pageNumber
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.FragmentEpisodeInfoBinding
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitImpl
import ru.aston.sarancha_aston_course_project.utils.EPISODE_INFO
import ru.aston.sarancha_aston_course_project.view.base.BaseFragment
import kotlin.properties.Delegates

class EpisodeInfoFragment : BaseFragment<FragmentEpisodeInfoBinding>(), HasCustomTitle {

    companion object {
        fun newInstance(characterId: Int): EpisodeInfoFragment {
            val args = Bundle()
            args.putInt(EPISODE_INFO, characterId)
            val fragment = EpisodeInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var episodeId by Delegates.notNull<Int>()

    private var disposable: Disposable = Disposable.empty()
    private val controller = RepositoryRetrofitImpl()
//    val characterResult = controller.characterResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        episodeId = arguments?.getInt(EPISODE_INFO)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getResult(episodeId)
    }

    override fun getTitleRes(): Int = R.string.titleEpisodes

    override fun getViewBinding(): FragmentEpisodeInfoBinding {
        return FragmentEpisodeInfoBinding.inflate(layoutInflater)
    }

    private fun getResult(episodeId: Int) {

        disposable = controller
            .getEpisodeData(pageNumber)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    with(binding) {
                        tvEpisode.text = it.results[episodeId].episode
                        tvEpisodeName.text = it.results[episodeId].name
                        tvAirDate.text = it.results[episodeId].airDate
                        tvCreated.text = it.results[episodeId].created
                        tvUrl.text = it.results[episodeId].url
                    }
                },
                {

                }
            )
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
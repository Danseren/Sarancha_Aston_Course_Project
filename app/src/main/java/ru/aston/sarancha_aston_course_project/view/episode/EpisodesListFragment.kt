package ru.aston.sarancha_aston_course_project.view.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.aston.sarancha_aston_course_project.App
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.FragmentEpisodesListBinding
import ru.aston.sarancha_aston_course_project.model.dto.episode.EpisodeDto
import ru.aston.sarancha_aston_course_project.navigation.IRouter
import ru.aston.sarancha_aston_course_project.utils.EPISODE_INFO_FRAGMENT_TAG
import ru.aston.sarancha_aston_course_project.utils.PAGE_NUMBER_BUNDLE
import ru.aston.sarancha_aston_course_project.view.base.BaseFragment
import ru.aston.sarancha_aston_course_project.viewmodel.EpisodeListViewModel
import javax.inject.Inject


class EpisodesListFragment : BaseFragment<FragmentEpisodesListBinding>(), HasCustomTitle {

    companion object {
        fun newInstance(pageNumber: Int): EpisodesListFragment {
            val args = Bundle()
            args.putInt(PAGE_NUMBER_BUNDLE, pageNumber)
            val fragment = EpisodesListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var router: IRouter

    lateinit var viewModel: EpisodeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.app.appComponent.inject(this@EpisodesListFragment)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[EpisodeListViewModel::class.java]

        val bundle = Bundle()
        arguments?.getInt(PAGE_NUMBER_BUNDLE)?.let { viewModel.getResult(it) }

        viewModel.episodeResult.observe(viewLifecycleOwner) {
            it.let {
                val result: EpisodeDto = it
                val list = result.results
                binding.recyclerEpisodesContainer.adapter =
                    RecyclerEpisodeAdapter(list).apply {

                        clickAction = {
                            router.replaceFragmentWithBackstack(
                                requireActivity().supportFragmentManager,
                                R.id.container,
                                EpisodeInfoFragment.newInstance(itemPos),
                                EPISODE_INFO_FRAGMENT_TAG
                            )
                        }
                    }
            }
        }
    }

    override fun getTitleRes(): Int = R.string.titleEpisodes

    override fun getViewBinding(): FragmentEpisodesListBinding {
        return FragmentEpisodesListBinding.inflate(layoutInflater)
    }
}
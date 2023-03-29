package ru.aston.sarancha_aston_course_project.view.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.alert_dialog_episodes_filter.view.*
import ru.aston.sarancha_aston_course_project.App
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.FragmentEpisodesListBinding
import ru.aston.sarancha_aston_course_project.di.AppComponent
import ru.aston.sarancha_aston_course_project.domain.EpisodeFilterData
import ru.aston.sarancha_aston_course_project.model.dto.episode.EpisodeDto
import ru.aston.sarancha_aston_course_project.navigation.IRouter
import ru.aston.sarancha_aston_course_project.utils.*
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
    private lateinit var alertDialogView: View
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        AppComponent.init(App.app).inject(this)
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
                binding.progressCircular.makeGone()
            }
        }

        binding.checkboxFilter.isChecked = false
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireActivity())

        binding.checkboxFilter.setOnClickListener {
            alertDialogView =
                LayoutInflater
                    .from(requireActivity())
                    .inflate(R.layout.alert_dialog_episodes_filter, null)
            launchCustomAlertDialog()
        }
    }

    private fun launchCustomAlertDialog() {

        val episodeFilter = EpisodeFilterData()

        materialAlertDialogBuilder
            .setView(alertDialogView)
            .setTitle(EPISODE_FILTER_DIALOG_TITLE)
            .setMessage(FILTER_MESSAGE)
            .setPositiveButton(FILTER_POSITIVE_BUTTON) { dialog, _ ->
                with(alertDialogView) {
                    episodeFilter.apply {
                        enabled = true
                        name =
                            if (!etName.text.isNullOrBlank()) etName.text.toString() else ""
                        episode =
                            if (!etEpisode.text.isNullOrBlank()) etEpisode.text.toString() else ""
                    }
                }
                binding.checkboxFilter.isChecked = true
                viewModel.getFilterResult(episodeFilter)
                dialog.dismiss()
            }
            .setNegativeButton(FILTER_NEGATIVE_BUTTON) { dialog, _ ->
                binding.checkboxFilter.isChecked = false
                dialog.dismiss()
            }
            .show()
    }

    override fun getTitleRes(): Int = R.string.titleEpisodes

    override fun getViewBinding(): FragmentEpisodesListBinding {
        return FragmentEpisodesListBinding.inflate(layoutInflater)
    }
}
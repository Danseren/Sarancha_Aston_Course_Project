package ru.aston.sarancha_aston_course_project.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.FragmentEpisodesListBinding


class EpisodesListFragment : BaseFragment<FragmentEpisodesListBinding>(), HasCustomTitle {

    companion object {
        fun newInstance() = EpisodesListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun getTitleRes(): Int = R.string.titleEpisodes

    override fun getViewBinding(): FragmentEpisodesListBinding {
        return FragmentEpisodesListBinding.inflate(layoutInflater)
    }
}
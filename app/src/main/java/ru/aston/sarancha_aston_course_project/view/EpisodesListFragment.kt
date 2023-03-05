package ru.aston.sarancha_aston_course_project.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.FragmentEpisodesListBinding


class EpisodesListFragment : Fragment(), HasCustomTitle {

    private var _binding: FragmentEpisodesListBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = EpisodesListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun getTitleRes(): Int = R.string.titleEpisodes

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
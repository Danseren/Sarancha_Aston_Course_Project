package ru.aston.sarancha_aston_course_project.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.FragmentLocationsListBinding


class LocationsListFragment : Fragment(), HasCustomTitle {

    private var _binding: FragmentLocationsListBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = LocationsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun getTitleRes(): Int = R.string.titleLocations

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
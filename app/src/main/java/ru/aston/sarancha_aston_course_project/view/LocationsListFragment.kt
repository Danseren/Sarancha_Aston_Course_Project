package ru.aston.sarancha_aston_course_project.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.FragmentLocationsListBinding


class LocationsListFragment : BaseFragment<FragmentLocationsListBinding>(), HasCustomTitle {

    companion object {
        fun newInstance() = LocationsListFragment()
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

    override fun getTitleRes(): Int = R.string.titleLocations

    override fun getViewBinding(): FragmentLocationsListBinding {
        return FragmentLocationsListBinding.inflate(layoutInflater)
    }
}
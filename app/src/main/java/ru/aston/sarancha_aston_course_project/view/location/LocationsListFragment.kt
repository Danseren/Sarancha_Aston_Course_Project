package ru.aston.sarancha_aston_course_project.view.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.alert_dialog_episodes_filter.view.etName
import kotlinx.android.synthetic.main.alert_dialog_location_filter.view.*
import ru.aston.sarancha_aston_course_project.App
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.FragmentLocationsListBinding
import ru.aston.sarancha_aston_course_project.di.AppComponent
import ru.aston.sarancha_aston_course_project.domain.LocationFilterData
import ru.aston.sarancha_aston_course_project.model.dto.location.LocationDto
import ru.aston.sarancha_aston_course_project.navigation.IRouter
import ru.aston.sarancha_aston_course_project.utils.*
import ru.aston.sarancha_aston_course_project.view.base.BaseFragment
import ru.aston.sarancha_aston_course_project.viewmodel.LocationListViewModel
import javax.inject.Inject

class LocationsListFragment : BaseFragment<FragmentLocationsListBinding>(), HasCustomTitle {

    companion object {
        fun newInstance(pageNumber: Int): LocationsListFragment {
            val args = Bundle()
            args.putInt(PAGE_NUMBER_BUNDLE, pageNumber)
            val fragment = LocationsListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var router: IRouter

    lateinit var viewModel: LocationListViewModel
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

        viewModel = ViewModelProvider(this)[LocationListViewModel::class.java]

        val bundle = Bundle()
        arguments?.getInt(PAGE_NUMBER_BUNDLE)?.let { viewModel.getResult(it) }

        viewModel.locationResult.observe(viewLifecycleOwner) {
            it.let {
                val result: LocationDto = it
                val list = result.results
                binding.recyclerLocationsContainer.adapter =
                    RecyclerLocationAdapter(list).apply {

                        clickAction = {
                            router.replaceFragmentWithBackstack(
                                requireActivity().supportFragmentManager,
                                R.id.container,
                                LocationInfoFragment.newInstance(itemPos),
                                LOCATIONS_LIST_FRAGMENT_TAG
                            )
                        }
                    }
            }
        }

        binding.checkboxFilter.isChecked = false
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireActivity())

        binding.checkboxFilter.setOnClickListener {
            alertDialogView =
                LayoutInflater
                    .from(requireActivity())
                    .inflate(R.layout.alert_dialog_location_filter, null)
            launchCustomAlertDialog()
        }
    }

    private fun launchCustomAlertDialog() {

        val locationFilter = LocationFilterData()

        materialAlertDialogBuilder
            .setView(alertDialogView)
            .setTitle(LOCATION_FILTER_DIALOG_TITLE)
            .setMessage(FILTER_MESSAGE)
            .setPositiveButton(FILTER_POSITIVE_BUTTON) { dialog, _ ->
                with(alertDialogView) {
                    locationFilter.apply {
                        enabled = true
                        name =
                            if (!etName.text.isNullOrBlank()) etName.text.toString() else ""
                        type =
                            if (!etType.text.isNullOrBlank()) etType.text.toString() else ""
                        dimension =
                            if (!etDimension.text.isNullOrBlank()) etDimension.text.toString() else ""
                    }
                }
                binding.checkboxFilter.isChecked = true
                viewModel.getFilterResult(locationFilter)
                dialog.dismiss()
            }
            .setNegativeButton(FILTER_NEGATIVE_BUTTON) { dialog, _ ->
                binding.checkboxFilter.isChecked = false
                dialog.dismiss()
            }
            .show()
    }

    override fun getTitleRes(): Int = R.string.titleLocations

    override fun getViewBinding(): FragmentLocationsListBinding {
        return FragmentLocationsListBinding.inflate(layoutInflater)
    }
}
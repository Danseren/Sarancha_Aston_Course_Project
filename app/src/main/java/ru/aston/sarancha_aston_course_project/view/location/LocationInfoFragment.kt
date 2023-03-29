package ru.aston.sarancha_aston_course_project.view.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import ru.aston.sarancha_aston_course_project.App.Companion.pageNumber
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.FragmentLocationInfoBinding
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitLocationImpl
import ru.aston.sarancha_aston_course_project.utils.LOCATION_INFO
import ru.aston.sarancha_aston_course_project.view.base.BaseFragment
import kotlin.properties.Delegates

class LocationInfoFragment : BaseFragment<FragmentLocationInfoBinding>(), HasCustomTitle {

    companion object {
        fun newInstance(locationId: Int): LocationInfoFragment {
            val args = Bundle()
            args.putInt(LOCATION_INFO, locationId)
            val fragment = LocationInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var locationId by Delegates.notNull<Int>()

    private var disposable: Disposable = Disposable.empty()
    private val episodeController = RepositoryRetrofitLocationImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationId = arguments?.getInt(LOCATION_INFO)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getResult(locationId)
    }

    override fun getTitleRes(): Int = R.string.titleLocations

    override fun getViewBinding(): FragmentLocationInfoBinding {
        return FragmentLocationInfoBinding.inflate(layoutInflater)
    }

    private fun getResult(locationId: Int) {

        disposable = episodeController
            .getLocationData(pageNumber)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    with(binding) {
                        tvType.text = it.results[locationId].type
                        tvDimension.text = it.results[locationId].dimension
                        tvLocationName.text = it.results[locationId].name
                        tvCreated.text = it.results[locationId].created
                        tvUrl.text = it.results[locationId].url
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
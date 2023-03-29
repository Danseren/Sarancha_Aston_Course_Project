package ru.aston.sarancha_aston_course_project.view.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.alert_dialog_characters_filter.view.*
import ru.aston.sarancha_aston_course_project.App
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.FragmentCharactersListBinding
import ru.aston.sarancha_aston_course_project.di.AppComponent
import ru.aston.sarancha_aston_course_project.domain.CharacterFilterData
import ru.aston.sarancha_aston_course_project.model.dto.character.CharacterDto
import ru.aston.sarancha_aston_course_project.navigation.IRouter
import ru.aston.sarancha_aston_course_project.utils.*
import ru.aston.sarancha_aston_course_project.view.base.BaseFragment
import ru.aston.sarancha_aston_course_project.viewmodel.CharacterListViewModel
import javax.inject.Inject

class CharacterListFragment : BaseFragment<FragmentCharactersListBinding>(), HasCustomTitle {

    companion object {
        fun newInstance(pageNumber: Int): CharacterListFragment {
            val args = Bundle()
            args.putInt(PAGE_NUMBER_BUNDLE, pageNumber)
            val fragment = CharacterListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var router: IRouter

    lateinit var viewModel: CharacterListViewModel
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

        viewModel = ViewModelProvider(this)[CharacterListViewModel::class.java]

        val bundle = Bundle()
        arguments?.getInt(PAGE_NUMBER_BUNDLE)?.let { viewModel.getResult(it) }

        viewModel.characterResult.observe(viewLifecycleOwner) {
            it.let {
                val result: CharacterDto = it
                val list = result.results
                binding.recyclerCharactersContainer.adapter =
                    RecyclerCharactersAdapter(list).apply {

                        clickAction = {
                            router.replaceFragmentWithBackstack(
                                requireActivity().supportFragmentManager,
                                R.id.container,
                                CharacterInfoFragment.newInstance(itemPos),
                                CHARACTER_INFO_FRAGMENT_TAG
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
                    .inflate(R.layout.alert_dialog_characters_filter, null)
            launchCustomAlertDialog()
        }
    }

    private fun launchCustomAlertDialog() {

        val characterFilter = CharacterFilterData()

        materialAlertDialogBuilder
            .setView(alertDialogView)
            .setTitle(CHARACTER_FILTER_DIALOG_TITLE)
            .setMessage(FILTER_MESSAGE)
            .setPositiveButton(FILTER_POSITIVE_BUTTON) { dialog, _ ->
                with(alertDialogView) {
                    characterFilter.apply {
                        enabled = true
                        name =
                            if (!etName.text.isNullOrBlank()) etName.text.toString() else ""
                        species =
                            if (!etSpecies.text.isNullOrBlank()) etSpecies.text.toString() else ""
                        type =
                            if (!etType.text.isNullOrBlank()) etType.text.toString() else ""
                        status = spinnerStatus.selectedItem.toString()
                        gender = spinnerGender.selectedItem.toString()
                    }
                }
                binding.checkboxFilter.isChecked = true
                viewModel.getFilterResult(characterFilter)
                dialog.dismiss()
            }
            .setNegativeButton(FILTER_NEGATIVE_BUTTON) { dialog, _ ->
                binding.checkboxFilter.isChecked = false
                dialog.dismiss()
            }
            .show()

    }

    override fun getTitleRes(): Int = R.string.titleCharacters

    override fun getViewBinding(): FragmentCharactersListBinding {
        return FragmentCharactersListBinding.inflate(layoutInflater)
    }
}
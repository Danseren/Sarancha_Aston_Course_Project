package ru.aston.sarancha_aston_course_project.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.FragmentCharactersListBinding
import ru.aston.sarancha_aston_course_project.model.dto.CharacterDto
import ru.aston.sarancha_aston_course_project.utils.PAGE_NUMBER_BUNDLE
import ru.aston.sarancha_aston_course_project.view.base.BaseFragment
import ru.aston.sarancha_aston_course_project.viewmodel.CharacterListViewModel

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

    lateinit var viewModel: CharacterListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
                    RecyclerCharactersAdapter(list)
            }
        }
    }

    override fun getTitleRes(): Int = R.string.titleCharacters

    override fun getViewBinding(): FragmentCharactersListBinding {
        return FragmentCharactersListBinding.inflate(layoutInflater)
    }
}
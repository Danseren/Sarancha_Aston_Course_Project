package ru.aston.sarancha_aston_course_project.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.FragmentCharactersListBinding
import ru.aston.sarancha_aston_course_project.viewmodel.AppState
import ru.aston.sarancha_aston_course_project.viewmodel.CharacterListViewModel

class CharacterListFragment : Fragment(), HasCustomTitle {

    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = CharacterListFragment()
    }

    lateinit var viewModel: CharacterListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CharacterListViewModel::class.java)
        viewModel.getLiveData()
            .observe(viewLifecycleOwner, object : Observer<AppState> {
                override fun onChanged(t: AppState) {
                    renderData(t)
                }
            })
        viewModel.sentRequest()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> {

            }
            is AppState.Error -> {

            }
            is AppState.Success -> {
                binding.recyclerCharactersContainer.adapter =
                    RecyclerCharactersAdapter(appState.character)
            }
        }
    }

    override fun getTitleRes(): Int = R.string.titleCharacters

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
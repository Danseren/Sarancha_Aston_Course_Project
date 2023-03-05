package ru.aston.sarancha_aston_course_project.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.sarancha_aston_course_project.databinding.FragmentCharactersListBinding
import ru.aston.sarancha_aston_course_project.model.dto.Result

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = CharacterListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characters = arrayListOf(
            Result(),
            Result(),
            Result(),
            Result(),
            Result(),
            Result(),
            Result(),
        )

        val adapter = RecyclerCharactersAdapter(characters)
        binding.recyclerCharactersContainer.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
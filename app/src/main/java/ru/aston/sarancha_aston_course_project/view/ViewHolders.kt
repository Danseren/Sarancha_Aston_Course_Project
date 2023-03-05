package ru.aston.sarancha_aston_course_project.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.aston.sarancha_aston_course_project.databinding.RecyclerCharactersBinding
import ru.aston.sarancha_aston_course_project.model.dto.Result

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(myViewDto: Result)
}

class CharactersViewHolder(
    val binding: RecyclerCharactersBinding
) : BaseViewHolder(binding.root) {
    override fun bind(myViewDto: Result) {}
}
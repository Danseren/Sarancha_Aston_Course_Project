package ru.aston.sarancha_aston_course_project.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.aston.sarancha_aston_course_project.databinding.RecyclerCharactersBinding
import ru.aston.sarancha_aston_course_project.databinding.RecyclerEpisodesBinding
import ru.aston.sarancha_aston_course_project.model.dto.character.CharacterInfo
import ru.aston.sarancha_aston_course_project.model.dto.episode.EpisodeInfo
import ru.aston.sarancha_aston_course_project.model.dto.location.LocationInfo

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(myViewDto: CharacterInfo)
}

class CharactersViewHolder(
    val binding: RecyclerCharactersBinding
) : BaseViewHolder(binding.root) {
    override fun bind(myViewDto: CharacterInfo) {}
}

class EpisodesViewHolder(
    val binding: RecyclerEpisodesBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(myViewDto: EpisodeInfo) {}
}

class LocationViewHolder(
    val binding: RecyclerEpisodesBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(myViewDto: LocationInfo) {}
}
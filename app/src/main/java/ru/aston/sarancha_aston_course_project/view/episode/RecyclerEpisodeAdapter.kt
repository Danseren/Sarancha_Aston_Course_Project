package ru.aston.sarancha_aston_course_project.view.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.aston.sarancha_aston_course_project.databinding.RecyclerEpisodesBinding
import ru.aston.sarancha_aston_course_project.model.dto.episode.EpisodeInfo
import ru.aston.sarancha_aston_course_project.view.EpisodesViewHolder

class RecyclerEpisodeAdapter(private val listData: List<EpisodeInfo>) :
    RecyclerView.Adapter<EpisodesViewHolder>() {

    var clickAction: ((EpisodeInfo) -> Unit)? = null
    var itemPos: Int = 1
    val list = listData

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodesViewHolder {

        val binding = RecyclerEpisodesBinding.inflate(LayoutInflater.from(parent.context))
        return EpisodesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(listData[position])
        when (holder) {
            is EpisodesViewHolder -> {
                with(holder.binding) {
                    custom.apply {
                        setEpisodeName(listData[position].name)
                        setEpisodeAirDate(listData[position].airDate)
                        setEpisodeNumber(listData[position].episode)

                        setOnClickListener {
                            itemPos = position
                            clickAction?.invoke(listData[position])
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = listData.size
}
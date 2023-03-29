package ru.aston.sarancha_aston_course_project.view.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.aston.sarancha_aston_course_project.databinding.RecyclerEpisodesBinding
import ru.aston.sarancha_aston_course_project.model.dto.location.LocationInfo
import ru.aston.sarancha_aston_course_project.view.LocationViewHolder

class RecyclerLocationAdapter(private val listData: List<LocationInfo>) :
    RecyclerView.Adapter<LocationViewHolder>() {

    var clickAction: ((LocationInfo) -> Unit)? = null
    var itemPos: Int = 1
    val list = listData

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationViewHolder {

        val binding = RecyclerEpisodesBinding.inflate(LayoutInflater.from(parent.context))
        return LocationViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(listData[position])
        when (holder) {
            is LocationViewHolder -> {
                with(holder.binding) {
                    custom.apply {
                        setEpisodeName(listData[position].name)
                        setEpisodeAirDate(listData[position].type)
                        setEpisodeNumber(listData[position].dimension)

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
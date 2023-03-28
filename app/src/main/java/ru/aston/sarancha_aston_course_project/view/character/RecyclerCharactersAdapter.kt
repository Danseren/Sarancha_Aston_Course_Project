package ru.aston.sarancha_aston_course_project.view.character

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.aston.sarancha_aston_course_project.databinding.RecyclerCharactersBinding
import ru.aston.sarancha_aston_course_project.model.dto.character.CharacterInfo
import ru.aston.sarancha_aston_course_project.utils.STATUS_ALIVE
import ru.aston.sarancha_aston_course_project.utils.STATUS_DEAD
import ru.aston.sarancha_aston_course_project.view.BaseViewHolder
import ru.aston.sarancha_aston_course_project.view.CharactersViewHolder

class RecyclerCharactersAdapter(private val listData: List<CharacterInfo>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    var clickAction: ((CharacterInfo) -> Unit)? = null
    var itemPos: Int = 1
    val list = listData

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {

        val binding = RecyclerCharactersBinding.inflate(LayoutInflater.from(parent.context))
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(listData[position])
        when (holder) {
            is CharactersViewHolder -> {
                with(holder.binding) {
                    custom.apply {
                        setCharacterName(listData[position].name)
                        setCharacterStatus(listData[position].status)
                        setImageSrc(listData[position].image)
                        setCharacterSpecies(listData[position].species)
                        setCharacterGender(listData[position].gender)
                        when (listData[position].status) {
                            STATUS_ALIVE -> setCharacterStatusColor(Color.GREEN)
                            STATUS_DEAD -> setCharacterStatusColor(Color.RED)
                            else -> setCharacterStatusColor(Color.GRAY)
                        }

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
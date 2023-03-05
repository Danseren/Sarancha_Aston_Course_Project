package ru.aston.sarancha_aston_course_project.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.aston.sarancha_aston_course_project.databinding.RecyclerCharactersBinding
import ru.aston.sarancha_aston_course_project.model.dto.Result

class RecyclerCharactersAdapter(private val listData: List<Result>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    var clickAction: ((Result) -> Unit)? = null
    var itemPos: Int = 1

//    override fun getItemViewType(position: Int): Int {
//        return listData[position].type
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {

        val binding = RecyclerCharactersBinding.inflate(LayoutInflater.from(parent.context))
        return CharactersViewHolder(binding)
//        return when (viewType) {
//            MOTHERLAND -> {
//                val binding =
//                    RecyclerAbroadCountryBinding.inflate(LayoutInflater.from(parent.context))
//                AbroadCountryViewHolder(binding)
//            }
//            else -> {
//                val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
//                CharactersViewHolder(binding)
//            }
//        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(listData[position])
        when (holder) {
            is CharactersViewHolder -> {
                with(holder.binding) {
                    custom.apply {
                        setCharacterName(listData[position].name)
                        setCharacterStatus(listData[position].status)
//                        setImageSrc(listData[position].image)
                        setCharacterSpecies(listData[position].species)
                        setCharacterGender(listData[position].gender)
                        when (listData[position].status) {
                            "Alive" -> setCharacterStatusColor(Color.GREEN)
                            "Dead" -> setCharacterStatusColor(Color.RED)
                            else -> setCharacterStatusColor(Color.GRAY)
                        }

//                        setOnClickListener {
//                            itemPos = position
//                            clickAction?.invoke(listData[position])
//                        }
                    }

                }
            }
        }
    }

    override fun getItemCount(): Int = listData.size
}
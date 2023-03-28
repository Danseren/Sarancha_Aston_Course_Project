package ru.aston.sarancha_aston_course_project.view.character

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.databinding.CharacterInfoBinding
import ru.aston.sarancha_aston_course_project.utils.loadImageFromUrl

class CustomCharacterView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: CharacterInfoBinding

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.character_info, this, true)
        binding = CharacterInfoBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomCharacterView, defStyleAttr, defStyleRes)

        val imageSrc = typedArray.getString(R.styleable.CustomCharacterView_imageSrc)
        if (imageSrc != null) {
            setImageSrc(imageSrc)
        }

        val characterName = typedArray.getString(R.styleable.CustomCharacterView_characterName)
        setCharacterName(characterName)

        val characterStatus = typedArray.getString(R.styleable.CustomCharacterView_characterStatus)
        setCharacterStatus(characterStatus)

        val characterStatusColor = typedArray.getColor(R.styleable.CustomCharacterView_characterStatusColor, Color.GRAY)
        setCharacterStatusColor(characterStatusColor)

        val characterSpecies = typedArray.getString(R.styleable.CustomCharacterView_characterSpecies)
        setCharacterSpecies(characterSpecies)

        val characterGender = typedArray.getString(R.styleable.CustomCharacterView_characterGender)
        setCharacterGender(characterGender)

        typedArray.recycle()
    }

    fun setCharacterName(headerText: String?) {
        binding.tvCharacterName.text = headerText ?: context.getString(R.string.tvCharacterName)
    }

    fun setCharacterStatus(bodyText: String?) {
        binding.tvCharacterStatus.text = bodyText ?: context.getString(R.string.tvCharacterStatus)
    }

    fun setImageSrc(imageSrc: String) {
        binding.ivAvatar.loadImageFromUrl(imageSrc)
    }

    fun setCharacterStatusColor(color: Int) {
        binding.tvCharacterStatus.setTextColor(color)
    }

    fun setCharacterSpecies(headerText: String?) {
        binding.tvSpecies.text = headerText ?: context.getString(R.string.tvSpecies)
    }

    fun setCharacterGender(bodyText: String?) {
        binding.tvGender.text = bodyText ?: context.getString(R.string.tvGender)
    }

}
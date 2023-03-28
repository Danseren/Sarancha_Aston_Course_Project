package ru.aston.sarancha_aston_course_project.view.episode

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.databinding.EpisodeInfoBinding

class CustomEpisodeView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: EpisodeInfoBinding

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
        inflater.inflate(R.layout.episode_info, this, true)
        binding = EpisodeInfoBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray =
            context.obtainStyledAttributes(
                attrs,
                R.styleable.CustomEpisodeView,
                defStyleAttr,
                defStyleRes
            )

        val episodeName = typedArray.getString(R.styleable.CustomEpisodeView_episodeName)
        setEpisodeName(episodeName)

        val episodeAirDate = typedArray.getString(R.styleable.CustomEpisodeView_episodeAirDate)
        setEpisodeAirDate(episodeAirDate)

        val episodeNumber = typedArray.getString(R.styleable.CustomEpisodeView_episodeNumber)
        setEpisodeNumber(episodeNumber)

        typedArray.recycle()
    }

    fun setEpisodeName(headerText: String?) {
        binding.tvEpisodeName.text = headerText ?: context.getString(R.string.tvCharacterName)
    }

    fun setEpisodeAirDate(bodyText: String?) {
        binding.tvAirDate.text = bodyText ?: context.getString(R.string.tvCharacterStatus)
    }

    fun setEpisodeNumber(headerText: String?) {
        binding.tvEpisode.text = headerText ?: context.getString(R.string.tvSpecies)
    }
}
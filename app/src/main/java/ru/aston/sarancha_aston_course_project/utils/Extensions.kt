package ru.aston.sarancha_aston_course_project.utils

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.Navigator

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_anonymous_128)
        .into(this)
}
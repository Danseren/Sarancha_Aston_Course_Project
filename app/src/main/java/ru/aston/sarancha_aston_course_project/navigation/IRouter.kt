package ru.aston.sarancha_aston_course_project.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface IRouter {

    fun addFragment(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        tag: String
    )

    fun replaceFragment(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        tag: String
    )

    fun replaceFragmentWithBackstack(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        tag: String
    )

    fun replaceFragmentWithBundle(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        tag: String,
        bundle: Bundle
    )

    fun deleteFragment(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        tag: String
    )
}
package ru.aston.sarancha_aston_course_project.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class Router : IRouter {

    override fun addFragment(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        tag: String
    ) {
        val previousFragment = fragmentManager.findFragmentByTag(tag)

        if (previousFragment == null) {
            fragmentManager
                .beginTransaction()
                .add(containerId, fragment, tag)
                .commit()
        } else {
            fragmentManager
                .beginTransaction()
                .replace(containerId, previousFragment, tag)
                .commit()
        }
    }

    override fun replaceFragment(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        tag: String
    ) {
        val previousFragment = fragmentManager.findFragmentByTag(tag)

        if (previousFragment == null) {
            fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, tag)
                .commit()
        } else {
            fragmentManager
                .beginTransaction()
                .replace(containerId, previousFragment, tag)
                .commit()
        }
    }

    override fun replaceFragmentWithBackstack(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        tag: String
    ) {

        fragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(containerId, fragment, tag)
            .commit()
    }

    override fun replaceFragmentWithBundle(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        tag: String,
        bundle: Bundle
    ) {

        fragmentManager
            .beginTransaction()
            .replace(containerId, fragment::class.java, bundle, tag)
            .commit()
    }

    override fun deleteFragment(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        tag: String
    ) {
        fragmentManager.findFragmentByTag(tag)?.let {
            fragmentManager.beginTransaction()
                .remove(it)
        }

    }
}
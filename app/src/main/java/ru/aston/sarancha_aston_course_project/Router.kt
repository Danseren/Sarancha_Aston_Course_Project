package ru.aston.sarancha_aston_course_project

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

    override fun replaceFragmentWithBundle(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        tag: String,
        bundle: Bundle
    ) {
        val previousFragment = fragmentManager.findFragmentByTag(tag)

        try {
            if (previousFragment == null) {
                fragmentManager
                    .beginTransaction()
                    .add(containerId, fragment::class.java, bundle, tag)
                    .commit()
            } else {
                fragmentManager
                    .beginTransaction()
                    .replace(containerId, previousFragment::class.java, bundle, tag)
                    .commit()
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }

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
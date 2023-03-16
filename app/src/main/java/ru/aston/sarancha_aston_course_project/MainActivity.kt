package ru.aston.sarancha_aston_course_project

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.ActivityMainBinding
import ru.aston.sarancha_aston_course_project.utils.*
import ru.aston.sarancha_aston_course_project.view.CharacterListFragment
import ru.aston.sarancha_aston_course_project.view.EpisodesListFragment
import ru.aston.sarancha_aston_course_project.view.LocationsListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val router = Router()

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.container)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        initViews()

        if (savedInstanceState == null) {
            router.addFragment(
                supportFragmentManager,
                binding.container.id,
                CharacterListFragment.newInstance(),
                CHARACTER_LIST_FRAGMENT_TAG
            )
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    private fun initViews() {
        with(binding) {

            bottomNavigation.apply {
                setOnItemSelectedListener { item ->
                    when (item.itemId) {

                        R.id.itemCharacters -> {
                            router.replaceFragment(
                                supportFragmentManager,
                                container.id,
                                CharacterListFragment.newInstance(),
                                CHARACTER_LIST_FRAGMENT_TAG
                            )
                            true
                        }

                        R.id.itemLocations -> {
                            router.replaceFragment(
                                supportFragmentManager,
                                container.id,
                                LocationsListFragment.newInstance(),
                                LOCATIONS_LIST_FRAGMENT_TAG
                            )
                            true
                        }

                        R.id.itemEpisodes -> {
                            router.replaceFragment(
                                supportFragmentManager,
                                container.id,
                                EpisodesListFragment.newInstance(),
                                EPISODES_LIST_FRAGMENT_TAG
                            )
                            true
                        }

                        else -> {
                            true
                        }
                    }
                }
                selectedItemId = R.id.itemCharacters
            }
        }
    }

    private fun updateUI() {
        val fragment = currentFragment

        if (fragment is HasCustomTitle) {
            binding.toolbar.title = getString(fragment.getTitleRes())
        } else {
            binding.toolbar.title = getString(R.string.titleCharacters)
        }

        if (supportFragmentManager.backStackEntryCount > 1) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            binding.bottomNavigation.makeGone()
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
            if (binding.bottomNavigation != null) binding.bottomNavigation.makeVisible()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }
}
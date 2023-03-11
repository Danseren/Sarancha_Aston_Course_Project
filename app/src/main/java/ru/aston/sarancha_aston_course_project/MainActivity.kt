package ru.aston.sarancha_aston_course_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.contract.Navigator
import ru.aston.sarancha_aston_course_project.databinding.ActivityMainBinding
import ru.aston.sarancha_aston_course_project.utils.makeGone
import ru.aston.sarancha_aston_course_project.utils.makeVisible
import ru.aston.sarancha_aston_course_project.view.CharacterListFragment
import ru.aston.sarancha_aston_course_project.view.EpisodesListFragment
import ru.aston.sarancha_aston_course_project.view.LocationsListFragment

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

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
            launchFragment(CharacterListFragment.newInstance(), R.id.container)
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    private fun initViews() {
        with(binding) {

            bottomNavigation.apply {
                setOnItemSelectedListener { item ->
                    when (item.itemId) {

                        R.id.itemCharacters -> {
                            launchFragment(
                                CharacterListFragment.newInstance(),
                                R.id.container
                            )
                            true
                        }

                        R.id.itemLocations -> {
                            launchFragment(
                                LocationsListFragment.newInstance(),
                                R.id.container
                            )
                            true
                        }

                        R.id.itemEpisodes -> {
                            launchFragment(
                                EpisodesListFragment.newInstance(),
                                R.id.container
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

    override fun showCharactersScreen() {
        launchFragmentWithAddToBackStack(
            fragment = CharacterListFragment.newInstance(),
            R.id.container
        )
    }

    override fun showLocationsScreen() {

    }

    override fun showEpisodesScreen() {

    }

    override fun showCharacterInfoScreen() {

    }

    override fun showEpisodeInfoScreen() {

    }

    override fun showLocationInfoScreen() {

    }

    private fun launchFragment(fragment: Fragment, container: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(container, fragment)
            .commit()
    }

    private fun launchFragmentWithAddToBackStack(fragment: Fragment, container: Int) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(container, fragment)
            .commit()
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
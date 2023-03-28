package ru.aston.sarancha_aston_course_project

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.aston.sarancha_aston_course_project.App.Companion.pageNumber
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.ActivityMainBinding
import ru.aston.sarancha_aston_course_project.di.AppComponent
import ru.aston.sarancha_aston_course_project.navigation.IRouter
import ru.aston.sarancha_aston_course_project.utils.*
import ru.aston.sarancha_aston_course_project.view.base.BaseActivity
import ru.aston.sarancha_aston_course_project.view.character.CharacterListFragment
import ru.aston.sarancha_aston_course_project.view.episode.EpisodesListFragment
import ru.aston.sarancha_aston_course_project.view.location.LocationsListFragment
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var router: IRouter

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

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppComponent.init(App.app).inject(this)

        setSupportActionBar(binding.toolbar)

        initViews()

        if (savedInstanceState == null) {
            startFragment(
                CharacterListFragment.newInstance(START_PAGE),
                CHARACTER_LIST_FRAGMENT_TAG
            )
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    private fun initViews() {
        with(binding) {

            btnNext.text = (START_PAGE + 1).toString()
            btnPrevious.text = LAST_PAGE.toString()

            btnNext.setOnClickListener {
                onNextPage()
            }

            btnPrevious.setOnClickListener {
                onPreviousPage()
            }

            bottomNavigation.apply {
                setOnItemSelectedListener { item ->
                    when (item.itemId) {

                        R.id.itemCharacters -> {
                            startFragment(
                                CharacterListFragment.newInstance(pageNumber),
                                CHARACTER_LIST_FRAGMENT_TAG
                            )
                            true
                        }

                        R.id.itemLocations -> {
                            startFragment(
                                LocationsListFragment.newInstance(),
                                LOCATIONS_LIST_FRAGMENT_TAG
                            )
                            true
                        }

                        R.id.itemEpisodes -> {
                            startFragment(
                                EpisodesListFragment.newInstance(pageNumber),
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

        with(binding) {
            if (fragment is HasCustomTitle) {
                toolbar.title = getString(fragment.getTitleRes())
            } else {
                toolbar.title = getString(R.string.titleCharacters)
            }

            if (supportFragmentManager.backStackEntryCount > 0) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
                bottomNavigation.makeGone()
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setDisplayShowHomeEnabled(false)
                bottomNavigation.makeVisible()
            }
        }

    }

    private fun startFragment(fragment: Fragment, tag: String) {
        router.replaceFragment(
            supportFragmentManager,
            binding.container.id,
            fragment,
            tag
        )
    }

    private fun startFragmentWithBundle(fragment: Fragment, tag: String, bundle: Bundle) {
        router.replaceFragmentWithBundle(
            supportFragmentManager,
            binding.container.id,
            fragment,
            tag,
            bundle
        )
    }

    private fun onNextPage() {
        pageNumber++

        if (pageNumber > LAST_PAGE) {
            pageNumber %= LAST_PAGE
        }

        val bundle = Bundle()
        bundle.putInt(
            PAGE_NUMBER_BUNDLE,
            pageNumber
        )

        previousAndNextPageNumber(pageNumber)

        startFragmentWithBundle(
            CharacterListFragment.newInstance(pageNumber),
            CHARACTER_LIST_FRAGMENT_TAG,
            bundle
        )
    }

    private fun onPreviousPage() {
        pageNumber--

        if (pageNumber < START_PAGE) {
            pageNumber = LAST_PAGE
        }

        val bundle = Bundle()
        bundle.putInt(
            PAGE_NUMBER_BUNDLE,
            pageNumber
        )

        previousAndNextPageNumber(pageNumber)

        startFragmentWithBundle(
            CharacterListFragment.newInstance(pageNumber),
            CHARACTER_LIST_FRAGMENT_TAG,
            bundle
        )
    }

    @SuppressLint("SetTextI18n")
    private fun previousAndNextPageNumber(pageNumber: Int) {
        with(binding) {
            when (pageNumber) {
                in 2..41 -> {
                    btnPrevious.text = (pageNumber - 1).toString()
                    btnNext.text = (pageNumber + 1).toString()
                }
                START_PAGE -> {
                    btnPrevious.text = LAST_PAGE.toString()
                    btnNext.text = (pageNumber + 1).toString()
                }
                LAST_PAGE -> {
                    btnPrevious.text = (pageNumber - 1).toString()
                    btnNext.text = START_PAGE.toString()
                }
            }
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
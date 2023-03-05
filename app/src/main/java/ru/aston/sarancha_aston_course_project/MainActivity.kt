package ru.aston.sarancha_aston_course_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.aston.sarancha_aston_course_project.databinding.ActivityMainBinding
import ru.aston.sarancha_aston_course_project.view.CharacterListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CharacterListFragment.newInstance()).commit()
        }

    }

    private fun initViews() {
        with(binding) {

            bottomNavigation.apply {
                setOnItemSelectedListener { item ->
                    when (item.itemId) {

                        R.id.itemMain -> {
//                            navigator().showAboutScreen()
                            true
                        }

                        R.id.itemVacancies -> {
//                            navigator().showVacanciesScreen()
                            true
                        }

                        R.id.itemOffices -> {
//                            navigator().showOfficesScreen()
                            true
                        }

                        else -> {
                            true
                        }
                    }
                }
                selectedItemId = R.id.itemMain
            }
        }
    }
}
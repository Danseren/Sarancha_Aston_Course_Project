package ru.aston.sarancha_aston_course_project.view.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import ru.aston.sarancha_aston_course_project.App.Companion.pageNumber
import ru.aston.sarancha_aston_course_project.R
import ru.aston.sarancha_aston_course_project.contract.HasCustomTitle
import ru.aston.sarancha_aston_course_project.databinding.FragmentCharacterInfoBinding
import ru.aston.sarancha_aston_course_project.model.retrofit.RepositoryRetrofitImpl
import ru.aston.sarancha_aston_course_project.utils.CHARACTER_INFO
import ru.aston.sarancha_aston_course_project.utils.loadImageFromUrl
import ru.aston.sarancha_aston_course_project.view.base.BaseFragment
import kotlin.properties.Delegates

class CharacterInfoFragment : BaseFragment<FragmentCharacterInfoBinding>(), HasCustomTitle {

    companion object {
        fun newInstance(characterId: Int): CharacterInfoFragment {
            val args = Bundle()
            args.putInt(CHARACTER_INFO, characterId)
            val fragment = CharacterInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var characterId by Delegates.notNull<Int>()

    private var disposable: Disposable = Disposable.empty()
    private val controller = RepositoryRetrofitImpl()
//    val characterResult = controller.characterResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterId = arguments?.getInt(CHARACTER_INFO)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getResult(characterId)
    }

    override fun getTitleRes(): Int = R.string.titleCharacters

    override fun getViewBinding(): FragmentCharacterInfoBinding {
        return FragmentCharacterInfoBinding.inflate(layoutInflater)
    }

    private fun getResult(characterId: Int) {

        disposable = controller
            .getData(pageNumber)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    with(binding) {
                        ivAvatar.loadImageFromUrl(it.results[characterId].image)
                        tvCharacterName.text = it.results[characterId].name
                        tvCharacterStatus.text = it.results[characterId].status
                        tvSpecies.text = it.results[characterId].species
                        tvType.text = it.results[characterId].type
                        tvGender.text = it.results[characterId].gender
                        tvOrigin.text = it.results[characterId].origin.name
                        tvLocation.text = it.results[characterId].location.name
                    }
                },
                {

                }
            )
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
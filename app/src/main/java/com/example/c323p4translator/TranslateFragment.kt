package com.example.c323p4translator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.c323p4translator.databinding.FragmentTranslateBinding
import com.google.mlkit.nl.translate.TranslateLanguage


class TranslateFragment : Fragment() {
    //variables
    private var _binding: FragmentTranslateBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TranslationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialize binding and viewModel
        _binding = FragmentTranslateBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(TranslationViewModel::class.java)

        //RadioGroups
        val sourceLangRG = binding.sourceRG
        val targetLangRG = binding.targetRG

        //checks radiogroups when changed, updates them as needed
        //source
        sourceLangRG.setOnCheckedChangeListener{group, checkedId ->
            if (binding.rbEngS.isChecked)
                viewModel.setSourceLanguage("English")
            else if (binding.rbSpanS.isChecked)
                viewModel.setSourceLanguage("Spanish")
            else if (binding.rbGermS.isChecked)
                viewModel.setSourceLanguage("German")
            else if (binding.rbAuto.isChecked)
                viewModel.setSourceLanguage("AutoDetect")

        }
        //target
        targetLangRG.setOnCheckedChangeListener{group, checkedId ->
            if (binding.rbEngT.isChecked)
                viewModel.setTargetLanguage("English")
            else if (binding.rbSpanT.isChecked)
                viewModel.setTargetLanguage("Spanish")
            else if (binding.rbGermT.isChecked)
                viewModel.setTargetLanguage("German")
        }

        //when something is typed, it is translated
        val etWrite = binding.etWrite
        etWrite.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val inputText = s.toString()
                if (binding.rbAuto.isChecked)
                    viewModel.autoTranslate(inputText, viewModel.targetLanguage.value.toString())
                else viewModel.translate(inputText, viewModel.sourceLanguage.value.toString(), viewModel.targetLanguage.value.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        //updates the translated view
        viewModel.translatedText.observe(viewLifecycleOwner, Observer { translatedText ->
            binding.tvTran.text = translatedText
        })

        return view
    }

    //on destroy, resets binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


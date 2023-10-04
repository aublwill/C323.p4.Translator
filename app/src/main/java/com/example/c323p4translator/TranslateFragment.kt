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
    private var _binding: FragmentTranslateBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TranslationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTranslateBinding.inflate(inflater, container, false)
        val view = binding.root

        //(activity as AppCompatActivity).setContentView(binding.etWrite)
        viewModel = ViewModelProvider(this).get(TranslationViewModel::class.java)

        val sourceLangRG = binding.sourceRG
        val targetLangRG = binding.targetRG

        sourceLangRG.setOnCheckedChangeListener{group, checkedId ->
            if (binding.rbEngS.isChecked)
                viewModel.setSourceLanguage("English")
            else if (binding.rbSpanS.isChecked)
                viewModel.setSourceLanguage("Spanish")
            else if (binding.rbGermS.isChecked)
                viewModel.setSourceLanguage("German")

        }
        targetLangRG.setOnCheckedChangeListener{group, checkedId ->
            if (binding.rbEngT.isChecked)
                viewModel.setTargetLanguage("English")
            else if (binding.rbSpanT.isChecked)
                viewModel.setTargetLanguage("Spanish")
            else if (binding.rbGermT.isChecked)
                viewModel.setTargetLanguage("German")
        }

       // updateScreen()

        //val old = binding.etWrite.text.toString()
        /*
        var new: String
        var sourceLang: String = ""
        var finalLang: String = ""
        if (binding.rbEngS.isChecked)
            sourceLang = "English"
        else if (binding.rbGermS.isChecked)
            sourceLang = "German"
        else if (binding.rbSpanS.isChecked)
            sourceLang = "Spanish"
        if (binding.rbEngT.isChecked)
            finalLang = "English"
        else if (binding.rbGermT.isChecked)
            finalLang = "German"
        else if (binding.rbSpanT.isChecked)
            finalLang = "Spanish"

//        val languageIdentifier = LanguageIdentification.getClient(
//            LanguageIdentificationOptions.Builder()
//                .setConfidenceThreshold(0.5f)
//                .build()
//        )
//        val options = TranslateLanguage.fromLanguageTag(sourceLang.uppercase())?.let {
//            TranslateLanguage.fromLanguageTag(finalLang.uppercase())?.let { it1 ->
//                TranslatorOptions.Builder()
//                    .setSourceLanguage(it)
//                    .setTargetLanguage(it1)
//                    .build()
//            }
//        }
//        val translator: Translator? = options?.let { Translation.getClient(it) }



         */
        val etWrite = binding.etWrite
        etWrite.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val inputText = s.toString()
                viewModel.translate(inputText, viewModel.sourceLanguage.value.toString(), viewModel.targetLanguage.value.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        viewModel.translatedText.observe(viewLifecycleOwner, Observer { translatedText ->
            binding.tvTran.text = translatedText
        })
        /*
        viewModel.sourceLanguage.observe(viewLifecycleOwner, Observer { sourceLanguage ->
            viewModel.setSourceLanguage(sourceLanguage)
        })
        viewModel.targetLanguage.observe(viewLifecycleOwner, Observer {targetLanguage ->
            viewModel.setTargetLanguage(targetLanguage)
        })

         */


        return view
    }

//        var trans = viewModel.translate(sourceLang, finalLang, viewModel.original.toString())
//        viewModel.original.observe(viewLifecycleOwner, Observer { list ->
//
//            binding.tvTran.text = viewModel.original.toString()
//               // viewModel.original.value?.let { viewModel.translate(sourceLang, finalLang, it) }
//        })
//
//        return view
//    }

    /*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sourceLangRG = binding.sourceRG
        val targetLangRG = binding.targetRG

        sourceLangRG.setOnCheckedChangeListener{group, checkedId ->
            if (binding.rbEngS.isChecked)
                viewModel.setSourceLanguage(TranslateLanguage.ENGLISH)
            else if (binding.rbSpanS.isChecked)
                viewModel.setSourceLanguage(TranslateLanguage.SPANISH)
            else if (binding.rbGermS.isChecked)
                viewModel.setSourceLanguage(TranslateLanguage.GERMAN)

        }
        targetLangRG.setOnCheckedChangeListener{group, checkedId ->
            if (binding.rbEngT.isChecked)
                viewModel.setTargetLanguage(TranslateLanguage.ENGLISH)
            else if (binding.rbSpanT.isChecked)
                viewModel.setTargetLanguage(TranslateLanguage.SPANISH)
            else if (binding.rbGermT.isChecked)
                viewModel.setTargetLanguage(TranslateLanguage.GERMAN)
        }
    }

     */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    fun updateScreen(){
//        binding.tvTran.text = viewModel.trans.toString()
//    }


}


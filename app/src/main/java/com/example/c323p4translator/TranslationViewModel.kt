package com.example.c323p4translator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.languageid.LanguageIdentificationOptions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslationViewModel: ViewModel() {
    //variables
    private val _translatedText = MutableLiveData<String>()
    private val _sourceLanguage = MutableLiveData<String>()
    private val _targetLanguage = MutableLiveData<String>()

    //get variables
    val translatedText:LiveData<String>
        get() = _translatedText
    val sourceLanguage:LiveData<String>
        get() = _sourceLanguage
    val targetLanguage:LiveData<String>
        get() = _targetLanguage

    //initialize translator
    private var translator:Translator

    init {
        val defaultTranslationOptions = getDefaultTranslationOptions()
        translator = Translation.getClient(defaultTranslationOptions)
    }

    private val languageIdentifier = LanguageIdentification.getClient(
        LanguageIdentificationOptions.Builder()
            .setConfidenceThreshold(0.5f)
            .build()
    )

    /*
    method that initializes translator with default options
    no params
    returns TranslatorOptions
     */
    private fun getDefaultTranslationOptions():TranslatorOptions{
        return TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()
    }

    /*
    method that translates the given text
    @param text: string fro translate
    @param sourceLang: string that identifies what the source language is
    @param targetLang: string that identifies what the target language is
     */
    fun translate(text:String, sourceLang:String, targetLang:String){

        translator = createTranslator(sourceLang, targetLang)
        translator.downloadModelIfNeeded()
        translator.translate(text)
            .addOnSuccessListener { translatedText ->
                _translatedText.value = translatedText
            }
            .addOnFailureListener{e ->
                Log.e(e.toString(), e.toString())
            }

        /*

        languageIdentifier.identifyLanguage(text)
            .addOnSuccessListener { languageCode ->
                _sourceLanguage.value = languageCode
                _targetLanguage.value = targetLang
                translator = createTranslator(languageCode, targetLang)
                translator.downloadModelIfNeeded()
                translator.translate(text)
                    .addOnSuccessListener { translatedText ->
                        _translatedText.value = translatedText
                    }
                    .addOnFailureListener{e ->
                        Log.e(e.toString(), e.toString())
                    }
            }
            .addOnFailureListener{e ->
                Log.e(e.toString(), e.toString())
            }


         */

    }

    /*
    method that creates / updates translator
    @param sourceLanguageCode: string that identifies the source language
    @param targetLanguageCode: string that identifies the target language
     */
    private fun createTranslator(sourceLanguageCode:String, targetLanguageCode:String): Translator{
        var source = ""
        if (sourceLanguageCode.equals("English"))
            source = TranslateLanguage.ENGLISH
        if (sourceLanguageCode.equals("Spanish"))
            source = TranslateLanguage.SPANISH
        if (sourceLanguageCode.equals("German"))
            source = TranslateLanguage.GERMAN
        var target = ""
        if (targetLanguageCode.equals("English"))
            target = TranslateLanguage.ENGLISH
        if (targetLanguageCode.equals("Spanish"))
            target = TranslateLanguage.SPANISH
        if (targetLanguageCode.equals("German"))
            target = TranslateLanguage.GERMAN
        val translationOptions = TranslatorOptions.Builder()
            .setSourceLanguage(source)
            .setTargetLanguage(target)
            .build()

        return Translation.getClient(translationOptions)
    }

    /*
    method that sets/resets the source language
    @param languageCode: string that will be set to be the source language
     */
    fun setSourceLanguage(languageCode: String){
        _sourceLanguage.value = languageCode
        translator = createTranslator(languageCode, targetLanguage.value.toString())
    }
    /*
    method that sets/resets the target language
    @param languageCode: string that will be set to be the target language
     */
    fun setTargetLanguage(languageCode: String){
        _targetLanguage.value = languageCode
        translator = createTranslator(sourceLanguage.value.toString(), languageCode)
    }
}
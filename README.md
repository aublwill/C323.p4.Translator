Collaboration with Kuzey Bektas

# C323.p4.Translator
This project functions as a translator.

## Functionality 
The following **required** functionality is completed:
* [] user sees two sets of buttons to select: one for input language (English, Spanish, German) and output language (English, Spanish, German)
* [] user can type into the space that says 'Write something'
* [] As user is typing, the translation will be displayed above

The folowing **extensions** are implemented:
* import android.util.Log
* import androidx.lifecycle.LiveData
* import androidx.lifecycle.MutableLiveData
* import androidx.lifecycle.ViewModel
* import com.google.mlkit.nl.languageid.LanguageIdentification
* import com.google.mlkit.nl.languageid.LanguageIdentificationOptions
* import com.google.mlkit.nl.translate.TranslateLanguage
* import com.google.mlkit.nl.translate.Translation
* import com.google.mlkit.nl.translate.Translator
* import com.google.mlkit.nl.translate.TranslatorOptions
* import android.os.Bundle
* import android.text.Editable
* import android.text.TextWatcher
* import androidx.fragment.app.Fragment
* import android.view.LayoutInflater
* import android.view.View
* import android.view.ViewGroup
* import androidx.lifecycle.Observer
* import androidx.lifecycle.ViewModelProvider
* import com.example.c323p4translator.databinding.FragmentTranslateBinding
  
## Video Walkthrough 



## Notes
* Does not detect language

## License
Copyright [2023] [Aubrey Williams]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

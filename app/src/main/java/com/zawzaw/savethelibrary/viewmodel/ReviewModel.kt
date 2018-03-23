package com.zawzaw.savethelibrary.viewmodel

import android.arch.lifecycle.ViewModel
import java.util.ArrayList
import com.zawzaw.savethelibrary.model.gson.GsonBook


/**
 * Created by zawzaw on 09/01/18.
 */

class ReviewModel : ViewModel() {

    var gsonBooks: List<GsonBook> = ArrayList()

}

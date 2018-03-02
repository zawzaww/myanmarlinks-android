package com.zawzaw.savethelibrary.viewmodel;

import android.arch.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import com.zawzaw.savethelibrary.model.gson.GsonBook;


/**
 * Created by zawzaw on 09/01/18.
 */

public class ReviewModel extends ViewModel {

    public List<GsonBook> gsonBooks = new ArrayList<>();

}

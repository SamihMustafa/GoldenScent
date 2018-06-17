package com.media.goldenscent;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by Samih on 17-Jun-18.
 */
public class MainViewModel extends ViewModel {

    private LiveData<List<String>> imageList;
    private LiveData<String> videoLink;

    public MainViewModel(){

    }

    public LiveData<String> getVideoLink(){
        return videoLink;
    }

    public LiveData<List<String>> getListOfImages(){
        return imageList;
    }


}

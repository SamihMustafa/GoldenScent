package com.media.goldenscent;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Samih on 17-Jun-18.
 */
public class MainViewModel extends ViewModel {

    private MutableLiveData<List<String>> imageList;
    private MutableLiveData<String> videoLink;
    private MutableLiveData<Integer> videoPosition;

    public MainViewModel(){

    }

    public LiveData<String> getVideoLink(){
        if(videoLink == null){
            videoLink = new MutableLiveData<>();
            selectVideoLink();
        }
        return videoLink;
    }

    private void selectVideoLink() {
        videoLink.setValue("http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8");
}

    public LiveData<List<String>> getListOfImages(){
        if(imageList == null){
            imageList = new MutableLiveData<>();
            createList();
        }
        return imageList;
    }

    private void createList() {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            list.add("https://picsum.photos/200/300?image=" + new Random().nextInt(100));
        }
        imageList.setValue(list);
    }

    public MutableLiveData<Integer> getVideoPosition(){
        if(videoPosition == null){
            videoPosition = new MutableLiveData<>();
            videoPosition.setValue(0);
        }
        return videoPosition;
    }



}

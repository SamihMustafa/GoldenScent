package com.media.goldenscent;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.MutableInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Samih on 17-Jun-18.
 */
public class MainViewModel extends ViewModel {

    private MutableLiveData<List<String>> imageList;
    private MutableLiveData<Integer> videoPosition;
    private static int count = 0;
    public ArrayList<String> videoList;

    public MainViewModel(){
        createVideoLink();
        videoPosition = new MutableLiveData<>();
        videoPosition.setValue(count);
    }

    public MutableLiveData<Integer> getVideoPosition(){
        if(videoPosition == null){
            videoPosition = new MutableLiveData<>();
            videoPosition.setValue(count);
        }
        return videoPosition;
    }

    private void createVideoLink() {
        videoList = new ArrayList<>();
        videoList.add("http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8");
        videoList.add("http://playertest.longtailvideo.com/adaptive/wowzaid3/playlist.m3u8");
        videoList.add("http://cdn-fms.rbs.com.br/hls-vod/sample1_1500kbps.f4v.m3u8");
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
            list.add("https://picsum.photos/200/300?image=" + new Random().nextInt(50));
        }
        imageList.setValue(list);
    }


    public void playNextVideo() {
        count++;
        if(count == 3) count = 0;
        videoPosition.setValue(count);
    }
}

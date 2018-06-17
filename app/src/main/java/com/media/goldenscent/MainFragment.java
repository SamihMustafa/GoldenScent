package com.media.goldenscent;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.media.goldenscent.ui.CarouselAdapter;
import com.media.goldenscent.ui.CarouselLayoutManager;

/**
 * Created by Samih on 17-Jun-18.
 */
public class MainFragment extends Fragment {

    public static final String TAG = "Main Fragment";

    private MainViewModel viewModel;

    private VideoView myVideo;
    private RecyclerView carousel;
    private ImageButton leftImageButton;
    private ImageButton rightImageButton;
    private CarouselLayoutManager layoutManager;
    private View view;

    public MainFragment(){
        // Requires empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);



        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setVideoPlayer(view);
        setImageButtons(view);
        setCarouselList(view);


    }

    private void setImageButtons(View view) {
        leftImageButton = view.findViewById(R.id.move_left);
        rightImageButton = view.findViewById(R.id.move_right);

        leftImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = layoutManager.findFirstVisibleItemPosition();
                carousel.scrollToPosition(pos - 1);
            }
        });


        rightImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = layoutManager.findLastVisibleItemPosition();
                carousel.scrollToPosition(pos + 1);
            }
        });

    }

    private void setCarouselList(View view) {
        carousel = view.findViewById(R.id.myCarousel);
        layoutManager = (new CarouselLayoutManager(getContext()));
        carousel.setLayoutManager(layoutManager);
        carousel.setHasFixedSize(true);

        CarouselAdapter adapter = new CarouselAdapter(getContext());
        carousel.setAdapter(adapter);
        carousel.getLayoutManager().scrollToPosition(Integer.MAX_VALUE / 2);
    }

    private void setVideoPlayer(View view) {
        myVideo = view.findViewById(R.id.myVideo);
        myVideo.setVideoURI(Uri.parse("http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8"));
        myVideo.start();
        myVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setVolume(0,0);
                mediaPlayer.setLooping(true);
            }
        });
    }

}

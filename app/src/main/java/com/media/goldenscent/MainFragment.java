package com.media.goldenscent;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.media.goldenscent.ui.CarouselAdapter;
import com.media.goldenscent.ui.CarouselLayoutManager;

import java.util.List;

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
    private CarouselAdapter adapter;

    public MainFragment(){
        // Requires empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        setVideoPlayer(view);
        setImageButtons(view);
        setCarouselList(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.getListOfImages().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> imageList) {
                adapter.setItemList(imageList);
            }
        });

    }

    private void setImageButtons(View view) {
        leftImageButton = view.findViewById(R.id.move_left);
        rightImageButton = view.findViewById(R.id.move_right);

        leftImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = layoutManager.findFirstVisibleItemPosition();
                layoutManager.scrollToPosition(pos - 1);
            }
        });


        rightImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = layoutManager.findLastVisibleItemPosition();
                layoutManager.scrollToPosition(pos + 1);
            }
        });

    }

    private void setCarouselList(View view) {
        carousel = view.findViewById(R.id.myCarousel);
        layoutManager = (new CarouselLayoutManager(getContext()));
        carousel.setLayoutManager(layoutManager);
        carousel.setHasFixedSize(true);

        adapter = new CarouselAdapter(getContext());
        carousel.setAdapter(adapter);
        carousel.getLayoutManager().scrollToPosition(Integer.MAX_VALUE / 2);
    }

    private void setVideoPlayer(View view) {
        myVideo = view.findViewById(R.id.myVideo);
        myVideo.setOnPreparedListener(listener);
        myVideo.setOnCompletionListener(onCompletedListener);
        viewModel.getVideoPosition().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer pos) {
                playVideo(pos);
            }
        });
    }

    private void playVideo(Integer pos) {
        myVideo.setVideoURI(Uri.parse(viewModel.videoList.get(pos)));
        myVideo.start();
    }

    final MediaPlayer.OnCompletionListener onCompletedListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            viewModel.playNextVideo();
        }
    };

    final MediaPlayer.OnPreparedListener listener = new MediaPlayer.OnPreparedListener() {

        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.setVolume(0,0);
        }
    };

}

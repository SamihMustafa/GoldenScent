package com.media.goldenscent;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.media.goldenscent.ui.CarouselAdapter;
import com.media.goldenscent.ui.CarouselLayoutManager;

public class MainActivity extends AppCompatActivity {

    private VideoView myVideo;
    private RecyclerView carousel;
    private ImageButton leftImageButton;
    private ImageButton rightImageButton;
    private CarouselLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVideoPlayer();

        setCarouselList();

        setImageButtons();

    }

    private void setImageButtons() {
        leftImageButton = findViewById(R.id.move_left);
        rightImageButton = findViewById(R.id.move_right);

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

    private void setCarouselList() {
        carousel = findViewById(R.id.myCarousel);
        layoutManager = (new CarouselLayoutManager(this));
        carousel.setLayoutManager(layoutManager);
        carousel.setHasFixedSize(true);

        CarouselAdapter adapter = new CarouselAdapter(this);
        carousel.setAdapter(adapter);
        carousel.getLayoutManager().scrollToPosition(Integer.MAX_VALUE / 2);
    }

    private void setVideoPlayer() {
        myVideo = findViewById(R.id.myVideo);
        myVideo.setVideoURI(Uri.parse("http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8"));
        myVideo.start();
        myVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
    }
}

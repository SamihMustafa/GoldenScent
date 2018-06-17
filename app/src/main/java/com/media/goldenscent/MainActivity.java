package com.media.goldenscent;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.VideoView;

import com.media.goldenscent.ui.CarouselLayoutManager;

public class MainActivity extends AppCompatActivity {

    private VideoView myVideo;
    private RecyclerView carousel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myVideo = findViewById(R.id.myVideo);
        myVideo.setVideoURI(Uri.parse("http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8"));
        myVideo.start();
        myVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        carousel = findViewById(R.id.myCarousel);
        carousel.setLayoutManager(new CarouselLayoutManager(this));
        carousel.setHasFixedSize(true);

        CarouselAdapter adapter = new CarouselAdapter(this);
        carousel.setAdapter(adapter);

    }
}

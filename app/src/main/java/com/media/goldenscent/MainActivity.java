package com.media.goldenscent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (savedInstanceState == null) {
            MainFragment fragment = new MainFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, fragment, MainFragment.TAG).commit();
        }

    }


}

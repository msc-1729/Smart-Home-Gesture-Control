package com.example.assignment2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewGesture extends AppCompatActivity {

    private static String GESTURE_file_name;
    private VideoView video_name;
    private int replay = 1;
    private String chosen_gesture;
    public static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 112;
    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 112;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        video_name = findViewById(R.id.View_Practice_Video);
        Intent intent = getIntent();
        chosen_gesture = intent.getStringExtra("gestures");
        System.out.println(chosen_gesture);
        GESTURE_file_name = "video" + chosen_gesture.replaceAll(" ", "").toLowerCase();
        System.out.println(GESTURE_file_name);
        Button replay_button = (Button) findViewById(R.id.replay_gesture_button);
        replay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializePlayer();
            }
        });
        Button practice_button = (Button) findViewById(R.id.practice_button);

        practice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(getApplicationContext(), RecordGesture.class);
                int3.putExtra("gestureitem", chosen_gesture);
                startActivity(int3);

            }
        });

    }

    private void requestPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            this.initializePlayer();
        } else {
            String[] permissionReq = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permissionReq, READ_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        initializePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            video_name.pause();
        }
    }

    private void initializePlayer() {
        Uri videoUri = getMedia(GESTURE_file_name);
        video_name.setVideoURI(videoUri);
        video_name.start();

    }


    private Uri getMedia(String mediaName) {
        return Uri.parse("android.resource://" + getPackageName() +
                "/raw/" + mediaName);
    }


    private void releasePlayer() {
        video_name.stopPlayback();
    }


    public void replayVideo(View view) {
        initializePlayer();
    }

    public void goToPracticeScreen(View view) {
//        Intent practiceGestureActivityIntent = new Intent(WatchGestureActivity.this, PracticeGestureActivity.class);
//        practiceGestureActivityIntent.putExtra("gesture_name", gestureSelected);
//        startActivity(practiceGestureActivityIntent);

    }

}
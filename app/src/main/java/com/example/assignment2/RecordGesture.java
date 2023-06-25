package com.example.assignment2;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecordGesture extends AppCompatActivity {
    private Intent mainActivity;
    public String video_file_id;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 1996;
    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 112;
    private static final int VIDEO_CAPTURE = 101;
    private Uri uri_file;
    public int Practice_number = 0;

    public String Chosen_gesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Intent intent = getIntent();
        Chosen_gesture = intent.getStringExtra("gestureitem");
        if (Chosen_gesture.equals("Turn on lights")) {
            video_file_id ="LightOn";
        } else if (Chosen_gesture.equals("Turn off lights")) {
            video_file_id = "LightOff";
        } else if (Chosen_gesture.equals("Turn on fan")) {
            video_file_id = "FanOn";;
        } else if (Chosen_gesture.equals("Turn off fan")) {
            video_file_id = "FanOff";
        } else if (Chosen_gesture.equals("Increase fan speed")) {
            video_file_id = "FanUp";
        } else if (Chosen_gesture.equals("decrease fan speed")) {
            video_file_id = "FanDown";
        } else if (Chosen_gesture.equals("Set Thermostat to specified temperature")) {
            video_file_id ="SetThermo";
        } else if (Chosen_gesture.equals("Number 0")) {
            video_file_id = "Num0";
        } else if (Chosen_gesture.equals("Number 1")) {
            video_file_id = "Num1";
        } else if (Chosen_gesture.equals("Number 2")) {
            video_file_id = "Num2";
        } else if (Chosen_gesture.equals("Number 3")) {
            video_file_id = "Num3";
        } else if (Chosen_gesture.equals("Number 4")) {
            video_file_id = "Num4";
        } else if (Chosen_gesture.equals("Number 5")) {
            video_file_id = "Num5";
        } else if (Chosen_gesture.equals("Number 6")) {
            video_file_id = "Num6";
        } else if (Chosen_gesture.equals("Number 7")) {
            video_file_id = "Num7";
        } else if (Chosen_gesture.equals("Number 8")) {
            video_file_id = "Num8";
        } else if (Chosen_gesture.equals("Number 9")) {
            video_file_id = "Num9";
        }
        mainActivity = new Intent(this, MainSelection.class);
        Button record_button = (Button) findViewById(R.id.button2);

        record_button.setOnClickListener((v) -> {
            this.preInvokeCamera();
        });
        Button upload_button = (Button) findViewById(R.id.button3);

        upload_button.setOnClickListener((v) -> {
            this.uploadMultipleFiles();
        });
    }

    private void preInvokeCamera() {
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            this.startRecording();
        } else {
            String[] permissionReq = {Manifest.permission.CAMERA};
            requestPermissions(permissionReq, CAMERA_PERMISSION_REQUEST_CODE);

            String[] permissionReq2 = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissionReq2, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.startRecording();
            } else {
//                Toast.makeText(this, "Camera permission required.", Toast.LENGTH_LONG).show();
                this.finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void startRecording() {
        File vidPath = getExternalFilesDir(Environment.getStorageDirectory().getAbsolutePath());
        File vidFile = new File(vidPath, getFileName(Practice_number));
        System.out.println("look here for the video file name");
        System.out.println(vidFile);
        uri_file = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileprovider", vidFile);
        //Toast.makeText(this,  getFileName(practicenum), Toast.LENGTH_LONG).show();
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
        takeVideoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri_file);
        takeVideoIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, VIDEO_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Practice_number = (Practice_number + 1) % 3;
            this.showPreview();
        } else {
            super.onActivityResult(requestCode, resultCode, intent);
        }
    }

    public String getFileName(int num) {

        return video_file_id + "_PRACTICE_"+ (num + 1) +".mp4";
    }
    private void showPreview() {
        VideoView display_video = findViewById(R.id.videoView2);
        display_video.setVideoURI(uri_file);
        display_video.start();
        display_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }
    private void uploadMultipleFiles() {
        File videoPath = getExternalFilesDir(Environment.getStorageDirectory().getAbsolutePath());
        File video1 = new File(videoPath, getFileName(0));
        MultipartBody.Part videoUpload1 = MultipartBody.Part.createFormData(video1.getName(),
                video1.getName(), RequestBody.create(MediaType.parse("*/*"), video1));
        System.out.println("Look for the videoupload1 output after multipart");
        System.out.println(videoUpload1);
        ServerApi getResponse = ServerConnection.getRetrofit().create(ServerApi.class);
        Call<ServerResponse> apicall = getResponse.uploadFile1(videoUpload1);
        apicall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse != null) {
                    if (serverResponse.getSuccess()) {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    assert serverResponse != null;
                    Log.v("Response", serverResponse.toString());
                }
                startActivity(mainActivity);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                startActivity(mainActivity);
            }
        });
    }


}



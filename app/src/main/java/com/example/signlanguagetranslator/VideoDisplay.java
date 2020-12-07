package com.example.signlanguagetranslator;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class VideoDisplay extends AppCompatActivity {

    private FirebaseStorage db=FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_display);
        MediaController mc=new MediaController(this);
        String sessionId = getIntent().getStringExtra("video_id");
        ProgressBar progressBar=(ProgressBar)findViewById(R.id.bufferProgress);
        VideoView video=(VideoView) findViewById(R.id.mainVideo);
        System.out.println(sessionId);

        downloadVideo(video,sessionId,mc,progressBar);
    }

    public void downloadVideo(VideoView v,String url,MediaController mc,ProgressBar progressBar){
        StorageReference ref = db.getReferenceFromUrl(url);

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                mc.setAnchorView(v);
                v.setMediaController(mc);
                v.setVideoURI(uri);
                v.requestFocus();

                progressBar.setVisibility(View.VISIBLE);

                v.setOnPreparedListener(new  MediaPlayer.OnPreparedListener() {

                    @Override
                    public void onPrepared(MediaPlayer mp) {

                        mp.start();

                        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {

                            @Override
                            public void onVideoSizeChanged(MediaPlayer mp, int arg1, int arg2) {
                                progressBar.setVisibility(View.GONE);
                                mp.start();
                            }
                        });
                    }
                });
                v.start();



            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure( Exception e) {
                Toast.makeText(getApplicationContext(),"Sorry No Internet Connection",Toast.LENGTH_SHORT).show();

            }
        });

    }
}

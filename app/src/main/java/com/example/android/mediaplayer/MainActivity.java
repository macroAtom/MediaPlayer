package com.example.android.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button start,pause,forward;
    private double startTime = 0;
    private double finalTime = 0;
//    private Handler myHandler = new Handler();
    private int forwardTime = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=(Button)findViewById(R.id.play_button);
        pause=(Button)findViewById(R.id.pause_button);
        forward=(Button)findViewById(R.id.forward_button);

        //creating media player
//        final MediaPlayer mp=new MediaPlayer();
//      用工厂方法创建的对象不需要prepare，因为create()已经准备好音频文件
        MediaPlayer mp = MediaPlayer.create(this, R.raw.karmin);

        try{
            //you can change the path, here path is external directory(e.g. sdcard) /Music/maine.mp3
//            mp.setDataSource(Environment.getExternalStorageDirectory().getPath()+"/Music/maine.mp3");

            mp.prepare();
        }catch(Exception e){e.printStackTrace();}

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                finalTime = mp.getDuration();
                startTime = mp.getCurrentPosition();

            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
            }
        });

//        stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mp.stop();
//
//            }
//        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp+forwardTime)<=finalTime){
                    startTime = startTime + forwardTime;
                    mp.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),"You have Jumped forward 5 seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot jump forward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });

//                当音频播放完成时调用
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
//                mp.release();
//                mp = null;
                Toast.makeText(MainActivity.this,"I'm done!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
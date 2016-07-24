package com.musicadd;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {
    MediaPlayer player;
    int numTaps = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageview=(ImageView)findViewById(R.id.imageview);
        Button button1=(Button)findViewById(R.id.button1);
        Button button2=(Button)findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numTaps ++;
                if(numTaps < 2) {
                    player=MediaPlayer.create(MainActivity.this,R.raw.song1);
                    player.setLooping(true);
                    player.start();
                    //conver to pause
                }
                if(numTaps%2 == 0) {
                    player.pause();
                    //cont to start
                }
                if(numTaps%2 != 0 ){
                    player.start();
                    //convert to start
                }

            }
            InputStream inputStream = getResources().openRawResource(R.raw.artistdata);
            CSVFile csvFile = new CSVFile(inputStream);
            ArrayList<Artist> artistList = csvFile.read();
            for(int i = 0 ; i < artistList.size() ; i++){

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
            }
        });

    }

}

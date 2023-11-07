package com.example.multimedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
        videoView = findViewById(R.id.videoView);
        Button playButton = findViewById(R.id.playButton);

        // Cargar la imagen desde recursos
        imageView.setImageResource(R.drawable.quemequedosincomer);

        // Configurar el reproductor de audio
        mediaPlayer = MediaPlayer.create(this, R.raw.quemequedosincomer);

        // Configurar el reproductor de video
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);

        // Configurar el MediaController y asignarlo al VideoView
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playButton.setText("Reproducir Audio");
                } else {
                    mediaPlayer.start();
                    playButton.setText("Pausar Audio");
                }
            }
        });

        // Iniciar la reproducción de video automáticamente
        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}


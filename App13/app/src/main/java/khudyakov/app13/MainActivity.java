                                                                                                                                                                                            package khudyakov.app13;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

                                                                                                                                                                                            public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://khudyakov.app13/"
                + R.raw.trainspotting_trailer));
        videoView.setMediaController(new MediaController(this));
        videoView.start();
        videoView.requestFocus();
    }
}

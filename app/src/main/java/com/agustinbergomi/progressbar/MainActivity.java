package com.agustinbergomi.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    static int progress;
    ProgressBar progressBar;
    int progressStatus = 0;
    Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = 0;
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

//        Tarea en el background
        new Thread(new Runnable()
        {
            public void run()
            {
                while (progressStatus < 10)
                {
                    progressStatus = hacerAlgo();
                }

                handler.post(new Runnable()
                {
                    public void run()
                    {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
            private int hacerAlgo()
            {
//                Simulo tarea larga
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                return ++progress;
            }
        }).start();
    }
}
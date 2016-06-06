package com.acadgild.balu.acd_an_session_8_assignment_3_main;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    ProgressBar progressBar1, progressBar2;
    int max_count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar1.setMax(max_count);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar2.setMax(max_count);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button)
        {
            progressBar1.setVisibility(ProgressBar.VISIBLE);
            progressBar2.setVisibility(ProgressBar.VISIBLE);
            new DownloadData1().execute();
        }
    }

    class DownloadData1 extends AsyncTask<Void, Integer, String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params)
        {
            for (int i = 1; i <= max_count; i++) {

                try {
                    Thread.sleep(500);
                    publishProgress(i);
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return getResources().getString(R.string.download_complete);
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            button.setText(getResources().getString(R.string.downloading) + " " + values[0]);
            progressBar1.setProgress(values[0]);
            progressBar2.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(String result)
        {
            progressBar1.setVisibility(View.GONE);
            progressBar2.setVisibility(View.GONE);
            button.setText(result);
        }
    }
}

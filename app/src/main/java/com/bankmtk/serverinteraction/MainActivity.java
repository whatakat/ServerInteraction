package com.bankmtk.serverinteraction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView mInfoTextView;
    private ProgressBar progressBar;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit_text);
        mInfoTextView = (TextView) findViewById(R.id.tvLoad);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Button btnLoad = (Button)findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener((v)->onClick());
    }
    public void onClick(){
        String bestUrl ="https://api.github.com/whatakat";
        if (editText.getText().toString().isEmpty()){
            bestUrl+="/"+editText.getText();
        }
        ConnectivityManager connectivityManager =
                (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = connectivityManager.getActiveNetwork();
        if (network != null){
            new DownloadPageTask().execute(bestUrl);//start new stream
        }else{
            Toast.makeText(this,"connect internet",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private class DownLoadPageTask extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mInfoTextView.setText("");
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                return downLoadOneUrl(urls[0]);
            }catch (IOException e){
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            mInfoTextView.setText(result);
            progressBar.setVisibility(View.GONE);
            super.onPostExecute(result);
        }
    }
}

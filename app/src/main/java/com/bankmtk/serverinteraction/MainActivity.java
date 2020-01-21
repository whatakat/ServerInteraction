package com.bankmtk.serverinteraction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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
        }


    }
}

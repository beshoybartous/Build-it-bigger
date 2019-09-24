package com.example.androidjokesproviderlibrary;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.androidjokesproviderlibrary.databinding.ActivityJokeBinding;

public class JokeActivity extends AppCompatActivity {
    private final static String JOKE="GET_JOKE";

    ActivityJokeBinding jokeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        jokeBinding= DataBindingUtil.setContentView( this, R.layout.activity_joke);
        if(getIntent()!=null){
            Intent i=getIntent();
            jokeBinding.joke.setText( i.getStringExtra( JOKE ) );
        }
    }
}

package com.example.signlanguagetranslator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    public void Action(View view) {


        //finish();
        startActivity(new Intent(this,MainActivity.class));


    }

    public void dictionary(View view) {
        startActivity(new Intent(this,Dictionary.class));

    }



    public void translate(View view) {
        startActivity(new Intent(this,DetectorActivity.class));


    }
}

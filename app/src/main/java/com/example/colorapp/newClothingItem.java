package com.example.colorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class newClothingItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_clothing_item);
        Intent intent = getIntent();
        if(intent.hasExtra("type")){
            String title = "New " + intent.getStringExtra("type");
            this.setTitle(title);
        }
    }

    private void buildOuterwear(){
        Slider tempSlider
    }

    private void buildTop(){}

    private void buildBottom(){}

    private void butFootwear(){}
}
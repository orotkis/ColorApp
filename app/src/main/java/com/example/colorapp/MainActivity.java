package com.example.colorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FAB to open new clothing item menu
        SpeedDialView speedDialView = findViewById(R.id.speedDial);
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_footwear, R.drawable.ic_link_white_24dp).create());
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_bottom, R.drawable.ic_link_white_24dp).create());
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_top, R.drawable.tshirt_crew).create());
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_outerwear, R.drawable.ic_link_white_24dp).create());
        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem speedDialActionItem) {
                switch (speedDialActionItem.getId()) {
                    case R.id.fab_add_outerwear:
                        Log.d("Clicked", "Outerwear clicked.");
                        return false; // true to keep the Speed Dial open
                    case R.id.fab_add_top:
                        Log.d("Clicked", "Tops clicked.");
                        return false;
                    case R.id.fab_add_bottom:
                        Log.d("Clicked", "Bottoms clicked.");
                        return false;
                    case R.id.fab_add_footwear:
                        Log.d("Clicked", "Footwear clicked.");
                        return false;
                    default:
                        return false;
                }
            }
        });
    }


    //Displays outerwear in wardrobe on outerwear button click - if empty,
    //displays toast
    public void outerwearOnClick(View v){

    }

    //Displays tops in wardrobe on tops button click - if empty,
    //displays toast
    public void topsOnClick(View v){

    }

    //Displays bottoms in wardrobe on bottoms button click - if empty,
    //displays toast
    public void bottomsOnClick(View v){

    }

    //Displays footwear in wardrobe on footwear button click - if empty,
    //displays toast
    public void footwearOnClick(View v){

    }

    //switches to add new clothing item activity
    public void addNewOnClick(View v){
        Intent intent = new Intent(MainActivity.this, newClothingItem.class);
        startActivity(intent);
    }
}
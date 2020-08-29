package com.example.colorapp;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

public class newClothingItem extends AppCompatActivity {
    public int dominantColor;
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private String type;
    private Bitmap imageBitmap;
    private Button picButton;
    private ImageView tee;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final String CAMERA_PERMISSION = "android.hardware.camera";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_clothing_item);
        picButton = (Button)findViewById(R.id.imageButton);
        tee = (ImageView)findViewById(R.id.shirtColorView);
        Intent intent = getIntent();
        if(intent.hasExtra("type")){
            String title = "New " + intent.getStringExtra("type");
            type = intent.getStringExtra("type");
            this.setTitle(title);
            switch(type){
                case "Outerwear":
                    tee.setImageResource(R.drawable.snowflake);
                    return;
                case "Bottom":
                    tee.setImageResource(R.drawable.seat_legroom_extra);
                    return;
                case "Footwear":
                    tee.setImageResource(R.drawable.shoe_formal);
                    return;
                case "Top":
                    return;
            }
        }
    }

    public void saveClothingItem(){
       //TODO: think about how to save clothing objects in mainactivity
    }

    public void colorPicker(View v){
        final ColorPicker cp = new ColorPicker(this, red, green, blue);
        cp.show();

        cp.enableAutoClose(); // Enable auto-dismiss for the dialog

        /* Set a new Listener called when user click "select" */
        cp.setCallback(new ColorPickerCallback() {
            @Override
            public void onColorChosen(@ColorInt int color) {
                // Do whatever you want
                // Examples
                Log.d("Alpha", Integer.toString(Color.alpha(color)));
                Log.d("Red", Integer.toString(Color.red(color)));
                red = Color.red(color);
                Log.d("Green", Integer.toString(Color.green(color)));
                green = Color.green(color);
                Log.d("Blue", Integer.toString(Color.blue(color)));
                blue = Color.blue(color);

                Log.d("Pure Hex", Integer.toHexString(color));
                Log.d("#Hex no alpha", String.format("#%06X", (0xFFFFFF & color)));
                Log.d("#Hex with alpha", String.format("#%08X", (0xFFFFFFFF & color)));

                tee.setColorFilter(color);
                dominantColor = color;
                // If the auto-dismiss option is not enable (disabled as default) you have to manually dimiss the dialog
                // cp.dismiss();
            }
        });
    }

    public void takePicture(View v){
        //asks for permission to use camera
        ActivityCompat.requestPermissions(this, new String[]{CAMERA_PERMISSION}, 1);
        int permissionCheck = ContextCompat.checkSelfPermission(this, CAMERA_PERMISSION);
        //if granted, then take a picture of clothes
        if(PackageManager.PERMISSION_GRANTED == permissionCheck) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
        //otherwise, use the color picker to choose the color manually and disable the camera button.
        else{
            picButton.setEnabled(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            //getting dominant color using Palette on a different thread
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    Palette.from(imageBitmap).generate(new Palette.PaletteAsyncListener() {
                        public void onGenerated(Palette p) {
                            dominantColor = p.getDominantColor(0);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ImageView tee = (ImageView)findViewById(R.id.shirtColorView);
                                    tee.setColorFilter(dominantColor);
                                }
                            });
                        }
                    });
                }
            });
            t1.start();
        }
    }
}

package com.example.rpsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView handAnimationImage;
    AnimationDrawable animationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handAnimationImage = findViewById(R.id.hand_animation_image);

        animationDrawable = (AnimationDrawable) handAnimationImage.getDrawable();

        animationDrawable.start();
    }


    public void button_click(View view) {
    }
}

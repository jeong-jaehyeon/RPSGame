package com.example.rpsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.os.IResultReceiver;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView handAnimationImage;
    ImageView computerAnimationImage;
    AnimationDrawable animationDrawable;

    TextToSpeech textToSpeech;

    TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int i) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handAnimationImage = findViewById(R.id.hand_animation_image);
        computerAnimationImage = findViewById(R.id.computer_animation_image);

        //GONE = 아예 없애는 기능.
        //VISIBLE = 보이게 하는 기능.
        //INVISIBLE = 있지만 보이게 하지 않게하는 기능.


//        첫시작을 computer 이미지로 해야하니 GONE을 써줌.
        handAnimationImage.setVisibility(View.GONE);
//        명확하게 표현해줌.
        computerAnimationImage.setVisibility(View.VISIBLE);


        animationDrawable = (AnimationDrawable) handAnimationImage.getDrawable();

//        animationDrawable.start();
    }


    public void button_click(View view) {
        switch (view.getId()) {

            case R.id.reButton:
                computerAnimationImage.setVisibility(View.GONE);
                handAnimationImage.setVisibility(View.VISIBLE);
                animationDrawable.start();
                break;

//            case R.id.gaweButton:
//
//                break;
//            case R.id.baweButton:
//
//                break;
//            case R.id.boButton:
//
//                break;

            default:
                animationDrawable.stop();
                handAnimationImage.setVisibility(View.GONE);
                computerAnimationImage.setVisibility(View.VISIBLE);
                computerAnimationImage.setImageResource(R.drawable.com_gawe);
                break;
        }
    }
}

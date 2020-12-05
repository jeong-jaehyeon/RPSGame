package com.example.rpsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.os.IResultReceiver;
import android.view.View;
import android.widget.ImageView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView computerAnimationImage;
    ImageView handAnimationImage;
    ImageView gaweButton;
    ImageView boButton;
    ImageView baweButton;
    ImageView replayButton;

    //가위바위보 0.2 초 간격의 애니메이션
    AnimationDrawable animationDrawable;

    // 가위바위보 음성
    TextToSpeech textToSpeech;
    TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int i) {
            if (i != TextToSpeech.ERROR) {
                //음성의 언어
                textToSpeech.setLanguage(Locale.KOREA);
                //음성의 높낮이
                textToSpeech.setPitch(1.0f);
                //음성의 속도
                textToSpeech.setSpeechRate(1.0f);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        computerAnimationImage = findViewById(R.id.computer_animation_image);
        handAnimationImage = findViewById(R.id.hand_animation_image);

        //GONE = 아예 없애는 기능.
        //VISIBLE = 보이게 하는 기능.
        //INVISIBLE = 있지만 보이게 하지 않게하는 기능.

        // Activity main의 이미지뷰를 바꿈으로써 코드 삭제 처리.
//        첫시작을 computer 이미지로 해야하니 GONE을 써줌.
//        handAnimationImage.setVisibility(View.GONE);
//        명확하게 표현해줌.
//        computerAnimationImage.setVisibility(View.VISIBLE);

        //각 버튼 실행시 활성화, 비활성화를 위한 선언.
        gaweButton = findViewById(R.id.gaweButton);
        boButton = findViewById(R.id.boButton);
        baweButton = findViewById(R.id.baweButton);
        replayButton = findViewById(R.id.reButton);

        animationDrawable = (AnimationDrawable) handAnimationImage.getDrawable();
        textToSpeech = new TextToSpeech(getApplicationContext(), onInitListener);

    }


    public void button_click(View view) {
        switch (view.getId()) {

            case R.id.reButton:
                computerAnimationImage.setVisibility(View.GONE);
                handAnimationImage.setVisibility(View.VISIBLE);
                animationDrawable.start();

                //https://developer.android.com/reference/android/speech/tts/TextToSpeech#speak(java.lang.CharSequence,%20int,%20android.os.Bundle,%20java.lang.String)
                voicePlay("가위바위보");
                replayButton.setEnabled(false);
                gaweButton.setEnabled(true);
                baweButton.setEnabled(true);
                boButton.setEnabled(true);
                break;

            case R.id.gaweButton:
            case R.id.baweButton:
            case R.id.boButton:
                replayButton.setEnabled(true);
                gaweButton.setEnabled(false);
                baweButton.setEnabled(false);
                boButton.setEnabled(false);

                animationDrawable.stop();
                handAnimationImage.setVisibility(View.GONE);
                computerAnimationImage.setVisibility(View.VISIBLE);

                //승자 체크
                winCheck(setUserHand(view), setComHand());
                break;

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //speech 리소스 해제
        textToSpeech.shutdown();
    }

    public void voicePlay(String voiceText) {
        textToSpeech.speak(voiceText, TextToSpeech.QUEUE_FLUSH, null, null);

    }

    public int setUserHand(View view) {
        int getUserHand = Integer.parseInt(view.getTag().toString());
        return getUserHand;
    }

    public int setComHand() {
        int getComHand = new Random().nextInt(3) + 1;
        switch (getComHand) {
            case 1:
                computerAnimationImage.setImageResource(R.drawable.com_gawe);
                break;
            case 2:
                computerAnimationImage.setImageResource(R.drawable.com_bawe);
                break;
            case 3:
                computerAnimationImage.setImageResource(R.drawable.com_bo);
                break;
        }
        return getComHand;
    }

    public void winCheck(int userHand, int comHand) {
        // 가위바위보 알고리즘 적용
        int result = (3 + userHand - comHand) % 3;

        switch (result) {
            case 0:
                voicePlay("무승부");
                break;
            case 1:
                voicePlay("유저 승리");
                break;
            case 2:
                voicePlay("컴퓨터 승리");
                break;
        }
    }
}

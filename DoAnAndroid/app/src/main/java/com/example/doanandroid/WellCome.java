package com.example.doanandroid;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import 	android.util.Pair;
import androidx.appcompat.app.AppCompatActivity;

public class WellCome extends AppCompatActivity {
    ImageView image;
    TextView logo;
    Animation topAnim, bottomAnim;
    private static int SPLASH_SCREEN = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activiti_wellcome);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.logohinhanh);
        logo = findViewById(R.id.logochu);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
ChuyenActivity();

    }

    private void ChuyenActivity() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WellCome.this,DangNhapActivity.class);

                Pair[]pairs=new Pair[2];
                pairs[0]=new Pair<View, String>(image,"logo_image");
                pairs[1]=new Pair<View, String>(logo,"logo_text");
                if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.LOLLIPOP)
                {
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(WellCome.this,pairs);
                    startActivity(intent,options.toBundle());

                }
                finish();
            }
        }, SPLASH_SCREEN);
    }

}
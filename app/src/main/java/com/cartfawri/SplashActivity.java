package com.cartfawri;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    private RelativeLayout bgViewGroup;
    private int i;
    private float finalRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        bgViewGroup = (RelativeLayout) findViewById(R.id.reveal_root);

        bgViewGroup.post(new Runnable() {
            @Override
            public void run() {
                animateRevealColor(bgViewGroup, R.color.white);
            }
        });




//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashActivity.this , MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                finish();
//            }
//        } , 2000);

    }







    private void animateRevealColor(ViewGroup viewRoot, @ColorRes int color) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        animateRevealColorFromCoordinates(viewRoot, color, cx, cy , finalRadius , 100 , 1000);
    }

    private Animator animateRevealColorFromCoordinates(final ViewGroup viewRoot, @ColorRes final int color, final int x, final int y , float from , float to , int duration) {
        i++;
        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y,  from , to);
        viewRoot.setBackgroundColor(ContextCompat.getColor(this, color));
        anim.setDuration(duration);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.d(TAG, "onAnimationEnd: ");
                switch (i){
                    case 1:
                        Log.d(TAG, "onAnimationEnd: loading 1");
                        animateRevealColorFromCoordinates(viewRoot , color , x , y , 20 ,200 , 1000);
                        break;
                    case 2:
                        Log.d(TAG, "onAnimationEnd: loading 2");
                        animateRevealColorFromCoordinates(viewRoot , color , x , y , 200 ,20 , 1000);
                        break;

                    case 3:
                        Log.d(TAG, "onAnimationEnd: loading 3");
                        animateRevealColorFromCoordinates(viewRoot , color , x , y , 100 ,finalRadius , 1000);

                        startMainActivity();
                        break;

                    case 4:

                        break;


                }
            }
        });
        anim.start();
        return anim;
    }

    private void startMainActivity() {
        Intent intent = new Intent(SplashActivity.this , MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}

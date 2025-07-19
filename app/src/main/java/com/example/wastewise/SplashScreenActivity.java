package com.example.wastewise;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView basketImage;
    private TextView txvLogo;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initViews();
        startAnimation();

        handler.postDelayed(() -> {
            Intent intent = new Intent(this, LandingPageActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }

    private void initViews() {
        basketImage = findViewById(R.id.basketImage);
        txvLogo = findViewById(R.id.txvLogo);
    }

    private void startAnimation() {
        animateBasketAppearance();
        handler.postDelayed(this::animateTextAppear, 1000);
    }

    private void animateBasketAppearance() {
        // Fade in
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(basketImage, "alpha", 0f, 1f);
        fadeIn.setDuration(600);

        // Mengubah ukuran keranjang dengan sedikit bounce
        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(basketImage, "scaleX", 0.5f, 1.2f, 1f);
        scaleUpX.setDuration(800);
        scaleUpX.setInterpolator(new BounceInterpolator());

        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(basketImage, "scaleY", 0.5f, 1.2f, 1f);
        scaleUpY.setDuration(800);
        scaleUpY.setInterpolator(new BounceInterpolator());

        // Gabungkan animasi
        AnimatorSet basketAppearSet = new AnimatorSet();
        basketAppearSet.playTogether(fadeIn, scaleUpX, scaleUpY);
        basketAppearSet.start();
    }

    private void animateTextAppear() {
        ObjectAnimator textSlideIn = ObjectAnimator.ofFloat(txvLogo, "translationX", 50f, 0f);
        textSlideIn.setDuration(800);
        textSlideIn.setInterpolator(new AccelerateDecelerateInterpolator());

        // Teks fade in
        ObjectAnimator textFadeIn = ObjectAnimator.ofFloat(txvLogo, "alpha", 0f, 1f);
        textFadeIn.setDuration(600);

        // Mengubah ukuran teks
        ObjectAnimator textScaleX = ObjectAnimator.ofFloat(txvLogo, "scaleX", 0.8f, 1.1f, 1f);
        textScaleX.setDuration(600);
        textScaleX.setInterpolator(new BounceInterpolator());

        ObjectAnimator textScaleY = ObjectAnimator.ofFloat(txvLogo, "scaleY", 0.8f, 1.1f, 1f);
        textScaleY.setDuration(600);
        textScaleY.setInterpolator(new BounceInterpolator());

        // Gabungkan animasi
        AnimatorSet textAppearSet = new AnimatorSet();
        textAppearSet.playTogether(textSlideIn, textFadeIn, textScaleX, textScaleY);
        textAppearSet.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
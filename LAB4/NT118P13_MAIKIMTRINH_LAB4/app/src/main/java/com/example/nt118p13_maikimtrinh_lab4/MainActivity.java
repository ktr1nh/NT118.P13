package com.example.nt118p13_maikimtrinh_lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView ivUitLogo;
    // XML buttons
    private Button btnFadeInXml, btnFadeOutXml, btnBlinkXml, btnZoomInXml, btnZoomOutXml;
    private Button btnRotateXml, btnMoveXml, btnSlideUpXml, btnBounceXml, btnCombineXml;
    // Code buttons
    private Button btnFadeInCode, btnFadeOutCode, btnBlinkCode, btnZoomInCode, btnZoomOutCode;
    private Button btnRotateCode, btnMoveCode, btnSlideUpCode, btnBounceCode, btnCombineCode;

    private Animation.AnimationListener animationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsByIds();
        initVariables();
        handleClickAnimationXml(btnFadeInXml, R.anim.anim_fade_in);
        handleClickAnimationXml(btnFadeOutXml, R.anim.anim_fade_out);
        handleClickAnimationXml(btnBlinkXml, R.anim.anim_blink);
        handleClickAnimationXml(btnZoomInXml, R.anim.anim_zoom_in);
        handleClickAnimationXml(btnZoomOutXml, R.anim.anim_zoom_out);
        handleClickAnimationXml(btnRotateXml, R.anim.anim_rotate);
        handleClickAnimationXml(btnMoveXml, R.anim.anim_move);
        handleClickAnimationXml(btnSlideUpXml, R.anim.anim_slide_up);
        handleClickAnimationXml(btnBounceXml, R.anim.anim_bounce);
        handleClickAnimationXml(btnCombineXml, R.anim.anim_combine);
        handleClickAnimationCode(btnFadeInCode, initFadeInAnimation());
        handleClickAnimationCode(btnFadeOutCode, initFadeOutAnimation());
        handleClickAnimationCode(btnBlinkCode, initBlinkAnimation());
        handleClickAnimationCode(btnZoomInCode, initZoomInAnimation());
        handleClickAnimationCode(btnZoomOutCode, initZoomOutAnimation());
        handleClickAnimationCode(btnRotateCode, initRotateAnimation());
        handleClickAnimationCode(btnMoveCode, initMoveAnimation());
        handleClickAnimationCode(btnSlideUpCode, initSlideUpAnimation());
        handleClickAnimationCode(btnBounceCode, initBounceAnimation());
        handleClickAnimationCode(btnCombineCode, initCombineAnimation());

        ivUitLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy animation và chạy nó
                Animation animation = getNewActivityAnimation();
                ivUitLogo.startAnimation(animation);

                // Thiết lập chuyển cảnh giữa các Activity
                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);

                // Chuyển sang SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    private Animation getNewActivityAnimation() {
        // Ví dụ, trả về một hoạt ảnh fade-in
        return AnimationUtils.loadAnimation(this, R.anim.anim_fade_in);
    }

    private void findViewsByIds() {
        ivUitLogo = findViewById(R.id.iv_uit_logo);
        ivUitLogo = (ImageView) findViewById(R.id.iv_uit_logo);
        btnFadeInXml = (Button) findViewById(R.id.btn_fade_in_xml);
        btnFadeInCode = (Button) findViewById(R.id.btn_fade_in_code);
        btnFadeOutXml = (Button) findViewById(R.id.btn_fade_out_xml);
        btnFadeOutCode = (Button) findViewById(R.id.btn_fade_out_code);
        btnBlinkXml = (Button) findViewById(R.id.btn_blink_xml);
        btnBlinkCode = (Button) findViewById(R.id.btn_blink_code);
        btnZoomInXml = (Button) findViewById(R.id.btn_zoom_in_xml);
        btnZoomInCode = (Button) findViewById(R.id.btn_zoom_in_code);
        btnZoomOutXml = (Button) findViewById(R.id.btn_zoom_out_xml);
        btnZoomOutCode = (Button) findViewById(R.id.btn_zoom_out_code);
        btnRotateXml = (Button) findViewById(R.id.btn_rotate_xml);
        btnRotateCode = (Button) findViewById(R.id.btn_rotate_code);
        btnMoveXml = (Button) findViewById(R.id.btn_move_xml);
        btnMoveCode = (Button) findViewById(R.id.btn_move_code);
        btnSlideUpXml = (Button) findViewById(R.id.btn_slide_up_xml);
        btnSlideUpCode = (Button) findViewById(R.id.btn_slide_up_code);
        btnBounceXml = (Button) findViewById(R.id.btn_bounce_xml);
        btnBounceCode = (Button) findViewById(R.id.btn_bounce_code);
        btnCombineXml = (Button) findViewById(R.id.btn_combine_xml);
        btnCombineCode = (Button) findViewById(R.id.btn_combine_code);
    }

    private void initVariables() {
        // Initialize animation listener and override methods
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Handle when animation starts (optional)
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Display a Toast when animation ends
                Toast.makeText(getApplicationContext(), "Animation Stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Handle when animation repeats (optional)
            }
        };
    }

    // Method to handle button clicks and apply XML animations
    private void handleClickAnimationXml(Button btn, int animId) {
        final Animation animation = AnimationUtils.loadAnimation(this, animId);
        animation.setAnimationListener(animationListener);  // Set listener for animation

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(animation);  // Apply animation to iv_uit_logo
            }
        });
    }

    // Method to handle button clicks and apply code animations
    private void handleClickAnimationCode(Button btn, Animation animation) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(animation);
            }
        });
    }

    // Initialize FadeIn animation
    private Animation initFadeInAnimation() {
        AlphaAnimation alphaanimation = new AlphaAnimation(0.0f, 1.0f);
        alphaanimation.setDuration(1000);
        alphaanimation.setFillAfter(true);
        return alphaanimation;
    }

    // Initialize FadeOut animation
    private Animation initFadeOutAnimation() {
        AlphaAnimation alphaanimation = new AlphaAnimation(1.0f, 0.0f);
        alphaanimation.setDuration(1000);
        alphaanimation.setFillAfter(true);
        return alphaanimation;
    }

    // Initialize Blink animation
    private Animation initBlinkAnimation() {
        AlphaAnimation alphaanimation = new AlphaAnimation(0.0f, 1.0f);
        alphaanimation.setDuration(1000);
        alphaanimation.setRepeatCount(3);
        alphaanimation.setRepeatMode(Animation.REVERSE);
        alphaanimation.setFillAfter(true);
        return alphaanimation;
    }

    // Initialize Rotate animation
    private Animation initRotateAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, // pivotX
                Animation.RELATIVE_TO_SELF, 0.5f  // pivotY
        );
        rotateAnimation.setDuration(600);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setFillAfter(true);
        return rotateAnimation;
    }

    // Initialize ZoomIn animation
    private Animation initZoomInAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 3f,
                1f, 3f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    // Initialize ZoomOut animation
    private Animation initZoomOutAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 0.25f,
                1f, 0.25f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    // Initialize Move animation
    private Animation initMoveAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0.75f,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0
        );
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

    // Initialize SlideUp animation
    private Animation initSlideUpAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 1f,
                1.0f, 0.0f
        );
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    // Initialize Bounce animation
    private Animation initBounceAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 1f,
                0.0f, 1.0f
        );
        scaleAnimation.setDuration(1000);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    // Initialize Combine animations
    private Animation initCombineAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(initZoomInAnimation());
        animationSet.addAnimation(initRotateAnimation());
        animationSet.setDuration(1000);
        return animationSet;
    }
}

package com.restaurant.customhorizontalviewpager.module.commonUtils;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.restaurant.customhorizontalviewpager.R;

public class DialogBaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performActivityEntryAnimation();
    }

    private void performActivityExitAnimation() {
        overridePendingTransition(0, R.anim.popup_slide_out);
    }

    private void performActivityEntryAnimation() {
        overridePendingTransition(R.anim.popup_slide_in, 0);
    }

    @Override
    public void finish() {
        super.finish();
        performActivityExitAnimation();
    }


}
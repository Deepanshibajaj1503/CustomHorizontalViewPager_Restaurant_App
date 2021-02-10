package com.restaurant.customhorizontalviewpager.module.commonUtils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.restaurant.customhorizontalviewpager.R;


public class CustomProgressBar extends Dialog {

    private static CustomProgressBar CustomProgressBar;

    private String mMessage;

    public CustomProgressBar(Context context) {
        super(context);
        mMessage = null;
        createProgressBar(context);
    }

    public CustomProgressBar(Context context, String message) {
        super(context);
        mMessage = message;
    }

    public synchronized static CustomProgressBar getProgressbar(Context context) {
        if (null == CustomProgressBar) {
            CustomProgressBar = new CustomProgressBar(context);
        }
        return CustomProgressBar;
    }

    public static CustomProgressBar getProgressbar(Context context, String message) {
        if (null == CustomProgressBar) {
            CustomProgressBar = new CustomProgressBar(context, message);
        }
        return CustomProgressBar;
    }

    private void createProgressBar(Context context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getAppProgressbar(context));
        Window window = getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    private View getAppProgressbar(Context context) {
        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setBackgroundResource(android.R.color.transparent);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
        if (null != mMessage) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.addView(progressBar);
            TextView messageTextView = new TextView(context);
            messageTextView.setText(mMessage);
            messageTextView.setGravity(Gravity.CENTER_VERTICAL);
            messageTextView.setTypeface(Typeface.DEFAULT_BOLD);
            //messageTextView.setTextColor(context.getResources().getColor(R.color.theme_yellow));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            messageTextView.setLayoutParams(layoutParams);
            layoutParams.setMargins(10, 0, 0, 0);
            linearLayout.addView(messageTextView);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            return linearLayout;
        }
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        return progressBar;
    }

    public boolean dismissProgress() {
        boolean dismissed = false;
        try {
            super.dismiss();
            dismissed = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        CustomProgressBar = null;
        return dismissed;
    }
}
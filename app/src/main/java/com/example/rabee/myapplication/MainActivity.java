package com.example.rabee.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextInputLayout tilPassword;
    private Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        sw = (Switch) findViewById(R.id.sw);
        /**
         * switch on: apply a new CustomTextInputLayoutAccessibilityDelegate() class with
         * the code that removes the hint inside onInitializeAccessibilityNodeInfo().
         * switch off: apply a new CustomTextInputLayoutAccessibilityDelegate() class with
         * only super() inside onInitializeAccessibilityNodeInfo().
         */
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                tilPassword.setTextInputAccessibilityDelegate(new CustomTextInputLayoutAccessibilityDelegate(tilPassword, b));
            }
        });
        sw.setChecked(true);
     }

    private class CustomTextInputLayoutAccessibilityDelegate extends TextInputLayout.AccessibilityDelegate{

        private final TextInputLayout layout;
        private final boolean flag;

        public CustomTextInputLayoutAccessibilityDelegate(TextInputLayout layout, boolean flag) {
            super(layout);
            this.layout = layout;
            this.flag = flag;
        }

        @Override
        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            if(flag) {
                EditText editText = layout.getEditText();
                CharSequence text = (editText != null) ? editText.getText() : null;
                CharSequence hintText = layout.getHint();
                CharSequence errorText = layout.getError();
                //CharSequence counterDesc = layout.getCounterOverflowDescription();
                boolean showingText = !TextUtils.isEmpty(text);
                boolean hasHint = !TextUtils.isEmpty(hintText);
                //boolean showingError = !TextUtils.isEmpty(errorText);
                //boolean contentInvalid = showingError || !TextUtils.isEmpty(counterDesc);

                if (showingText) {
                    info.setText(text);
                } else if (hasHint) {
                    info.setText("");
                }

                if (hasHint) {
                    info.setHintText("");
                    info.setShowingHintText(!showingText && hasHint);
                }

                //if (contentInvalid) {
                //    info.setError(showingError ? errorText : counterDesc);
                //    info.setContentInvalid(true);
                //}
            }
        }
    }

}

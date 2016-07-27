package com.kakao.rxeample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;
    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = ((TextView) findViewById(R.id.textView));
        textView2 = ((TextView) findViewById(R.id.textView2));
        textView3 = ((TextView) findViewById(R.id.textView3));

        new Chapter1().doRXTest(textView);
        new Chapter2(textView2, textView3);
    }
}

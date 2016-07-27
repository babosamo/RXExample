package com.kakao.rxeample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private Button button;

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = ((TextView) findViewById(R.id.textView));
        textView2 = ((TextView) findViewById(R.id.textView2));
        textView3 = ((TextView) findViewById(R.id.textView3));
        textView4 = ((TextView) findViewById(R.id.textView4));
        button = ((Button) findViewById(R.id.button));

        new Chapter1().doRXTest(textView);
        new Chapter2(textView2, textView3);
        Chapter3 chapter3 = new Chapter3(button, textView4);
        chapter3.mergeTest(this, (Button)findViewById(R.id.leftButton), (Button)findViewById(R.id.rightButton), (TextView)findViewById(R.id.mergeView));
        chapter3.scanTest((Button)findViewById(R.id.minusButton), (Button)findViewById(R.id.plusButton), (TextView)findViewById(R.id.count), (TextView)findViewById(R.id.number));


    }
}

package com.kakao.rxeample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> simpleObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxAndroid !!");
                subscriber.onCompleted();
            }
        });

        simpleObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "complete!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "error: " + e.getMessage());
            }

            @Override
            public void onNext(String text) {
                ((TextView) findViewById(R.id.textView)).setText(text);
            }
        });
    }
}

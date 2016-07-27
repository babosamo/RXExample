package com.kakao.rxeample;

import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Chatper1 on 2016. 7. 26..
 */
public class Chapter1 {

    private final String TAG = Chapter1.class.getSimpleName();

    public void doRXTest(final TextView textView){

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
                textView.setText(text);
            }
        });

        simpleObservable
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String text) {
                        Log.d(TAG, "textView set Text: "  + text);
                        textView.setText(text);
                    }
                });

        simpleObservable
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String text) {
                        Log.d(TAG, "textView2 set Text: "  + text);
                        textView.setText(text);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }, new Action0() {
                    @Override
                    public void call() {

                    }
                });

        simpleObservable
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String text) {
                        Log.d(TAG, "textView3 set Text: "  + text);
                        textView.setText(text);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }

}

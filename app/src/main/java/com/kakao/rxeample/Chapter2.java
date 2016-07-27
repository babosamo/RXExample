package com.kakao.rxeample;

import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Chapter2 on 2016. 7. 27..
 */
public class Chapter2 {

    private final String TAG = Chapter2.class.getSimpleName();

    public Chapter2(TextView textView2, TextView textView3) {

        doMapTest1();
        doMapTest2(textView2);
        lambdaTest(textView3);
    }

    public void doMapTest1() {
        Observable<Integer> simpleObservable = Observable.just(1);
        simpleObservable.map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer value) {
                return value * 10;
            }
        });
    }

    public void doMapTest2(final TextView textView) {

        Log.d(TAG, "doMapTest2");
        Observable<String> simpleObservable = Observable.just("Hello world");
        simpleObservable.map(new Func1<String, String>() {
            @Override
            public String call(String text) {
                Log.d(TAG, "map: " + text);
                return text.toUpperCase();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String text) {
                Log.d(TAG, "call : " + text);
                textView.setText(text);
            }
        });
    }

    public void doMapTest2_2(final TextView textView) {

        Log.d(TAG, "doMapTest2_2");
        Observable<String> simpleObservable = Observable.just("Hello world");
        simpleObservable.map(new Func1<String, String>() {
            @Override
            public String call(String text) {
                Log.d(TAG, "map: " + text);
                return text.toUpperCase();
            }
        });

        // 이렇게 하면 map 이 호출되지 않는다.
        simpleObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String text) {
                Log.d(TAG, "call : " + text);
                textView.setText(text);
            }
        });
    }

    public void doMapTest3(final TextView textView) {

        Log.d(TAG, "doMapTest3");
        Observable<String> simpleObservable = Observable.just("Hello world");
        simpleObservable
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String text) {
                        return text.length();
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer length) {
                        textView.setText("length: " + length);
                    }
                });
    }


    public void lambdaTest(final TextView textView){
        Log.d(TAG, "lambdaTest");
        Observable<String> simpleObservable = Observable.just("Hello Lambda!!");
        simpleObservable
                .map(text -> text.length())
                .subscribe(length -> textView.setText("length: " + length));
    }
}

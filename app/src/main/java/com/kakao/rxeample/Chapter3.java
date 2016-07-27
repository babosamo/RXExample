package com.kakao.rxeample;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Random;

import rx.Observable;

/**
 * Created by Chapter3 on 2016. 7. 27..
 */
public class Chapter3 {

    private final String TAG = Chapter3.class.getSimpleName();


    public Chapter3(Button button, TextView textView) {
        doRXClick(button, textView);
//        doRxClick2(button);
    }

    public void doRXClick(final Button button, final TextView textView) {
        RxView.clicks(button)
                .map(event -> new Random().nextInt())
                .subscribe(value -> {
                    textView.setText("number: " + value.toString());
                }, throwable -> {
                    Log.e(TAG, "Error: " + throwable.getMessage());
                    throwable.printStackTrace();
                });
    }

    private void doRxClick2(final Button button){
        // subscribe 는 1회만 되는 것일까?
        Observable<Void> click = RxView.clicks(button);
        click.subscribe(value -> Log.v(TAG, "click1"));
        click.subscribe(value -> Log.v(TAG, "click2"));

    }

    public void mergeTest(Context context, Button leftButton, Button rightButton, TextView mergeText) {

        Observable<String> lefts = RxView.clicks(leftButton).map(event -> "left");
        Observable<String> rights = RxView.clicks(rightButton).map(event -> "right");

        Observable<String> together = Observable.merge(lefts, rights);

        together.subscribe((String text) -> {
            Log.v(TAG, "subscribe text: text");
            (mergeText).setText(text);
        });

        together.subscribe((String text) -> {
            Log.v(TAG, "# babosamo");
            (mergeText).setText(text);
        });


        // subscribe 가 동시에는 안되는 것일까 ?

//        together.map(text -> text.toUpperCase()).subscribe(text -> Toast.makeText(context, text, Toast.LENGTH_SHORT).show());
//        together.subscribe(text -> Toast.makeText(context, text, Toast.LENGTH_SHORT).show());
    }

    public void scanTest(Button minusButton, Button plusButton, TextView countText, TextView numberText) {

        Observable<Integer> minuses = RxView.clicks(minusButton).map(event -> -1);
        Observable<Integer> pluses = RxView.clicks(plusButton).map(event -> 1);
        Observable<Integer> together = Observable.merge(minuses, pluses);
        together.scan(0, (sum, number) -> sum + 1).subscribe(count -> countText.setText(count.toString()));
        // 동시에 2번 스캔은 안되는 것인가?


        together.scan(0, (sum, number) -> sum + number).subscribe(number -> numberText.setText(number.toString()));
    }

    public void combineLatest(CheckBox checkBox1, EditText editText1, CheckBox checkBox2, EditText editText2, CheckBox checkBox3, EditText editText3, Button button){

        /*
        Observable<Boolean> checks1 = RxCompoundButton.checkedChanges(checkBox1);
        checks1.subscribe(check -> editText1.setEnabled(check));

        Observable<Boolean> textExists1 = RxTextView.text(editText1).map(Chapter3::isEmpty);
        Observable<Boolean> textExists1 = RxTextView.text(editText1)..map(Chapter3::isEmpty);

        Observable<Boolean> textValidations1 = Observable.combineLatest(checks1, textExists1, (check, exist) -> !check || exist);

        Observable<Boolean> checks2 = RxCompoundButton.checkedChanges(checkBox2);

        checks2.subscribe(check -> editText2.setEnabled(check));

        Observable<Boolean> textExists2 = RxTextView.text(editText2).map(Chapter3::isEmpty);

        Observable<Boolean> textValidations2 = Observable
                .combineLatest(checks2, textExists2, (check, exist) -> !check || exist);

        Observable<Boolean> checks3 = RxCompoundButton.checkedChanges(checkBox3);

        checks3.subscribe(check -> editText3.setEnabled(check));

        Observable<Boolean> textExists3 = RxTextView.text(editText3).map(Chapter3::isEmpty);

        Observable<Boolean> textValidations3 = Observable
                .combineLatest(checks3, textExists3, (check, exist) -> !check || exist);


        Observable.combineLatest(textValidations1, textValidations2, textValidations3,
                (validation1, validation2, validation3) ->
                        validation1 && validation2 && validation3)
                .subscribe(validation -> button.setEnabled(validation));

*/
    }

    public static boolean isEmpty(CharSequence sequence) {
        return sequence.length() != 0;
    }
}

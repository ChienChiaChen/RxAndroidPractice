package com.example.chiachen.rxandroidpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;


/**
 * Created by chiachen on 2017/6/24.
 */

public class SimpleActivity extends AppCompatActivity {
	private static final String TAG = "SimpleActivity";
	@BindView(R.id.title_simple) TextView mTitle;

	final String mManyWords[] = {"Tommy", "jason", "bear", "jimmy", "weting", "wade"};
	final List<String> mManyWordsList = Arrays.asList(mManyWords);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple);
		ButterKnife.bind(this);
		//============
		rx.Observable<String> obShow = rx.Observable.just(getName());
		obShow.observeOn(AndroidSchedulers.mainThread()).
				map(String::toUpperCase).
				subscribe(mLogAction);

		//============
		rx.Observable<String> obMap = rx.Observable.from(mManyWords);
		obMap.observeOn(AndroidSchedulers.mainThread()).
				map(String::toUpperCase).
				subscribe(mTitle::setText);

		//============
		rx.Observable.just(mManyWordsList)
				.observeOn(AndroidSchedulers.mainThread())
				.flatMap(Observable::from)
				.reduce(this::mergeStrings)
				.subscribe(this::showToast);
		//============
	}

	public String getName(){
		return "im jason";
	}

	private String mergeStrings(String s, String s2) {
		return String.format("%s %s", s, s2);
	}

	private void showToast(String string){
		Toast.makeText(SimpleActivity.this, string, Toast.LENGTH_SHORT).show();
	}

	private Action1<String> mLogAction = string -> {
		Log.e(TAG, "eeeeeee  "+string);
	};
}


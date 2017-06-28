package com.example.chiachen.rxandroidpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * Created by chiachen on 2017/6/24.
 */

public class SimpleActivity extends AppCompatActivity {
	private static final String TAG = "SimpleActivity";
	@BindView(R.id.title_simple) TextView mTitle;

	final String mManyWords[] = {"Tommy", "jason", "bear", "jimmy", "weting", "wade"};
	final ArrayList<String> mManyWordsList = new ArrayList<>(Arrays.asList(mManyWords));

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple);
		ButterKnife.bind(this);
		//============
		rx.Observable<String> obShow = rx.Observable.just(getName());
		obShow.observeOn(AndroidSchedulers.mainThread()).
				map(mUpperLetterFunc).
				subscribe(mToastAction);
		//============

		rx.Observable<String> obMap = rx.Observable.from(mManyWords);
		obMap.observeOn(AndroidSchedulers.mainThread()).
				map(mUpperLetterFunc).
				subscribe(mLogAction);

	}

	public String getName(){
		return "im jason";
	}

	// string -> upper case
	private Func1<String, String> mUpperLetterFunc = string -> {
		return string.toUpperCase(); // 大小字母
	};

	// Shoe toast
	private Action1<String> mToastAction = string -> {
		Toast.makeText(SimpleActivity.this, string, Toast.LENGTH_SHORT).show();
	};

	private Action1<String> mChangeTextAction = s -> {
		mTitle.setText(s);
	};

	private Action1<String> mLogAction = string -> {
		Log.e(TAG, "eeeeeee  "+string);
	};
}


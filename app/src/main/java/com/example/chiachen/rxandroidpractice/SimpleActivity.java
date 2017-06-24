package com.example.chiachen.rxandroidpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rx.Observable;
import rx.Subscriber;


/**
 * Created by chiachen on 2017/6/24.
 */

public class SimpleActivity extends AppCompatActivity {
	Observable.OnSubscribe mObservableAction = new Observable.OnSubscribe<String>() {
		@Override public void call(Subscriber<? super String> subscriber) {
			subscriber.onNext(sayMyName()); // 发送事件
			subscriber.onCompleted(); // 完成事件
		}
	};

	private String sayMyName() {
		return "Hello, I am your friend, Spike!";
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple);
	}
}


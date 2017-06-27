package com.example.chiachen.rxandroidpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;


public class MainActivity extends AppCompatActivity {
	public static final String TAG = "mainActivity";
	@BindView(R.id.title) TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		// 注册观察活动
		@SuppressWarnings("unchecked")
		Observable<String> observable = Observable.create(mObservableAction);

		// 分发订阅信息
		// observable.observeOn(AndroidSchedulers.mainThread());
		observable.subscribe(mLogSubscriber);
		observable.subscribe(mToastSubscriber);// 注册观察活动
	}

	Observable.OnSubscribe mObservableAction = new Observable.OnSubscribe<String>() {
		@Override
		public void call(Subscriber<? super String> subscriber) {
			subscriber.onNext(sayMyName()); // 发送事件
			subscriber.onCompleted(); // 完成事件
		}
	};

	private String sayMyName() {
		return "Hello, I am your friend, Spike!";
	}

	Subscriber<String> mToastSubscriber = new Subscriber<String>() {
		@Override
		public void onCompleted() {

		}

		@Override
		public void onError(Throwable e) {

		}

		@Override
		public void onNext(String s) {
			Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
		}
	};

	Subscriber<String> mLogSubscriber = new Subscriber<String>() {
		@Override
		public void onCompleted() {


		}

		@Override
		public void onError(Throwable e) {

		}

		@Override
		public void onNext(String s) {
			Log.e(TAG, s);
		}
	};

}

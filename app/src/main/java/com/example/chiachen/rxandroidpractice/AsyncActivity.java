package com.example.chiachen.rxandroidpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.chiachen.rxandroidpractice.util.Tool;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jason_Chien on 2017/7/7.
 */

public class AsyncActivity extends AppCompatActivity {
	@BindView (R.id.main_rx)
	TextView mRxBtn;
	Observable<String> observable;
	Observable.OnSubscribe<String> onSubscribe = new Observable.OnSubscribe<String>() {
		@Override
		public void call(Subscriber<? super String> subscriber) {
			subscriber.onNext(longRunningOperation());
			subscriber.onCompleted();
		}
	};
	
	Subscriber<String> subscriber = new Subscriber<String>() {
		@Override
		public void onCompleted() {
			mRxBtn.setEnabled(true);
		}
		
		@Override
		public void onError(Throwable e) {
			
		}
		
		@Override
		public void onNext(String s) {
			Tool.tag(s);
		}
	};
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asynk);
		ButterKnife.bind(this);
		
		observable = Observable.create(onSubscribe).
				subscribeOn(Schedulers.io()).
				observeOn(AndroidSchedulers.mainThread());
		
	}
	
	@OnClick (R.id.main_rx)
	public void gogo(View view) {
		switch (view.getId()) {
			case R.id.main_rx: {
				// It's Practice for async
				// mRxBtn.setEnabled(false);
				// observable.subscribe(subscriber);
				
				createObserver().subscribe(new Subscriber<Integer>() {
					@Override
					public void onCompleted() {
						Tool.tag("onCompleted");
					}
					
					@Override
					public void onError(Throwable e) {
						Tool.tag("onError  "+e.toString());
					}
					
					@Override
					public void onNext(Integer integer) {
						Tool.tag("onNext "+integer);
					}
				});
			}
		}
	}
	
	private String longRunningOperation() {
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			Log.e("DEBUG", e.toString());
		}
		return "Complete!";
	}
	
	private Observable<Integer> createObserver() {
		return Observable.create(new Observable.OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				if (subscriber.isUnsubscribed())
					return;
				
				for (int i = 0; i < 5; i++) {
					int temp = new Random().nextInt(6);
					if (temp > 4)
						subscriber.onError(new Throwable(temp + "  >4"));
					else
						subscriber.onNext(temp);
					if (i == 4)
						subscriber.onCompleted();
				}
			}
		});
	}
}

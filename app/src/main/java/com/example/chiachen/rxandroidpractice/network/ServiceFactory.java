package com.example.chiachen.rxandroidpractice.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by chiachen on 2017/6/29.
 */

public class ServiceFactory {
	public static <T> T createServiceFrom(final Class<T> serviceClass, String endpoint) {
		Retrofit adapter = new Retrofit.Builder()
				.baseUrl(endpoint)
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
				.addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
				.build();
		return adapter.create(serviceClass);
	}
}

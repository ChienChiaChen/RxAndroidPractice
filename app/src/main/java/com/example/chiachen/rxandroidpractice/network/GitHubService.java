package com.example.chiachen.rxandroidpractice.network;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by chiachen on 2017/6/29.
 */

public interface GitHubService {
	String ENDPOINT = "https://api.github.com";

	// 获取个人信息
	@GET("/users/{user}")
	Observable<UserListAdapter.GitHubUser> getUserData(@Path("user") String user);

	// 获取库, 获取的是数组
	@GET("/users/{user}/repos")
	Observable<RepoListAdapter.GitHubRepo[]> getRepoData(@Path("user") String user);

}

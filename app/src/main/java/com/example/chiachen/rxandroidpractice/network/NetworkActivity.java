package com.example.chiachen.rxandroidpractice.network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chiachen.rxandroidpractice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chiachen on 2017/6/29.
 */

public class NetworkActivity extends AppCompatActivity {
	@BindView(R.id.network_rv_list) RecyclerView mRvList; // 列表

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_network);
		ButterKnife.bind(this);

		// 设置Layout管理器
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRvList.setLayoutManager(layoutManager);

		// 设置适配器
		UserListAdapter adapter = new UserListAdapter(this::gotoDetailPage);
		NetworkWrapper.getUsersInto(adapter);
		mRvList.setAdapter(adapter);
	}

	// 点击的回调
	public interface UserClickCallback {
		void onItemClicked(String name);
	}

	// 跳转到库详情页面
	private void gotoDetailPage(String name) {
		startActivity(NetworkDetailActivity.from(NetworkActivity.this, name));
	}

}

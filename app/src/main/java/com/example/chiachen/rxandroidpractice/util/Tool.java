package com.example.chiachen.rxandroidpractice.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by Jason_Chien on 2017/7/7.
 */

public class Tool {
	public static final String TOOL = "TOOL";
	public static void tag(String tag){
		Log.e(TOOL, tag);
	}
	public static void tag(int tag){
		Log.e(TOOL, String.valueOf(tag));
	}
	
	public static void showToast(Context context, String string) {
		Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
	}
}

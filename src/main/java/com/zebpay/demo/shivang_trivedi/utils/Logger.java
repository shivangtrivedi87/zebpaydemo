package com.zebpay.demo.shivang_trivedi.utils;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class includes Log methods to be used in application.
 * @author shivang Trivedi
 */
public class Logger {
	public static final String DATEFORMAT = "dd/MM/yyyy hh:mm:ss";
	@NonNull
	@SuppressLint("SimpleDateFormat")
	private static final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);

	private static boolean isShow = true;
	
	public static void info(String tag, String msg) {
		if(isShow){
			Log.i(tag, " [ " + dateToString(new Date()) + " ] " + msg);
		}
	}

	public static void debug(String tag, String msg) {
		if(isShow){
			Log.d(tag, "[ " + dateToString(new Date()) + " ] " + msg);
		}
	}

	public static void error(String tag, String msg) {
		if(isShow){
			Log.e(tag, " [ " + dateToString(new Date()) + " ]" + msg);
		}
	}

	public static void verbose(String tag, String msg) {
		if(isShow){
			Log.v(tag, " [ " + dateToString(new Date()) + " ] " + msg);
		}
	}

	public static void warning(String tag, String msg) {
		if(isShow){
			Log.w(tag, " [ " + dateToString(new Date()) + " ] " + msg);
		}
	}

	protected static String dateToString(Date date) {
		return sdf.format(date);
	}
}
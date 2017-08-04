package com.example.newgame;

import android.app.Activity;
import android.os.Bundle;

public class maingame extends Activity {
	viewclass v;
	int h,w;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		   h=getWindowManager().getDefaultDisplay().getHeight();
	        w=getWindowManager().getDefaultDisplay().getWidth();
	     
		 v=new viewclass(this,h,w);
		setContentView(v);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		v.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		v.resum();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}
	

}

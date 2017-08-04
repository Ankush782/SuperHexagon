package com.example.newgame;

import com.example.newgame.R.raw;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class flash extends Activity implements Runnable {
  Thread t;
  MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flash);
		mp=MediaPlayer.create(this, R.raw.flash);
		t=new Thread(this);
		t.start();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	//	mp.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Intent menu=new Intent(this,Menu.class);
			//mp.stop();
			startActivity(menu);
			this.finish();
		}

	}

}

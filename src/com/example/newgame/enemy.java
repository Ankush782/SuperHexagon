package com.example.newgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaPlayer;

public class enemy {
	Bitmap bm;
	MediaPlayer mp;
	int h,w;
	int x,y;
	int xi,yi,tw,th,ew,eh;
	boolean sound;
	SharedPreferences pr;
 public Bitmap getBm() {
		return bm;
	}

	public void setBm(Bitmap bm) {
		this.bm = bm;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

public enemy(int tw,int th,int h,int w,Bitmap bm,int x,int y,Context c)
	{
	ew=(int) (tw*0.1);
	eh=(int) (tw*0.1);
	pr=c.getSharedPreferences("sshigh", Context.MODE_PRIVATE);
	sound=pr.getBoolean("sound", true);
	mp=MediaPlayer.create(c, R.raw.re);
	 this.x=x;
	 this.y=y;
	 this.tw=tw;
	 this.th=th;
	 xi=10;
	 yi=10;
		
	 this.h=h;
		this.bm=Bitmap.createScaledBitmap(bm, w, h, false);
		
		
	}
public void update()
{

	x=x+xi;
	y=y+yi;
	if(x>=tw-ew||x<=0)
	{
		xi=-1*xi;
		if(sound)
		{
			mp.seekTo(0);
		mp.start();}
	}
	if(y>=th-eh||y<=0)
	{
		yi=-1*yi;
		if(sound){
		mp.seekTo(0);
		mp.start();}
	}
	
}

public Rect geth()
{
	return new Rect(x,y,x+bm.getWidth(),y+bm.getHeight());
}}

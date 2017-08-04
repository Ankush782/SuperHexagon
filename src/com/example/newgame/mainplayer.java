package com.example.newgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class mainplayer {
Bitmap bm;
int height;
int width;
int x,y;
int s;

public  mainplayer(Context c,int h,int w)
{
	s=100;
	x=(int) (w*0.5);
	y=(int) (h*0.4);
	height=(int) (h*0.1);
	width=(int) (h*0.1);
	bm=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(c.getResources(), R.drawable.pent), width, height, false);
	
}
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
public void control()
{
	y-=s;
	s+=50;
}
public void update()
{
	y+=25;
	s=100;

}
public Rect gethit()
{
	return new Rect(x,y,x+bm.getWidth(),y+bm.getHeight());
}
}

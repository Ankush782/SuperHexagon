package com.example.newgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class viewclass extends SurfaceView implements Runnable{
    volatile boolean playing;
    Canvas c;
    SurfaceHolder sh;
    Paint p;
    Rect player;
    enemy e;
    boolean ft;
    MediaPlayer mediac,mediaco,hsc;
     SharedPreferences pr;
    
    SharedPreferences.Editor pre;
   
    WindowManager wm;
    Display d;
    Point po;
    int h,w;
    mainplayer mp;
   long co;
    enemy enemies[]=new enemy[10];
    long score;
    long ts;
   Context context;
   long hscore;
    boolean sound;
	
	Thread game=null;

	public viewclass(Context context,int h,int w) {
		super(context);
		playing=true;
		ft=true;;
		score=0;
		hsc=MediaPlayer.create(context, R.raw.highscore);
		pr=context.getSharedPreferences("sshigh", Context.MODE_PRIVATE);
		pre=pr.edit();
		pre.putLong("high", 0);
		hscore=pr.getLong("high", 0);
		mediac=MediaPlayer.create(context, R.raw.click);
		mediaco=MediaPlayer.create(context, R.raw.col);
		this.context=context;
		sound=pr.getBoolean("sound", true);
		co=2;
		sh=getHolder();
		p=new Paint();
		po=new Point();
		
		mp=new mainplayer(context,h,w);
		this.h=	h;
		this.w=w;
	
		int l=(int) (w*(0.35));
		int r=(int) (w*(0.4));
		int t=(int) (h*(0.4));
		int b=(int) (w*(0.3));
		
		player=new Rect(l, t, r, b);
		int we=(int) (w*0.1);
		int he=(int) (w*0.1);
		 enemies[0]=new enemy(w, h, he, we,BitmapFactory.decodeResource(context.getResources(), R.drawable.c3), 0, 0,context);
		 enemies[1]=new enemy(w, h, he, we,BitmapFactory.decodeResource(context.getResources(), R.drawable.c2), 0, 0,context);
		 enemies[2]=new enemy(w, h, he, we,BitmapFactory.decodeResource(context.getResources(), R.drawable.c1), 0, 0,context);
		 enemies[3]=new enemy(w, h, he, we,BitmapFactory.decodeResource(context.getResources(), R.drawable.c4), 0, 0,context);
		 enemies[4]=new enemy(w, h, he, we,BitmapFactory.decodeResource(context.getResources(), R.drawable.c6), 0, 0,context);
		 enemies[5]=new enemy(w, h, he, we,BitmapFactory.decodeResource(context.getResources(), R.drawable.c8), 0, 0,context);
		 enemies[6]=new enemy(w, h, he, we,BitmapFactory.decodeResource(context.getResources(), R.drawable.c10), 0, 0,context);
		 enemies[7]=new enemy(w, h, he, we,BitmapFactory.decodeResource(context.getResources(), R.drawable.c3), 0, 0,context);
		 enemies[8]=new enemy(w, h, he, we,BitmapFactory.decodeResource(context.getResources(), R.drawable.c10), 0, 0,context);
		 enemies[9]=new enemy(w, h, he, we,BitmapFactory.decodeResource(context.getResources(), R.drawable.c1), 0, 0,context);

		
	
	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(ft){
		while(playing)
		{
			
			draw();
			update();
			contril();
			try
			{
				Thread.sleep(75);
				
			}catch(Exception e){}
		}
		
	}}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(sound)
		{
		mediac.seekTo(0);
		mediac.start();}
		mp.control();
		if(!playing&&!ft)
		{
			
			score=0;
			co=0;
			playing=true;
			
		
			}
		else if(!playing&&ft)
		{
			playing=true;
		}
		
			
		return super.onTouchEvent(event);
	}
	private void contril() {
		// TODO Auto-generated method stub
		for(int i=0;i<=co;i++)
		{
			if(Rect.intersects(mp.gethit(), enemies[i].geth()))
			{
				playing=false;
			
				  

				
				
				this.out();
				if(score>hscore)
				{
					pre.putLong("high", score);
					pre.commit();
					hscore=score;
				}
				
				for(int i1=0;i1<=co;i1++){
				enemies[i1].x=0;
				enemies[i1].y=0;
				enemies[i1].xi=10;
						enemies[i1].yi=10;
						
				}
				score=0;
				co=0;
			}
		}
		
		
	}

	private void draw() {
		
		if(sh.getSurface().isValid())
		{
			c=sh.lockCanvas();
			c.drawColor(Color.BLACK);
		   c.drawBitmap(mp.getBm(), mp.getX(), mp.getY(), p);
		   p.setColor(Color.WHITE);
		
		
		 p.setTextSize((float) (h*0.05));
		 c.drawText("Score :"+Long.toString(score), 10, (float) (h*0.05), p);
		
		 
		 c.drawText("High Score :"+Long.toString(hscore), w-175,(float) (h*0.05), p);
		   for(int i=0;i<=co;i++)
		   {
			   c.drawBitmap(enemies[i].getBm(), enemies[i].getX(), enemies[i].getY(),p);
		   }
		
			sh.unlockCanvasAndPost(c);
		}
	}

	private void update() {
		// TODO Auto-generated method stub
		mp.update();
	
		ts++;
		if(ts%10==0)
		{
			score++;
			ts=0;
		}
		co=(score/10)<=9?score/10:9;
	
	
		  for(int i=0;i<=co;i++)
		  {
			  enemies[i].update();
		  }
		  if(mp.getY()>h||mp.getY()<0)
		  {
			
				
			  playing=false;
			  this.out();
			  if(score>hscore)
				{
					pre.putLong("high", score);
					hscore=score;
				}
			 
				for(int i1=0;i1<=co;i1++){
					enemies[i1].x=0;
					enemies[i1].y=0;
					enemies[i1].xi=10;
							enemies[i1].yi=10;
					}
				score=0;
				co=0;
		  }
		
	}
	private void out() {
		// TODO Auto8-generated method stub
		p.setTextSize(80);
		p.setTextAlign(Align.CENTER);
		if(sh.getSurface().isValid())
		{
			c=sh.lockCanvas();
			if(score>hscore)
				
			{
				if(sound){
				hsc.seekTo(0);
				hsc.start();}
				c.drawColor(Color.GREEN);
				c.drawText("Game Over",w/2, 75, p);
				p.setTextSize(25);
				c.drawText("Score :"+Long.toString(score),w/2, 150, p);
				c.drawText("New High Score :"+Long.toString(score),w/2,190, p);
				p.setTextSize(80);
				c.drawText("Tab to replay",w/2, 275, p);
				
				
			}else{
				if(sound){
				  mediaco.seekTo(0);
					mediaco.start();}
			c.drawColor(Color.RED);
			c.drawText("Game Over",w/2, 75, p);
			p.setTextSize(25);
			c.drawText("Score :"+Long.toString(score),w/2, 150, p);
			c.drawText("High Score :"+Long.toString(hscore),w/2,190, p);
			p.setTextSize(80);
			c.drawText("Tab to replay",w/2, 275, p);}
			sh.unlockCanvasAndPost(c);
			p.setTextAlign(Align.LEFT);
		}
		
	
		mp.x=(int) (w*0.5);
		mp.y=(int) (h*0.4);
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(event.getKeyCode()==KeyEvent.KEYCODE_BACK)
		{
			playing=false;
		}
		
		return false;
	}
	public void pause()
	{
		playing=false;
		
		ft=false;
		try{
			game.join();
		}catch(Exception e){}
	
		
	}
	public void resum()
	{
		playing=true;
		ft=true;
		game=new Thread(this);
		game.start();
	}
	
}

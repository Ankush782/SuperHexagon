package com.example.newgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Menu extends Activity {
Button play,rate,help,music;
TextView name,hscore;
long high;
boolean sound;
String namep;
SharedPreferences sp;
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	sound=sp.getBoolean("sound", true);
	high=sp.getLong("high", 0);
	hscore.setText("High Score :"+Long.toString(high));
	
}
SharedPreferences.Editor spe;
AlertDialog.Builder adb;
EditText ed;
Menu x;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		adb=new AlertDialog.Builder(this);
		ed=new EditText(this);
		adb.setView(ed);
		adb.setTitle("Hey enter your name");
		x=this;
		adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				namep=ed.getText().toString();
				name.setText("Hello "+namep);
				spe=sp.edit();
				spe.putString("name", namep);
				spe.commit();
				
			}
		});
		play=(Button)findViewById(R.id.play);
		music=(Button)findViewById(R.id.button1);
		rate=(Button)findViewById(R.id.button3);
		help=(Button)findViewById(R.id.button4);
		name=(TextView)findViewById(R.id.name);
		hscore=(TextView)findViewById(R.id.high);
		sp=getApplicationContext().getSharedPreferences("sshigh", Context.MODE_PRIVATE);
		namep=sp.getString("name", "Dude");
	
		if(namep=="Dude")
		{
			adb.show();
			
			
			
		}
		name.setText("Hello "+namep);
		if(sound)
		{
			music.setBackgroundResource(R.drawable.music);
		}
		else
		{
			music.setBackgroundResource(R.drawable.soundoff);
		}
       play.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent i=new Intent(getApplicationContext(),maingame.class);
			startActivity(i);
		//	x.finish();
			
		}
	});
       music.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(sound)
			{
				sound=false;
			}
			else
			{
				sound=true;
			}
			spe=sp.edit();
			spe.putBoolean("sound", sound);
			spe.commit();
			if(sound)
			{
				music.setBackgroundResource(R.drawable.music);
			}
			else
			{
				music.setBackgroundResource(R.drawable.soundoff);
			}
			
		}
	});
   
	}

}

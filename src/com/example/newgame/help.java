package com.example.newgame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class help extends Activity {
  TextView name,help,score,tries;
  SharedPreferences sp;
	SharedPreferences.Editor spe;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sp=getApplicationContext().getSharedPreferences("sshigh", Context.MODE_PRIVATE);
		setContentView(R.layout.help);
		name=(TextView)findViewById(R.id.name);
		help=(TextView)findViewById(R.id.disc);
		score=(TextView)findViewById(R.id.high);
		tries=(TextView)findViewById(R.id.tries);
		
	}

}

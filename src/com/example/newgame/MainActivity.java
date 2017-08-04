package com.example.newgame;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{
int h,w;
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        Button b=(Button)findViewById(R.id.play);
        tv=(TextView) findViewById(R.id.textView1);
      tv.setTextColor(Color.WHITE);
      SharedPreferences sp=getSharedPreferences("sshigh",MODE_PRIVATE);
      tv.setText("High Scores :"+Long.toString(sp.getLong("high", 0)));
        b.setOnClickListener(this);
     
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View arg0) {
		Intent i=new Intent(this,maingame.class);
		startActivity(i);
		finish();
		
	}


	
}

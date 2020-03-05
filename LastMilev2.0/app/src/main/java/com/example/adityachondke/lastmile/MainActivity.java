package com.example.adityachondke.lastmile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = (TextView) findViewById(R.id.textView);
        Thread timer= new Thread()
        {
            public void run(){
                try{
                    sleep(5000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();

                }
                finally {
                    Intent i = new Intent(MainActivity.this,Login.class);
                    startActivity(i);
                }
            }
        };
        timer.start();

    }

    public void startAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim);
        t.setAnimation(animation);
    }

    public void onPause() {
        super.onPause();
        finish();
    }

}

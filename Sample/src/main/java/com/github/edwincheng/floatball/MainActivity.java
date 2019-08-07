package com.github.edwincheng.floatball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.edwincheng.floatball.service.FloatBallService;
import com.github.edwincheng.floatbuttonball.manager.ViewManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, FloatBallService.class);
        startService(intent);
    }

    public void showball(View view) {
        ViewManager.getInstance(this).showFloatBall();
    }

    public void hideball(View view) {
        ViewManager.getInstance(this).hideFloatBall();
    }
}

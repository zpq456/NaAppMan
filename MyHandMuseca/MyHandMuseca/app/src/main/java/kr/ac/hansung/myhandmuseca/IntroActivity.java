package kr.ac.hansung.myhandmuseca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IntroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void onClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

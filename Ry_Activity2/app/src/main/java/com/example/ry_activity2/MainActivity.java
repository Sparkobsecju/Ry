package com.example.ry_activity2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test1(View view) {
        Intent intent = new Intent(this, Page2Activity.class);
        //startActivity(intent);
        startActivityForResult(intent, 475);
    }

    public void test2(View view) {
        Intent intent = new Intent(this, Page3Activity.class);
        startActivityForResult(intent, 444);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 475) {
            int key1 = data.getIntExtra("key1", -1);
            String key2 = data.getStringExtra("key2");
            Log.v("brad", key1 + ":" + key2);
        }
        Log.v("brad", "onActivityResult(): " + requestCode + " : " + resultCode);
    }

 }
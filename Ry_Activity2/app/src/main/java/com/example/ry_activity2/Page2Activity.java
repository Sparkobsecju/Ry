package com.example.ry_activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Page2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

    }

    public void test1(View view) {
        finish();
    }

    @Override
    public void finish() {

        Intent intent = new Intent();
        intent.putExtra("key1", 123);
        intent.putExtra("key2", "brad");
        //setResult((int) (Math.random() * 100));
        setResult((int) (Math.random() * 100), intent);
        super.finish();
    }
}
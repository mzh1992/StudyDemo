package com.example.amsdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class ProxyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "ProxyActivity launch.", Toast.LENGTH_SHORT).show();
    }
}

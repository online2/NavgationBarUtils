package com.linfc.www.navgationbarutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.linfc.www.navgationbarutils.utils.NavgationbarUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvTest = findViewById(R.id.tvTest);
        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTest.setText(NavgationbarUtils.navgationbarIsOpen(MainActivity.this)+"");
                Toast.makeText(MainActivity.this, NavgationbarUtils.getNavigationBarHeight(MainActivity.this)+"", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

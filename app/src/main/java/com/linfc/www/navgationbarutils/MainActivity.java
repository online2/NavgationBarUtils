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

        final TextView tvInfo = findViewById(R.id.tvInfo);

        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder str = new StringBuilder();
                if (NavgationbarUtils.navgationbarIsOpen(MainActivity.this)){
                    str.append("导航栏开启了");
                }else {
                    str.append("导航栏关闭了");
                }
                tvTest.setText(str.toString());
                tvInfo.setText("动态获取到的导航栏高度:"+NavgationbarUtils.getNavigationBarHeight(MainActivity.this));

            }
        });
    }
}

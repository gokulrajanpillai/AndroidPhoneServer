package com.gpow.phoneserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton pwrBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pwrBtn.findViewById(R.id.pwr_btn);
        pwrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwrBtn.setBackgroundColor(R.color.black);
                new Toast()
            }
        });
    }
}
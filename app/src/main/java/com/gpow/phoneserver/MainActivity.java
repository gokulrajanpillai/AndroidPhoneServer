package com.gpow.phoneserver;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton pwrBtn;
    private TextView htmlCodeTv;

    private PhoneServer server = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureServerBtn();
    }

    private void configureServerBtn() {
        pwrBtn = findViewById(R.id.pwr_btn);
//        pwrBtnLayout = findViewById(R.id.pwr_btn_layout);
        pwrBtn.setOnClickListener(view -> {
            ColorDrawable colorDrawable = (ColorDrawable) pwrBtn.getBackground();
            if (colorDrawable instanceof ColorDrawable) {
                int color = ((ColorDrawable) colorDrawable).getColor();
                if (color == getResources().getColor(R.color.green)) {
                    pwrBtn.setBackgroundColor(getResources().getColor(R.color.red));
                    Toast.makeText(MainActivity.this, "Stopped Server!",
                            Toast.LENGTH_SHORT).show();
                    if (server != null) {
                        server.stopServer();
                    }
                } else {
                    pwrBtn.setBackgroundColor(getResources().getColor(R.color.green));
                    Toast.makeText(MainActivity.this, "Started Server!",
                            Toast.LENGTH_SHORT).show();
                    // Starting server
                    if (server == null) {
                        server = new PhoneServer(8080);
                    }
                    server.setWebsiteHTML(htmlCodeTv.getText().toString());
                    server.startServer();
                }
            }
        });
    }
}
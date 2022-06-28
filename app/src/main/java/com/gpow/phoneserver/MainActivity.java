package com.gpow.phoneserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

public class MainActivity extends AppCompatActivity {

    private ImageButton pwrBtn;
    private TextView htmlCodeTv;

    private int PORT_NUMBER = 8080;
    private PhoneServer server = null;

    private Toast toast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureHtmlTv();
        configureServerBtn();
    }


    private void configureHtmlTv() {
        htmlCodeTv = findViewById(R.id.htmlcode_tv);
        String htmlcode = "<html>\n"
                + "<body>\n"
                + "<p>Hello World!</p>\n"
                + "</body>\n"
                + "</html>";
        htmlCodeTv.setText(htmlcode);
        htmlCodeTv.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    restartServer();
                    return true;
                }
                return false;
            }
        });
    }


    private void configureServerBtn() {
        pwrBtn = findViewById(R.id.pwr_btn);
        pwrBtn.setOnClickListener(view -> {
            ColorDrawable colorDrawable = (ColorDrawable) pwrBtn.getBackground();
            if (colorDrawable instanceof ColorDrawable) {
                int color = ((ColorDrawable) colorDrawable).getColor();
                if (color == getResources().getColor(R.color.green)) {
                    pwrBtn.setBackgroundColor(getResources().getColor(R.color.red));
                    showToast("Stopped Server!");
                    stopServer();
                } else {
                    pwrBtn.setBackgroundColor(getResources().getColor(R.color.green));
                    showToast("Started Server!");
                    startServer();
                }
            }
        });
    }


    private void startServer() {
        // Starting server
        if (server == null) {
            server = new PhoneServer(PORT_NUMBER);
        }
        server.setWebsiteHTML(htmlCodeTv.getText().toString());
        server.startServer();
    }


    private void stopServer() {
        if (server != null) {
            server.stopServer();
        }
    }


    private void restartServer() {
        stopServer();
        startServer();
        showToast("Restarting server");
    }


    private void showToast(String msg) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(MainActivity.this, msg + "\nRunning under: " + wifiIpAddress() + ":" + PORT_NUMBER, Toast.LENGTH_LONG);
        toast.show();
    }


    private String wifiIpAddress() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

        // Convert little-endian to big-endianif needed
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }

        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

        String ipAddressString;
        try {
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException ex) {
            Log.e("WIFIIP", "Unable to get host address.");
            ipAddressString = null;
        }

        return ipAddressString;
    }
}
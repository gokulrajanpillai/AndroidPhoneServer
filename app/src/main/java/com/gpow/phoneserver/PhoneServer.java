package com.gpow.phoneserver;

//import org.nanohttpd.*;
import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
//
public class PhoneServer extends NanoHTTPD {

    private int PORT_NUMBER = 8080;
    private String websiteHTML = "";

    public PhoneServer(int port) {
        super(port);
        System.out.println("\nRunning! Point your browsers to http://localhost:8080/ \n");
    }


    public void startServer() {
        try {
            start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void stopServer() {
        stop();
    }


    public void setWebsiteHTML(String htmlCode) {
        websiteHTML = htmlCode;
    }

    @Override
    public Response serve(IHTTPSession session) {
//        String msg = "<html><body><h1>Hello server</h1>\n";
//        Map<String, String> parms = session.getParms();
//        if (parms.get("username") == null) {
//            msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n";
//        } else {
//            msg += "<p>Hello, " + parms.get("username") + "!</p>";
//        }
        return newFixedLengthResponse(websiteHTML);
    }
}
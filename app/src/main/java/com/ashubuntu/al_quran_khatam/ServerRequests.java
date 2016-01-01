package com.ashubuntu.al_quran_khatam;

import android.content.ContentValues;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class ServerRequests {
    private static final String SERVER_ADDRESS = "http://10.0.2.2/";
    //private static final String SERVER_ADDRESS = "http://localhost/";
    //private static final String SERVER_ADDRESS = "http://ashubuntu.orgfree.com/";
    private static final int CONNECTION_TIMEOUT = 15 * 1000;

    public ServerRequests() {}

    public JSONObject sendServerRequest(String requestMethod, String file, ContentValues dataToSend) {
        JSONObject dataToReceive = new JSONObject();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(SERVER_ADDRESS + file).openConnection();
            if (requestMethod.equals("POST")) {
                httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                httpURLConnection.setReadTimeout(CONNECTION_TIMEOUT);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
                writer.write(getPostData(dataToSend));
                writer.flush();
                writer.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder prepare = new StringBuilder();
                String helper;

                while ((helper = reader.readLine()) != null) {
                    prepare.append(helper);
                }

                reader.close();
                httpURLConnection.disconnect();

                dataToReceive = new JSONObject(String.valueOf(prepare));
            } else {
                httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                httpURLConnection.setReadTimeout(CONNECTION_TIMEOUT);
                httpURLConnection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder prepare = new StringBuilder();
                String helper;

                while ((helper = reader.readLine()) != null) {
                    prepare.append(helper);
                }

                reader.close();
                httpURLConnection.disconnect();

                dataToReceive = new JSONObject(String.valueOf(prepare));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return dataToReceive;
    }

    private String getPostData(ContentValues dataToSend) {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (String value : dataToSend.keySet()) {
            if (first)
                first = false;
            else
                builder.append("&");

            try {
                builder.append(URLEncoder.encode(value, "UTF-8"));
                builder.append("=");
                builder.append(URLEncoder.encode((String) dataToSend.get(value), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return String.valueOf(builder);
    }
}

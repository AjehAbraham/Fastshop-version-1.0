package com.fastshop.myApp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import android.os.Handler;
import android.content.Context;
import java.io.FileInputStream;
import java.io.IOException;

public class StartService extends Service {
    private Context context;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       /* String sessionID = intent.getStringExtra("sessionID");
        String UserId = intent.getStringExtra("UserId");
        sendRequest(sessionID, UserId);*/
        return START_STICKY;
    }
   public void begin(Context context){
       Intent intent = new Intent(context, StartService.class);
       context.startService(intent);
       
   }
    /*private void sendRequest(String sessionID, String UserId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String Url = "https:fastshop.com/api/startService";                                           
                     URL url = new URL(Url);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Connection", "Keep-Alive");
                    DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                    dos.writeBytes("SessionID=" + sessionID + "&UserId=" + UserId);
                    dos.flush();
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void begin(Context context, String sessionID, String UserId) {
        Intent intent = new Intent(context, StartService.class);
        intent.putExtra("// Read response if needed
                    // InputStream is = connection.getInputStream();
                    // BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    // StringBuilder response = new StringBuilder();
                    // String line;
                    // while ((line = reader.readLine()) != null) {
                    //     response.append(line);
                    // }
                    // reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void begin(Context context, String sessionID, String UserId) {
        Intent intent = new Intent(context, StartService.class);
        intent.putExtra("sessionID", sessionID);
        intent.putExtra("UserId", UserId);
        context.startService(intent);
    }*/
}
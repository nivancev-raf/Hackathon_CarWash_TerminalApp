package com.cyb.payten_windowsxp_terminalapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.cyb.payten_windowsxp_terminalapp.MainActivity;
import org.json.JSONException;
import org.json.JSONObject;


public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String response = intent.getStringExtra("ResponseResult");
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject financialObject = jsonObject.getJSONObject("response").getJSONObject("financial");
            JSONObject resultObject = financialObject.getJSONObject("result");
            String message = resultObject.getString("message");
            Log.d("MyReceiver", "Message: ${}" + message); // Proverili smo ovde stampa Odobreno
            if (message.equals("Odobreno")) {
                Log.d("MainActivityyyy", message);
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    for (String key : extras.keySet()) {
                        Log.d("MyReceiver", "Extra: " + key + " = " + extras.get(key));
                    }
                }
                Intent thankYouIntent = new Intent(context, MainActivity.class);
                thankYouIntent.setAction(intent.getAction());
                thankYouIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                thankYouIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                thankYouIntent.putExtra("navigateTo", "thank_you_screen");
                Log.d("MyReceiver", "Intent: " + thankYouIntent);
                context.startActivity(thankYouIntent);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}


/*
 if (message.equals("Odobreno")) {
                Log.d("MyReceiver--", "Approved") ;
                Intent startWashingIntent = new Intent(context, SplashScreenKt.class);
                Log.d("MyReceiver--", startWashingIntent.toString()) ;
                Log.d("MyReceiver--", startWashingIntent.toString()) ;
                startWashingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startWashingIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(startWashingIntent);
            }else {
                Log.d("MyReceiver--", "Approved") ;
                Intent startWashingIntent = new Intent(context, MainActivity.class);
                Log.d("MyReceiver--", startWashingIntent.toString()) ;
                Log.d("MyReceiver--", startWashingIntent.toString()) ;
                startWashingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startWashingIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(startWashingIntent);
            }

 */
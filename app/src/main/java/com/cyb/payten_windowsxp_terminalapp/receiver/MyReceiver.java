package com.cyb.payten_windowsxp_terminalapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cyb.payten_windowsxp_terminalapp.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import kotlinx.serialization.json.JsonObject;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyReceiver", "Intent received: ${}" + intent.getAction());
        String response = intent.getStringExtra("ResponseResult");
        Log.d("MyReceiver", "Response: ${}" + response);
        Intent i = new Intent(context, MainActivity.class);
        Log.d("MyReceiver", String.valueOf(i));
        i.putExtra("ResponseResult",response);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(i);
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject financialObject = jsonObject.getJSONObject("response").getJSONObject("financial");
            JSONObject resultObject = financialObject.getJSONObject("result");
            String message = resultObject.getString("message");
            Log.d("MyReceiver", "Message: ${}" + message);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
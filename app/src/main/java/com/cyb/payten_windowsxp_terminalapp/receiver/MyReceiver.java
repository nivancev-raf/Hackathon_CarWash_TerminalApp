package com.cyb.payten_windowsxp_terminalapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cyb.payten_windowsxp_terminalapp.MainActivity;

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
    }
}
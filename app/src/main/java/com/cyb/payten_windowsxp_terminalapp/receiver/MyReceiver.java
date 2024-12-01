package com.cyb.payten_windowsxp_terminalapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cyb.payten_windowsxp_terminalapp.MainActivity;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyReceiver", "Intent received: ${intent.action}");
        String response = intent.getStringExtra("ResponseResult");
        Intent i = new Intent(context, MainActivity.class);
        i.putExtra("ResponseResult",response);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(i);
    }
}
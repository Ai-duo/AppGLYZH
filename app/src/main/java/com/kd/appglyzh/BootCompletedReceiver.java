
package com.kd.appglyzh;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompletedReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Intent autoRun = new Intent(context, MainActivity.class);
		autoRun.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(autoRun);
	}
}
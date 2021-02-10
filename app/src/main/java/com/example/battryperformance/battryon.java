package com.example.battryperformance;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.BatteryManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.battryperformance.common.editor;
import static com.example.battryperformance.common.pref;
import static com.example.battryperformance.common.r;

public class battryon extends BroadcastReceiver {


    @Override
    public void onReceive(final Context context, Intent intent) {

        pref=context.getSharedPreferences("battryvalue",0);
        editor=pref.edit();
        TextView stattulabel=((MainActivity)context).findViewById(R.id.statuslabel);
        TextView percentagelabel=((MainActivity)context).findViewById(R.id.percentagelabel);
        final ImageView battyimage=((MainActivity)context).findViewById(R.id.battyimage);





        String acion=intent.getAction();
        if(acion!=null && acion.equals(Intent.ACTION_BATTERY_CHANGED))
        {
            int status=intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            String message="";
            switch (status){
                case  BatteryManager.BATTERY_STATUS_FULL:
                    message="Full";
                    break;
                case  BatteryManager.BATTERY_STATUS_CHARGING:
                    message="charging";
                    break;
                case  BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    message="Not charging";
                    break;
            }
            stattulabel.setText(message);

            int level=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            int scale=intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
            final int percentage=(level*100)/scale;
            percentagelabel.setText(percentage+"%");

            final Resources res=context.getResources();

            r= RingtoneManager.getRingtone(context,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));


            if(percentage==pref.getInt("value",-1))
            {

                if(!r.isPlaying())
                {
                    r.play();
                   editor.putInt("value",0);



                }


            }

              if(90>percentage && percentage>=65)
            {
                battyimage.setImageDrawable(res.getDrawable(R.drawable.ic_battery_50_black_24dp));
            }
            else if(65>percentage && percentage>=40)
            {
                battyimage.setImageDrawable(res.getDrawable(R.drawable.ic_battery_50_black_24dp));
            }
            else if(40>percentage && percentage>=15)
            {  battyimage.setImageDrawable(res.getDrawable(R.drawable.ic_battery_50_black_24dp));

            }
            else {
                battyimage.setImageDrawable(res.getDrawable(R.drawable.ic_battery_50_black_24dp));
            }

        }
    }
}

package com.example.battryperformance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.battryperformance.common.editor;
import static com.example.battryperformance.common.r;

public class MainActivity extends AppCompatActivity {
    private battryon mbattrysaver=new battryon();
    final int[] pp = {0};
    private IntentFilter intentFilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText sttsu=findViewById(R.id.sttsu);
        Button button=findViewById(R.id.button);
        Button button1=findViewById(R.id.syop);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(common.r.isPlaying())
                {
                    r.stop();
                    editor.remove("value");
                    unregisterReceiver(mbattrysaver);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pp[0] =  Integer.parseInt(sttsu.getText().toString());
                editor.putInt("value",pp[0]);
                editor.commit();
              //  registerReceiver(mbattrysaver,intentFilter);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mbattrysaver,intentFilter);

if(common.music.contains("playing"))
{

    common.hello="hello";
  //

}
else
{
    Log.d("hh","hhhh");
}

    }


    @Override
    protected void onStop() {
        super.onStop();

    }
}

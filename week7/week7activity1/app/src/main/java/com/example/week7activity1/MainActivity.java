package com.example.week7activity1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity implements MainCallbacks {

    FragmentTransaction ft;
    RedFragment redFragment;
    BlueFragment blueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a new BLUE fragment - show it
        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_holder_blue, BlueFragment.newInstance("first-blue"), null);
        ft.commit();
// create a new RED fragment - show it
        ft = getFragmentManager().beginTransaction();
        redFragment = RedFragment.newInstance("first-red");
        ft.replace(R.id.main_holder_red, RedFragment.newInstance("first-red"), null);
        ft.commit();
    }

    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {
// show message arriving to MainActivity
        Toast.makeText(getApplication(),
                " MAIN GOT>> " + sender + "\n" + strValue, Toast.LENGTH_LONG)
                .show();
        if (sender.equals("RED-FRAG")) {
// TODO: if needed, do here something on behalf of the RED fragment
        }
        if (sender.equals("BLUE-FRAG")) {
            try {
// forward blue-data to redFragment using its callback method
                redFragment.onMsgFromMainToFragment("\nSender: " + sender
                        + "\nMsg: " + strValue);
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
    }
}
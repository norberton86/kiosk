package kiosk.ddc.a3nomdev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kiosk.ddc.a3nomdev.myapplication.util.LocalStorage;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // to keep the screen ON
    }

    @OnClick(R.id.layoutMaster)
    void scanClick() {

        if(LocalStorage.isConfigured(this))
        {
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(SplashActivity.this,SettingsActivity.class);
            startActivity(intent);
        }

            

    }
}

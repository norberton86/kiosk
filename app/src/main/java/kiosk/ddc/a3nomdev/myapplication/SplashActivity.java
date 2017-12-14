package kiosk.ddc.a3nomdev.myapplication;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.layoutMaster)
    void scanClick() {

        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
    }
}

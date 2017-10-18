package kiosk.ddc.a3nomdev.myapplication;

import android.content.Intent;
import android.graphics.Typeface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import butterknife.ButterKnife;
import butterknife.OnClick;
import kiosk.ddc.a3nomdev.myapplication.model.GitHub;
import kiosk.ddc.a3nomdev.myapplication.service.ddcService;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        //load the images from Font Awesome Icon
        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.mio), iconFont);

    }

    @OnClick(R.id.scanImage)
    void scanClick() {
        Intent intent = new Intent(MainActivity.this, ScanActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.UserLogin)
    void userClick() {
        //goResults();

        ddcService.getUserData().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GitHub>() {

                               @Override
                               public void onCompleted() { }

                               @Override
                               public void onError(Throwable e) { }

                               @Override
                               public void onNext(GitHub git) {
                                   Toast.makeText(MainActivity.this,git.getName(),Toast.LENGTH_SHORT).show();
                               }
                           }
                );
    }

    private void goResults() {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        startActivity(intent);
    }
}

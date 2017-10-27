package kiosk.ddc.a3nomdev.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import kiosk.ddc.a3nomdev.myapplication.model.User;
import kiosk.ddc.a3nomdev.myapplication.model.UserCollection;
import kiosk.ddc.a3nomdev.myapplication.service.ddcService;
import rx.Observer;


public class MainActivity extends AppCompatActivity {

    public static String codigo;

    @InjectView(R.id.editTextName) EditText editTextName;
    @InjectView(R.id.editTextCompany) EditText editTextCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        //load the images from Font Awesome Icon
        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.mio), iconFont);

        /*Drawable d=getResources().getDrawable(R.drawable.banner);
        getSupportActionBar().setBackgroundDrawable(d);*/

    }

    @OnClick(R.id.scanImage)
    void scanClick() {
        Intent intent = new Intent(MainActivity.this, ScanActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.UserLogin)
    void userClick() {
          CallWebService();
    }

    private void goResults(UserCollection u) {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("UserCollection",u);
        startActivity(intent);
    }

    private void CallWebService() {

        ddcService.Get(editTextName.getText().toString(),"name")
                .subscribe(new Observer<List<User>>() {

                               @Override
                               public void onCompleted() {
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Toast.makeText(MainActivity.this, "Error trying to login", Toast.LENGTH_SHORT).show();
                               }

                               @Override
                               public void onNext(List<User> u) {

                                   UserCollection uc= new UserCollection();
                                   uc.setUsers(u);
                                   goResults(uc);
                               }
                           }
                );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings)
        {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

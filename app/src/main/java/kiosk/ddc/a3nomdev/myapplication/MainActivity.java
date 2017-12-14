package kiosk.ddc.a3nomdev.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;



import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnFocusChange;

import kiosk.ddc.a3nomdev.myapplication.model.User;
import kiosk.ddc.a3nomdev.myapplication.model.UserCollection;
import kiosk.ddc.a3nomdev.myapplication.service.ddcService;
import kiosk.ddc.a3nomdev.myapplication.util.FontManager;
import kiosk.ddc.a3nomdev.myapplication.util.LocalStorage;
import rx.Observer;


public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_SCAN = 1;

    private String loginType="name";

    @InjectView(R.id.editTextFirstName) EditText editTextFirstName;
    @InjectView(R.id.editTextLastName) EditText editTextLastName;
    @InjectView(R.id.UserLogin) Button UserLogin;

    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;

    FrameLayout progressBarHolder;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if(requestCode == REQUEST_SCAN && resultCode == Activity.RESULT_OK)
        {
            String reservationId = (String)data.getExtras().getSerializable("ReservationId");
            loginType="scan";
            CallType(reservationId);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        //load the images from Font Awesome Icon
        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.mio), iconFont);

        /*Drawable d=getResources().getDrawable(R.drawable.banner);
        getSupportActionBar().setBackgroundDrawable(d);*/

        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolderMain);

        editTextFirstName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return F( v,  actionId,  event);
            }
        });

        editTextLastName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return F( v,  actionId,  event);
            }
        });

    }


    boolean F(TextView v, int actionId, KeyEvent event)
    {
        if (actionId == EditorInfo.IME_ACTION_NEXT ||actionId == EditorInfo.IME_ACTION_GO ||actionId == EditorInfo.IME_ACTION_DONE) {
            navAndGo();
            return true;
        }
        else {
            return false;
        }
    }



    @OnFocusChange(R.id.editTextFirstName)
    void nameSelected() {
        loginType="name";
    }

    @OnFocusChange(R.id.editTextLastName)
    void companySelected() {
        loginType="name";
    }


    //---------------------------------------------------------------------------

    @OnClick(R.id.scanImage)
    void scanClick() {

        Intent intent = new Intent(MainActivity.this, ScanActivity.class);
        startActivityForResult(intent, REQUEST_SCAN);
    }



    @OnClick(R.id.UserLogin)
    void userClick() {

       navAndGo();
    }

    void navAndGo()
    {
        if(this.getCurrentFocus()!=null)  //this block is to hide the keyboard
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }



        if(loginType.equalsIgnoreCase("name")&&editTextFirstName.getText().toString().equalsIgnoreCase("")&&editTextLastName.getText().toString().equalsIgnoreCase(""))
        {

            showDialog("Error","Fill at least one field");
            return;
        }

        CallWebService();
    }

    private void goResults(UserCollection u) {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        u.setLoginType(loginType);
        intent.putExtra("UserCollection",u);
        startActivity(intent);
    }

    private void CallWebService() {

        String data=editTextFirstName.getText().toString()+"<-->"+editTextLastName.getText().toString();

        UserLogin.setEnabled(false);
        UserLogin.setText("Searching...");

        CallType(data);
    }

    void CallType(String data)
    {
        ShowLoading();
        ddcService.getService(LocalStorage.getServer(this)).Get(data,loginType)
                .subscribe(new Observer<List<User>>() {

                               @Override
                               public void onCompleted() {
                               }

                               @Override
                               public void onError(Throwable e) {
                                   HideLoading();
                                   EnableButton();
                                   showDialog("Error","Error trying to login");
                               }

                               @Override
                               public void onNext(List<User> u) {
                                   HideLoading();

                                   EnableButton();

                                   if(u.size()>0)
                                   {
                                       UserCollection uc= new UserCollection();

                                       uc.setUsers(u);


                                       goResults(uc);
                                   }
                                   else
                                   {
                                       showDialog("Record not found","Please see a staff member");
                                   }

                               }
                           }
                );
    }


    void showDialog(String title,String message)
    {
        new LovelyInfoDialog(this)
                .setTopColorRes(R.color.colorPrimary)
                .setIcon(R.mipmap.information)
                .setTitle(title)
                .setMessage(message)
                .show();
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
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        else
            if(id==android.R.id.home)
            {
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            }


        return super.onOptionsItemSelected(item);
    }

    void EnableButton()
    {
        UserLogin.setEnabled(true);
        UserLogin.setText("SEARCH");

    }

    void HideLoading()
    {
        outAnimation = new AlphaAnimation(1f, 0f);
        outAnimation.setDuration(200);
        progressBarHolder.setAnimation(outAnimation);
        progressBarHolder.setVisibility(View.GONE);
    }

    void ShowLoading()
    {
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        progressBarHolder.setAnimation(inAnimation);
        progressBarHolder.setVisibility(View.VISIBLE);
    }


}

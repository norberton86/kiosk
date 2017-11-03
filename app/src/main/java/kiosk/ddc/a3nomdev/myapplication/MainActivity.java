package kiosk.ddc.a3nomdev.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import kiosk.ddc.a3nomdev.myapplication.model.User;
import kiosk.ddc.a3nomdev.myapplication.model.UserCollection;
import kiosk.ddc.a3nomdev.myapplication.service.ddcService;
import kiosk.ddc.a3nomdev.myapplication.util.FontManager;
import rx.Observer;


public class MainActivity extends AppCompatActivity {

    public static String codigo;
    private String loginType="name";

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

        editTextName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return F( v,  actionId,  event);
            }
        });

        editTextCompany.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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



    @OnFocusChange(R.id.editTextName)
    void nameSelected() {
        loginType="name";
        editTextCompany.setText("");
    }

    @OnFocusChange(R.id.editTextCompany)
    void companySelected() {
        loginType="company";
        editTextName.setText("");
    }


    //---------------------------------------------------------------------------

    @OnClick(R.id.scanImage)
    void scanClick() {

        Intent intent = new Intent(MainActivity.this, ScanActivity.class);
        startActivity(intent);
    }



    @OnClick(R.id.UserLogin)
    void userClick() {

       navAndGo();
    }

    void navAndGo()
    {
        if(loginType.equalsIgnoreCase("company")&&editTextCompany.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(MainActivity.this,"Fill the field COMPANY",Toast.LENGTH_SHORT).show();
            return;
        }

        if(loginType.equalsIgnoreCase("name")&&editTextName.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(MainActivity.this,"Fill the field NAME",Toast.LENGTH_SHORT).show();
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

        final String data;
        if(loginType.equalsIgnoreCase("company"))
        data=editTextCompany.getText().toString();
        else
        data=editTextName.getText().toString();

        ddcService.Get(data,loginType)
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

                                   if(u.size()>0)
                                   {
                                       UserCollection uc= new UserCollection();

                                       User user=new User();
                                       user.setCompanyName(u.get(0).getCompanyName());

                                       uc.setUser(user);
                                       uc.setUsers(u);


                                       goResults(uc);
                                   }
                                   else
                                   {
                                       Toast.makeText(MainActivity.this, "Record not found, please see a staff member", Toast.LENGTH_SHORT).show();
                                   }

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

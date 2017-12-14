package kiosk.ddc.a3nomdev.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import kiosk.ddc.a3nomdev.myapplication.adapter.FinalAdapter;
import kiosk.ddc.a3nomdev.myapplication.model.UserCollection;
import kiosk.ddc.a3nomdev.myapplication.util.FontManager;

public class CheckInActivity extends AppCompatActivity {


    @InjectView(R.id.textViewVipSupport) TextView textViewVipSupport;
    @InjectView(R.id.textViewVip) TextView textViewVip;

    private static final long SPLASH_SCREEN_DELAY = 5000;

    boolean goBack=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        ButterKnife.inject(this);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();


        actionBar.setDisplayHomeAsUpEnabled(true);
        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.checkImage), iconFont);

        Intent i = getIntent();
        UserCollection uc = (UserCollection)i.getSerializableExtra("UserCollection");
        uc.getUsers().add(0,uc.getUser());  //add main user to user list because need to be showed
        uc.setUsers(uc.Chossed());

        if(uc.getUsers().get(0).getIsSarElef()==1)
        {
            textViewVipSupport.setVisibility(View.VISIBLE);
            textViewVip.setText("Please go to the reservation desk to receive a token of our appreciation for your generous donation");
        }
        else
        {
            textViewVip.setText("Thank you for your participation. Enjoy the evening!");
        }



        RecyclerView recyclerViewAccompany = (RecyclerView) findViewById(R.id.recyclerViewFinals);
        recyclerViewAccompany.setAdapter(new FinalAdapter(uc.StringForFinal()));
        recyclerViewAccompany.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAccompany.addItemDecoration(new DividerItemDecoration(this,0));

        Timer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                goBack=true;
                startActivity(new Intent(CheckInActivity.this, SplashActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void Timer()
    {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {


                if(!goBack)
                {
                    startActivity(new Intent(CheckInActivity.this, SplashActivity.class));
                    finish();
                }

            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}

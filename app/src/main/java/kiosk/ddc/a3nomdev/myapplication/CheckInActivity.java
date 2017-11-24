package kiosk.ddc.a3nomdev.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import kiosk.ddc.a3nomdev.myapplication.adapter.FinalAdapter;
import kiosk.ddc.a3nomdev.myapplication.model.UserCollection;
import kiosk.ddc.a3nomdev.myapplication.util.FontManager;

public class CheckInActivity extends AppCompatActivity {




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
        uc.setUsers(uc.Chossed());


        RecyclerView recyclerViewAccompany = (RecyclerView) findViewById(R.id.recyclerViewFinals);
        recyclerViewAccompany.setAdapter(new FinalAdapter(uc.StringForFinal()));
        recyclerViewAccompany.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAccompany.addItemDecoration(new DividerItemDecoration(this,0));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                startActivity(new Intent(CheckInActivity.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

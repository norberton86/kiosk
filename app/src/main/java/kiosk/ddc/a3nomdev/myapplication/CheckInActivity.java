package kiosk.ddc.a3nomdev.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import kiosk.ddc.a3nomdev.myapplication.adapter.AccompanyAdapter;
import kiosk.ddc.a3nomdev.myapplication.adapter.FinalAdapter;

public class CheckInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();


        actionBar.setDisplayHomeAsUpEnabled(true);
        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.checkImage), iconFont);


        ArrayList<String> names=new ArrayList<String>();
        names.add("Willner , Jane");
        names.add("Willner , Mike");
        names.add("Willner , Stacy");
        names.add("Willner , Jhon");


        RecyclerView recyclerViewAccompany = (RecyclerView) findViewById(R.id.recyclerViewFinals);
        recyclerViewAccompany.setAdapter(new FinalAdapter(names));
        recyclerViewAccompany.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAccompany.addItemDecoration(new DividerItemDecoration(this,0));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

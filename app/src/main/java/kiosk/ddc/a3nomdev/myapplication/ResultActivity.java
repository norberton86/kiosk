package kiosk.ddc.a3nomdev.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import kiosk.ddc.a3nomdev.myapplication.adapter.MainAdapter;
import kiosk.ddc.a3nomdev.myapplication.model.User;
import kiosk.ddc.a3nomdev.myapplication.model.UserCollection;

public class ResultActivity extends AppCompatActivity {

    @InjectView(R.id.recyclerView) RecyclerView recyclerView;
   // @InjectView(R.id.textViewCompanyName) TextView textViewCompanyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.inject(this);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.myback);


        Intent i = getIntent();
        UserCollection uc = (UserCollection)i.getSerializableExtra("UserCollection");


       // textViewCompanyName.setText("Results");

        recyclerView.setAdapter(new MainAdapter(uc.getUsers(),this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,0));
    }

    public void goFriends(User user)
    {
        Intent intent = new Intent(ResultActivity.this, AccompaniesActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
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

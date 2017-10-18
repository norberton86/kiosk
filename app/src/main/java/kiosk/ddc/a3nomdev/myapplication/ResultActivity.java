package kiosk.ddc.a3nomdev.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kiosk.ddc.a3nomdev.myapplication.adapter.AccompanyAdapter;
import kiosk.ddc.a3nomdev.myapplication.adapter.MainAdapter;
import kiosk.ddc.a3nomdev.myapplication.model.Accompany;
import kiosk.ddc.a3nomdev.myapplication.model.Client;

public class ResultActivity extends AppCompatActivity {

    private List<Client> clients;
    private List<Accompany> accompanies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);


        initClients();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new MainAdapter(clients,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,0));

        initAccompanies();

        RecyclerView recyclerViewAccompany = (RecyclerView) findViewById(R.id.recyclerViewAccompany);
        recyclerViewAccompany.setAdapter(new AccompanyAdapter(accompanies,this));
        recyclerViewAccompany.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAccompany.addItemDecoration(new DividerItemDecoration(this,0));

        Button login=(Button)findViewById(R.id.UserConfirm);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, CheckInActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Show(Client c)
    {
        Toast.makeText(this,c.getName(),Toast.LENGTH_SHORT).show();
    }

    private void initClients() {
        clients = new ArrayList<Client>();

        clients.add(new Client(1,"Smith Rick 1","4650 Same Place St"));
        clients.add(new Client(1,"Smith Rick 2","4650 Same Place St"));
        clients.add(new Client(1,"Smith Rick 3","4650 Same Place St"));
        clients.add(new Client(1,"Smith Rick 4","4650 Same Place St"));
        clients.add(new Client(1,"Smith Rick 5","4650 Same Place St"));
        clients.add(new Client(1,"Smith Rick 6","4650 Same Place St"));
        clients.add(new Client(1,"Smith Rick","4650 Same Place St"));
        clients.add(new Client(1,"Smith Rick","4650 Same Place St"));
        clients.add(new Client(1,"Smith Rick","4650 Same Place St"));
        clients.add(new Client(1,"Smith Rick","4650 Same Place St"));
        clients.add(new Client(1,"Smith Rick","4650 Same Place St"));
        clients.add(new Client(1,"Smith Rick","4650 Same Place St"));
    }

    private void initAccompanies() {
        accompanies = new ArrayList<Accompany>();

        accompanies.add(new Accompany(false,"Willner, Jane"));
        accompanies.add(new Accompany(false,"Willner, Jane"));
        accompanies.add(new Accompany(false,"Willner, Jane"));
        accompanies.add(new Accompany(false,"Willner, Jane"));
        accompanies.add(new Accompany(false,"Willner, Jane"));
        accompanies.add(new Accompany(false,"Willner, Jane"));
        accompanies.add(new Accompany(false,"Willner, Jane"));
        accompanies.add(new Accompany(false,"Willner, Jane"));
        accompanies.add(new Accompany(false,"Willner, Jane"));
        accompanies.add(new Accompany(false,"Willner, Jane"));
        accompanies.add(new Accompany(false,"Willner, Jane"));
        accompanies.add(new Accompany(false,"Willner, Jane"));


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

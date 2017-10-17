package kiosk.ddc.a3nomdev.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kiosk.ddc.a3nomdev.myapplication.adapter.MainAdapter;
import kiosk.ddc.a3nomdev.myapplication.adapter.RecyclerViewOnItemClickListener;
import kiosk.ddc.a3nomdev.myapplication.model.Client;

public class ResultActivity extends AppCompatActivity {

    private List<Client> clients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initClients();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new MainAdapter(clients,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,0));
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
}

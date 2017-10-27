package kiosk.ddc.a3nomdev.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import kiosk.ddc.a3nomdev.myapplication.adapter.AccompanyAdapter;
import kiosk.ddc.a3nomdev.myapplication.adapter.MainAdapter;
import kiosk.ddc.a3nomdev.myapplication.model.User;
import kiosk.ddc.a3nomdev.myapplication.model.UserCollection;
import kiosk.ddc.a3nomdev.myapplication.service.ddcService;
import rx.Observer;

public class ResultActivity extends AppCompatActivity {

    @InjectView(R.id.recyclerView) RecyclerView recyclerView;
    @InjectView(R.id.recyclerViewAccompany) RecyclerView recyclerViewAccompany;
    @InjectView(R.id.textViewTable) TextView textViewTable;
    @InjectView(R.id.textViewMain) TextView textViewMain;


    User userSelected;
    List<User> friends;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.inject(this);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);


        Intent i = getIntent();
        UserCollection uc = (UserCollection)i.getSerializableExtra("UserCollection");


        recyclerView.setAdapter(new MainAdapter(uc.getUsers(),this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,0));

        friends=new ArrayList<User>();

        initFriends(uc.getUsers().get(0));




    }

    @OnClick(R.id.UserConfirm)
    void check() {
        Intent intent = new Intent(ResultActivity.this, CheckInActivity.class);
        UserCollection uc=new UserCollection();
        uc.setUser(userSelected);
        uc.setUsers(friends);
        intent.putExtra("UserCollection",uc);
        startActivity(intent);
    }


    @OnClick(R.id.buttonSelectAll)
    void All() {

        for(User u : friends)
        {
            u.setCheckIn(true);
        }
        recyclerViewAccompany.setAdapter(new AccompanyAdapter(friends,ResultActivity.this));
        recyclerViewAccompany.setLayoutManager(new LinearLayoutManager(ResultActivity.this));
        recyclerViewAccompany.addItemDecoration(new DividerItemDecoration(ResultActivity.this,0));
    }



    public void ChangeState(int id,boolean state)
    {
        for(User f :  friends){
            if(f.getUserID().equals(id))
            {
                f.setCheckIn(state);
                break;
            }
        }
    }

    void setHeader(User u)
    {
        textViewTable.setText("Table - "+u.getTableNumber());
        textViewMain.setText(u.getFirstName()+" "+u.getLastName());
    }


    public void initFriends(User u) {

        ddcService.GetFriends(u.getUserID(),u.getResevationId())
                .subscribe(new Observer<List<User>>() {

                               @Override
                               public void onCompleted() {
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Toast.makeText(ResultActivity.this, "Error trying to get Accompanies", Toast.LENGTH_SHORT).show();
                               }

                               @Override
                               public void onNext(List<User> users) {
                                   friends=users;
                                   recyclerViewAccompany.setAdapter(new AccompanyAdapter(users,ResultActivity.this));
                                   recyclerViewAccompany.setLayoutManager(new LinearLayoutManager(ResultActivity.this));
                                   recyclerViewAccompany.addItemDecoration(new DividerItemDecoration(ResultActivity.this,0));

                               }
                           }
                );
        setHeader(u);
        this.userSelected=u;

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

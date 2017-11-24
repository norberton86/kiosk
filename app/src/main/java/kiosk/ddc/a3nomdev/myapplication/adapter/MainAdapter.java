package kiosk.ddc.a3nomdev.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import kiosk.ddc.a3nomdev.myapplication.R;
import kiosk.ddc.a3nomdev.myapplication.ResultActivity;
import kiosk.ddc.a3nomdev.myapplication.model.User;

/**
 * Created by 3nomdev on 10/17/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private List<User> data;
    private ResultActivity activity;


    public MainAdapter(@NonNull List<User> data, ResultActivity activity) {
        this.data = data;
        this.activity=activity;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_layout, parent, false);
        return new MainViewHolder(row);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        final User user = data.get(position);
        holder.getTextViewName().setText(user.getTitle()+" "+ user.getFirstName()+" "+user.getLastName());
        holder.getTextViewAddress().setText("");
        holder.getButtonCheckIn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   activity.goFriends(user);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder  {
        private Button buttonCheckIn;
        private TextView textViewName;
        private TextView textViewAddress;

        public MainViewHolder(View itemView) {
            super(itemView);
            buttonCheckIn = (Button)itemView.findViewById(R.id.buttonCheckIn);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewAddress = (TextView) itemView.findViewById(R.id.textViewAddress);
        }

        public TextView getTextViewName() {
            return textViewName;
        }

        public TextView getTextViewAddress() {
            return textViewAddress;
        }

        public Button getButtonCheckIn() {
            return buttonCheckIn;
        }


    }
}
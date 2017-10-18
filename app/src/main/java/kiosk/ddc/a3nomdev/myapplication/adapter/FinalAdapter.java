package kiosk.ddc.a3nomdev.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import kiosk.ddc.a3nomdev.myapplication.R;
import kiosk.ddc.a3nomdev.myapplication.ResultActivity;
import kiosk.ddc.a3nomdev.myapplication.model.Accompany;

/**
 * Created by 3nomdev on 10/18/17.
 */

public class FinalAdapter extends RecyclerView.Adapter<FinalAdapter.MainViewHolder> {
    private List<String> data;



    public FinalAdapter(@NonNull List<String> data) {
        this.data = data;

    }

    @Override
    public FinalAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.final_item_layout, parent, false);
        return new FinalAdapter.MainViewHolder(row);
    }

    @Override
    public void onBindViewHolder(FinalAdapter.MainViewHolder holder, int position) {

        holder.getTextViewName().setText(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder  {

        private TextView textViewName;


        public MainViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewFinalItem);

        }

        public TextView getTextViewName() {
            return textViewName;
        }

    }
}
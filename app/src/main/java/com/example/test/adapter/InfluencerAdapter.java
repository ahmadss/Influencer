package com.example.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.model.DataInfluencer;
import java.util.ArrayList;
import java.util.List;

public class InfluencerAdapter extends RecyclerView.Adapter<InfluencerAdapter.PendingAdapterViewHolder> {
    private List<DataInfluencer> pendingList = new ArrayList<>();
    private Context mContext;
    private static MyClickListener myClickListener;

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public class PendingAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtUsername,txtRate,txtType;
        public Button btnViewDetail;

        @Override
        public void onClick(View view) {
            myClickListener.onItemClick(getAdapterPosition(), view);
        }

        public PendingAdapterViewHolder(View itemView) {
            super(itemView);

            this.txtUsername  = itemView.findViewById(R.id.txtUsername);
            this.txtRate = itemView.findViewById(R.id.txtRate);
            this.txtType = itemView.findViewById(R.id.txtType);
            this.btnViewDetail = itemView.findViewById(R.id.btnViewDetail);


            this.btnViewDetail.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }
    }

    public InfluencerAdapter(List<DataInfluencer> list, Context context) {
        this.pendingList = list;
        mContext = context;
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public PendingAdapterViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_data_list, parent, false);
        PendingAdapterViewHolder mViewHolder = new PendingAdapterViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (final PendingAdapterViewHolder holder, final int position){
        DataInfluencer itemData = pendingList.get(position);

        String customerName = itemData.getName();
        int rate = itemData.getEngagement_rate();
        String bintang = "";
        for (int i=0; i< rate;i++){
            bintang += "*";
        }
        String category = /*itemData.getcategory()*/"category";

        holder.txtUsername.setText(customerName);
        holder.txtRate.setText(bintang);
        holder.txtType.setText(category);

    }

    @Override
    public int getItemCount () {
        return pendingList.size();
    }
}

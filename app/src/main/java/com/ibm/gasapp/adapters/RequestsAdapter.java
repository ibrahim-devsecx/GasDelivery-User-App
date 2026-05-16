package com.ibm.gasapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ibm.gasapp.R;
import com.ibm.gasapp.modules.Request;
import com.ibm.gasapp.interfaces.setOnClickListenerMap;

import java.util.ArrayList;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestsViewHolder> {

    ArrayList<Request> requestList;
    private setOnClickListenerMap listenerMap;
    Context context;

    public RequestsAdapter(Context context,ArrayList<Request> requestList, setOnClickListenerMap listenerMap) {
        this.context = context;
        this.requestList = requestList;
        this.listenerMap = listenerMap;
    }

    @NonNull
    @Override
    public RequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_request, parent, false);
        RequestsViewHolder requestsViewHolder = new RequestsViewHolder(view);
        return requestsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestsViewHolder holder, int position) {

        Request request = requestList.get(position);

        holder.tvDate.setText(request.getDate());
        holder.tvTime.setText(request.getTime());
        holder.tvRequestCase.setText(request.getRequestCase());

        if (request.getRequestCase().equals(context.getString(R.string.delivered))) {
            holder.tvRequestCase.setTextColor(ContextCompat.getColor(context, R.color.colorGreen));

        } else {
            holder.tvRequestCase.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        }

        holder.btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerMap.onClickMap(request.getLatitude(), request.getLongitude());
            }
        });

    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    class RequestsViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate, tvTime, tvRequestCase;
        ImageButton btnMap;

        public RequestsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.item_tv_date);
            tvTime = itemView.findViewById(R.id.item_tv_time);
            btnMap = itemView.findViewById(R.id.item_btn_map);
            tvRequestCase = itemView.findViewById(R.id.item_tv_request_case);
        }
    }
}

package com.example.contactapp3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapp3.R;
import com.example.contactapp3.model.ModelCalls;

import java.util.List;

public class CallsRvAdapter extends RecyclerView.Adapter<CallsRvAdapter.ViewHolder> {
   private LayoutInflater layoutInflater;
   private Context mContext;
    private List<ModelCalls> modelCallsList;
   public CallsRvAdapter(Context context, List<ModelCalls> callsList){
       modelCallsList = callsList;
       mContext = context;
   }
    @NonNull
    @Override
    public CallsRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       layoutInflater = LayoutInflater.from(mContext);
       View view = layoutInflater.inflate(R.layout.item_calls,parent,false);
       ViewHolder viewHolder = new ViewHolder(view);

       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CallsRvAdapter.ViewHolder holder, int position) {
        TextView name,duration,date;
        name = holder.name;
        duration = holder.duration;
        date = holder.date;

        name.setText(modelCallsList.get(position).getName());
        duration.setText(modelCallsList.get(position).getDuration());
        date.setText(modelCallsList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return modelCallsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,duration,date;
        public ViewHolder (View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.call_name);
            duration = itemView.findViewById(R.id.call_duration);
            date = itemView.findViewById(R.id.call_date);

        }
    }

}

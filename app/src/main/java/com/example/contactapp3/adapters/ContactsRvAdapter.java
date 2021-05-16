package com.example.contactapp3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapp3.R;
import com.example.contactapp3.model.ModelContact;

import java.util.List;

public class ContactsRvAdapter extends RecyclerView.Adapter<ContactsRvAdapter.ViewHolder> {
   private LayoutInflater layoutInflater;
   private List<ModelContact> modelContactList;
   private Context mContext;

   public ContactsRvAdapter(Context context, List<ModelContact> contactList) {
       mContext = context;
       modelContactList = contactList;
   }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_contacts,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView contact_name, contact_number;

        contact_name = holder.contact_name;
        contact_number = holder.contact_number;

        contact_name.setText(modelContactList.get(position).getName());
        contact_number.setText(modelContactList.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return modelContactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView contact_name, contact_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_number);
        }

    }
}

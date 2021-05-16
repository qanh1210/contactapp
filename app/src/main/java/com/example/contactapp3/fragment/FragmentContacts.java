package com.example.contactapp3.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapp3.R;
import com.example.contactapp3.adapters.ContactsRvAdapter;
import com.example.contactapp3.model.ModelContact;

import java.util.ArrayList;
import java.util.List;

public class FragmentContacts extends Fragment {
    private View view;
    private RecyclerView recyclerView;

    public FragmentContacts(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_contacts,container,false);

        recyclerView = view.findViewById(R.id.rv_contacts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;

        recyclerView.setLayoutManager(layoutManager);
        ContactsRvAdapter adapter = new ContactsRvAdapter(getContext(),getContacts());

        recyclerView.setAdapter(adapter);
        return view;
    }

    private List<ModelContact> getContacts(){
        List<ModelContact> contactList = new ArrayList<>();
        Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null,
                ContactsContract.Contacts.DISPLAY_NAME);


        cursor.moveToFirst();
        while(cursor.moveToNext()){
            contactList.add(new ModelContact(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))));
        }
        return contactList;
    }
}

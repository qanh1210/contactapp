package com.example.contactapp3.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapp3.R;
import com.example.contactapp3.adapters.CallsRvAdapter;
import com.example.contactapp3.model.ModelCalls;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentCalls extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private String DurationFormat;

    public FragmentCalls(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_calls,container,false);

        recyclerView = view.findViewById(R.id.rv_calls);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        CallsRvAdapter adapter = new CallsRvAdapter(getContext(),getCallLogs());
        recyclerView.setAdapter(adapter);
        return view;
    }

    private List<ModelCalls> getCallLogs(){
        List<ModelCalls> list = new ArrayList<>();

        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String [] {Manifest.permission.READ_CALL_LOG},1);
        }

        Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI,null,
                null,null,CallLog.Calls.DATE);

        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);

        int duration  = cursor.getColumnIndex(CallLog.Calls.DURATION);

        int date = cursor.getColumnIndex(CallLog.Calls.DATE);

        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm");

        String durationString = String.valueOf(duration);
        durationString = DurationFormat(durationString);
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            String dateString = formatter.format(new Date(Long.valueOf(cursor.getString(date))));
            //durationString = duration.DurationFormat(duration_);
            list.add(new ModelCalls(cursor.getString(number),durationString,dateString));
        }
        return list;
    }

    private String DurationFormat(String duration) {
        String durationFormatted=null;
        if(Integer.parseInt(duration) < 60){
            durationFormatted = "0 min " + duration + " secs";
        }
        else{
            int min = Integer.parseInt(duration)/60;
            int sec = Integer.parseInt(duration)%60;

            if(sec==0)
                durationFormatted = min + " mins" + " 0 sec" ;
            else
                durationFormatted = min + " mins " + sec + " secs";

        }
        return durationFormatted;
    }


}

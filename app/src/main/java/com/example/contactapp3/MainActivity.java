package com.example.contactapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.contactapp3.adapters.ViewPagerAdapter;
import com.example.contactapp3.fragment.FragmentCalls;
import com.example.contactapp3.fragment.FragmentContacts;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final int[] ICONS = {R.drawable.ic_baseline_call_24,R.drawable.ic_baseline_contacts_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentCalls(),"NHẬT KÝ");
        adapter.addFragment(new FragmentContacts(),"DANH BẠ");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        for(int i = 0; i < tabLayout.getTabCount(); i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setIcon(ICONS[i]);
        }

    }

    private void askPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
        == PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.READ_CONTACTS},1 );
            ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.READ_CALL_LOG},1 );
        }
    }

}
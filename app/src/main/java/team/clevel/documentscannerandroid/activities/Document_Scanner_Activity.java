package team.clevel.documentscannerandroid.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import team.clevel.documentscannerandroid.Docs_fragment;
import team.clevel.documentscannerandroid.Docs_fragment_;
import team.clevel.documentscannerandroid.Docs_fragmenttt;
import team.clevel.documentscannerandroid.QR_Fragment;
import team.clevel.documentscannerandroid.R;
import team.clevel.documentscannerandroid.TextRecognition;
import team.clevel.documentscannerandroid.fragmentAdapter.Document_Scanner_PagerAdapter;


public class Document_Scanner_Activity extends AppCompatActivity {

    private ViewPager viewPager;
    private Document_Scanner_PagerAdapter ds_adapter;
    private String[] tabs = {"DocS", "QRScanner"};
    private Toolbar toolbar;
    private TabLayout tabLayout;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_scanner_activity);

        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        Document_Scanner_PagerAdapter adapter = new Document_Scanner_PagerAdapter(getSupportFragmentManager());


        adapter.addFragment(new Docs_fragmenttt(), "DocS");
        adapter.addFragment(new QR_Fragment(), "QR");
        adapter.addFragment(new TextRecognition(), "Recognizer");


        viewPager.setAdapter(adapter);
    }
}
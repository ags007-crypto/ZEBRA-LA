package com.firstapp.zebra;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Videostudents extends AppCompatActivity {
RecyclerView recyclerView;
    ArrayList<DataSetList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_assingnments);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        arrayList=new ArrayList<DataSetList>();

        DataSetList dataSetList=new DataSetList("https://www.youtube.com/embed/FrwESEGJJ0Q");
        arrayList.add(dataSetList);
        dataSetList=new DataSetList("https://www.youtube.com/embed/Ka_H2dmWXlg");
        arrayList.add(dataSetList);

        dataSetList=new DataSetList("https://www.youtube.com/embed/usllfawONZk");
        arrayList.add(dataSetList);

        dataSetList=new DataSetList("https://www.youtube.com/embed/n7GKburfWE8");
        arrayList.add(dataSetList);

        dataSetList=new DataSetList("https://www.youtube.com/embed/WXj1f1Cl81g");
        arrayList.add(dataSetList);
        dataSetList=new DataSetList("https://www.youtube.com/embed/jUsFP0tzjbc");
        arrayList.add(dataSetList);
        dataSetList=new DataSetList("https://www.youtube.com/embed/VBgkoWFEGPg");
        arrayList.add(dataSetList);
        dataSetList=new DataSetList("https://www.youtube.com/embed/6D5Lp5cTYpM");
        arrayList.add(dataSetList);
        dataSetList=new DataSetList("https://www.youtube.com/embed/srRG-MtSyTg");
        arrayList.add(dataSetList);
        dataSetList=new DataSetList("https://www.youtube.com/embed/X3UXiCnvCCI");
        arrayList.add(dataSetList);
        dataSetList=new DataSetList("https://www.youtube.com/embed/Z0ojLOOxKi8");
        arrayList.add(dataSetList);

        YoutubeAdapter youtubeAdapter=new YoutubeAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(youtubeAdapter);
    }
}
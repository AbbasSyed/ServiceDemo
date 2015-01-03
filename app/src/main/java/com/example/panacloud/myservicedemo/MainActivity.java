package com.example.panacloud.myservicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by panacloud on 12/28/14.
 */

public class MainActivity extends Activity {
    ListView mListView;
    Button mButton;
    String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        mButton = (Button) findViewById(R.id.button);

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("http://stream.wmbr.org:8000/med");
        arrayList.add("http://marconi.emerson.edu:8000/wers");
        arrayList.add("http://audio4.radioreference.com/161117976.mp3");
        arrayList.add("http://brown.netexpress.com:8010/PALH");
        arrayList.add("http://audio4.radioreference.com/516866891.mp3");
        arrayList.add("http://relay.broadcastify.com:80/446184308");
        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        mListView.setAdapter(myAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                URL = myAdapter.getItem(position);

                Intent i = new Intent(MainActivity.this, MyService.class);
                i.putExtra("URL Stream",URL);

                //Starts a service by passing in an Intent with some data
                //multiple calls using startService will only call the same Service
                startService(i);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Stops a running Service associated with this Android Application(activity)
                stopService(new Intent(getApplicationContext(), MyService.class));
            }
        });

    }



}

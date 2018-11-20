package space.harbou.java.homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listView;
    private RecyclerView.LayoutManager manager;
    private String[] dataSet;
    private ArrayList<JSONObject> movies;
    private ArrayList<String> inbetween;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (RecyclerView) findViewById(R.id.list_view);
        listView.setHasFixedSize(true);

        manager = new LinearLayoutManager(this);

        Log.i("Main", "Trying to download");
        listView.setLayoutManager(manager);
        HttpConnector connector = new HttpConnector(listView, this);
        connector.execute();


    }
}

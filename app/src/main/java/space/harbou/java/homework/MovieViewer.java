package space.harbou.java.homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_viewer);
        Intent intent = getIntent();
        JSONObject jObj;
        String jmovie = intent.getStringExtra("Movie");
        Log.i("Movie", jmovie);
        try{
            jObj = new JSONObject(jmovie);
            ((TextView) findViewById(R.id.title)).setText(jObj.getString("Title").toString());
        } catch (JSONException e){}



    }
}

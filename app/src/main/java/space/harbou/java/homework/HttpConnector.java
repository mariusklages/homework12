package space.harbou.java.homework;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HttpConnector extends AsyncTask<Void, Void, Void> {

    private final static String MOVIE_API = "https://api.myjson.com/bins/nfvfi";

    private ArrayList<JSONObject> movies = new ArrayList<>();
    private ArrayList<String> inbetween = new ArrayList<>();
    RecyclerView listView;
    MyAdapter adapter;
    Context context;

    public HttpConnector(RecyclerView listView, Context context) { this.listView = listView; this.context = context;}

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try{
            for(JSONObject movie: movies){
                String title = movie.getString("Title");
                Log.i("Title",title);
                inbetween.add(title);
                Log.i("Title",movie.getString("Title") );
            }
        } catch(JSONException e){
            Log.e("JSON", "ERROR");
        };
        adapter = new MyAdapter(inbetween);
        listView.setAdapter(adapter);
        adapter.setClickListener(new MyAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i("onItemClick position: ", Integer.toString(position));
                Intent intent = new Intent(context, MovieViewer.class);
                intent.putExtra("Movie", movies.get(position).toString());
                context.startActivity(intent);
            }
        });

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Void doInBackground(Void... voids) {
        String result = null;
        InputStream in = null;
        URL url = null;

        Log.i("Connector", "Started downloading");
        try{
            url = new URL(MOVIE_API);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();


            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            result = sb.toString();
            JSONArray jmovies = new JSONArray(result);


            for (int i = 0; i < jmovies.length(); i++) {
                movies.add(jmovies.getJSONObject(i));
                Log.e("TITLE", jmovies.getJSONObject(i).getString("Title"));
            }


        } catch (MalformedURLException e) {
            Log.e("Url is not valid", MOVIE_API);
        } catch (IOException e) {
            Log.e("Http Task", "Internet connection problem");
            e.printStackTrace();
        } catch (JSONException e){
            Log.e("JSON", "Error while parsing movies");
        }
        return null;
    }

    public MyAdapter getAdapter(){
        return adapter;
    }

}

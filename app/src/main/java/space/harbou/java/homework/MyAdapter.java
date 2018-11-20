package space.harbou.java.homework;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by leo on 20.11.18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private static ClickListener clickListener;
    private ArrayList<String> mDataset;

    public MyAdapter(ArrayList<String> mDataset) {
        this.mDataset = mDataset;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView movieTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            movieTitle = itemView.findViewById(R.id.movie_title);
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(v.getContext(), movieTitle.getText(), Toast.LENGTH_SHORT).show();
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        public void setMovieTitle(String title) {
            movieTitle.setText(title);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.movie_list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String movieTitle = mDataset.get(position);
        holder.setMovieTitle(movieTitle);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public void setClickListener(ClickListener clickListener) {
        MyAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}

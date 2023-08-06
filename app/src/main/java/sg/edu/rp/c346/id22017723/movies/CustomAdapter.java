package sg.edu.rp.c346.id22017723.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<data> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<data> objects){
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.Title);
        TextView tvGenre = rowView.findViewById(R.id.genre);
        TextView tvYear = rowView.findViewById(R.id.year);
        ImageView ivRating = rowView.findViewById(R.id.rating);

        data currentItem = movieList.get(position);
        tvTitle.setText(currentItem.getTitle());
        tvGenre.setText(currentItem.getGenre());
        tvYear.setText(currentItem.getYear());
        ivRating.setImageResource(R.drawable.rating_g);

        if (currentItem.getRating().equalsIgnoreCase("g")){
            ivRating.setImageResource(R.drawable.rating_g);
        } else if (currentItem.getRating().equalsIgnoreCase("m18")) {
            ivRating.setImageResource(R.drawable.rating_m18);
        } else if (currentItem.getRating().equalsIgnoreCase("nc16")){
            ivRating.setImageResource(R.drawable.rating_nc16);
        } else if (currentItem.getRating().equalsIgnoreCase("pg")){
            ivRating.setImageResource(R.drawable.rating_pg);
        } else if (currentItem.getRating().equalsIgnoreCase("pg13")) {
            ivRating.setImageResource(R.drawable.rating_pg13);
        } else {
            ivRating.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }

}


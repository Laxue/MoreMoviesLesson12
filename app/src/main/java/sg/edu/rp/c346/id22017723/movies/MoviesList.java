package sg.edu.rp.c346.id22017723.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class MoviesList extends AppCompatActivity {

    Button btnPG;
    ListView lvMovies;
    ArrayList<data> al;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);


        btnPG = findViewById(R.id.btnShowPG13Movies);
        lvMovies = findViewById(R.id.lvMovies);
        al = new ArrayList<>();
        adapter = new CustomAdapter(this, R.layout.row, al);
        lvMovies.setAdapter(adapter);

        DBHelper db = new DBHelper(MoviesList.this);
        al = db.getMovies();
        db.close();

        adapter = new CustomAdapter(this, R.layout.row, al);
        lvMovies.setAdapter(adapter);



        btnPG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MoviesList.this);
                ArrayList<data> movies = db.getMoviesByRating("PG13");
                db.close();

                adapter = new CustomAdapter(MoviesList.this,R.layout.row,movies);
                lvMovies.setAdapter(adapter);
            }
        });


//            lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            data selectedMovie =  al.get(position);
//            // Start the ThirdActivity and pass the selected song ID as an extra
//            Intent intent = new Intent(MoviesList.this, EditActivity.class);
//            intent.putExtra("Movie", (Serializable) selectedMovie);
//            startActivity(intent);
//
//
//        }
//    });


}

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the ListView here
        DBHelper db = new DBHelper(MoviesList.this);
        al = db.getMovies();
        db.close();

        //ArrayAdapter adapter = new ArrayAdapter(SongActivity.this, android.R.layout.simple_list_item_1,al );
        adapter = new CustomAdapter(this, R.layout.row, al);
        lvMovies.setAdapter(adapter);
    }

}
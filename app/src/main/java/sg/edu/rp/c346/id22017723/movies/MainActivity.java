package sg.edu.rp.c346.id22017723.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etGenre, etYear;
    Spinner ratingSpinner;
    Button btnInsert, btnShow;

    ArrayList<data> al;
    ArrayAdapter<data> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.btnUpdate);
        btnShow = findViewById(R.id.btnDelete);
        ratingSpinner = findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.ratings_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ratingSpinner.setAdapter(spinnerAdapter);

        al = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                String yearStr = etYear.getText().toString().trim();
                int year = Integer.parseInt(yearStr);
                String rating = ratingSpinner.getSelectedItem().toString();

                // Insert movie into database
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertMovie(title, genre, year, rating);
                db.close();

                // Clear the input fields
                etTitle.setText("");
                etGenre.setText("");
                etYear.setText("");

                Toast.makeText(MainActivity.this, "Movie inserted!", Toast.LENGTH_LONG).show();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SecondActivity to show the list of movies
                Intent intent = new Intent(MainActivity.this, MoviesList.class);
                startActivity(intent);
            }
        });
    }
}
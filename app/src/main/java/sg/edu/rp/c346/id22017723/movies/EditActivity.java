package sg.edu.rp.c346.id22017723.movies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    Button btnUpdate, btnDelete;
    Button btnCancel;
    EditText etMovie, etGenre, etYear;
    Spinner ratingSpinner;
    data movie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        etMovie = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        ratingSpinner = findViewById(R.id.spinner);

        Intent i = getIntent();
        movie = (data) i.getSerializableExtra("movie");
        etMovie.setText(movie.getTitle());
        etGenre.setText(movie.getGenre());
        etYear.setText(""+movie.getYear());
        ratingSpinner.getSelectedItem();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.ratings_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ratingSpinner.setAdapter(spinnerAdapter);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);

                // Get user input
                String title = etMovie.getText().toString();
                String genre = etGenre.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                String rating = ratingSpinner.getSelectedItem().toString();

                // Update movie information in the database
                movie.setTitle(title);
                movie.setGenre(genre);
                movie.setYear(year);
                movie.setRating(rating);

                int result = dbh.updateMovie(movie);

                if (result > 0) {
                    Toast.makeText(EditActivity.this, "Movie updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditActivity.this, "Failed to update movie", Toast.LENGTH_SHORT).show();
                }

                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setTitle("Danger");
                builder.setMessage("Are you sure you want to delete the movie '" + movie.getTitle() + "'?");
                builder.setCancelable(false);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Delete the movie from the database and finish the activity
                        DBHelper dbh = new DBHelper(EditActivity.this);
                        dbh.deleteMovie(movie.getId());
                        dbh.close();
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Close the dialog
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mybuilder = new AlertDialog.Builder(EditActivity.this);
                mybuilder.setTitle("Danger");
                mybuilder.setMessage("Are you sure you want to discard the changes?");
                mybuilder.setCancelable(false);
                mybuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Delete the movie from the database and finish the activity
                        finish();
                    }
                });
                mybuilder.setPositiveButton("Do not discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Close the dialog
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = mybuilder.create();
                dialog.show();
            }
        });



    }


}
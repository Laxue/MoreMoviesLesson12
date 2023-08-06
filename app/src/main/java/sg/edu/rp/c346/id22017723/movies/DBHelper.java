package sg.edu.rp.c346.id22017723.movies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "moviesList.db";
    private static final String TABLE_MOVIES = "moviesList";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_MOVIES +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_GENRE + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_RATING + " INTEGER )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        // Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        // Create table(s) again
//        onCreate(db);
        db.execSQL("ALTER TABLE " + TABLE_MOVIES + " ADD COLUMN module_name TEXT ");
    }

    public void insertMovie(String title, String genre, int year, String rating) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the title as value
        values.put(COLUMN_TITLE, title);
        // Store the column name as key and the singer as value
        values.put(COLUMN_GENRE, genre);
        // Store the column name as key and the year as value
        values.put(COLUMN_YEAR, year);
        // Store the column name as key and the star as value
        values.put(COLUMN_RATING, rating);
        // Insert the row into the TABLE_SONG
        db.insert(TABLE_MOVIES, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<data> getMovies() {
        ArrayList<data> movies = new ArrayList<data>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        Cursor cursor = db.query(TABLE_MOVIES, columns, null, null, null, null, COLUMN_TITLE, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                data obj = new data(id, title, year, rating, genre);
                movies.add(obj);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return movies;
    }

    public ArrayList<data> getMoviesByRating(String rating) {
        ArrayList<data> movies = new ArrayList<data>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        String selection = COLUMN_RATING + " = ?";
        String[] selectionArgs = {rating};
        Cursor cursor = db.query(TABLE_MOVIES, columns, selection, selectionArgs, null, null, COLUMN_TITLE, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String movieRating = cursor.getString(4);
                data obj = new data(id, title, year, movieRating, genre);
                movies.add(obj);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return movies;
    }

//    public void updateSong(Song song) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_TITLE, song.getTitle());
//        values.put(COLUMN_SINGER, song.getSingers());
//        values.put(COLUMN_YEAR, song.getYear());
//        values.put(COLUMN_STAR, song.getStar());
//        String[] args = {String.valueOf(song.getId())};
//        db.update(TABLE_SONG, values, COLUMN_ID, args);
//        db.close();
//    }

    public int updateMovie(data movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_GENRE, movie.getGenre());
        values.put(COLUMN_YEAR, movie.getYear());
        values.put(COLUMN_RATING, movie.getRating());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(movie.getId())};
        int result = db.update(TABLE_MOVIES, values, condition, args);
        db.close();
        return result;
    }

    public int deleteMovie(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIES, condition, args);
        db.close();
        return result;
    }

}
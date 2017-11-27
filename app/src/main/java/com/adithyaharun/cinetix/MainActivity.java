package com.adithyaharun.cinetix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Spinner spMovie, spClass, spInclude;
    EditText edtQty;
    Button btnBook;

    ArrayList<String> movieList = new ArrayList<>();
    ArrayList<String> classList = new ArrayList<>();
    ArrayList<String> includeList = new ArrayList<>();

    String selectedMovie;
    Integer selectedClass, qty;
    Boolean selectedInclude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup controls.
        setupControls();

        // Add movies.
        Calendar calendar = Calendar.getInstance();
        Integer day = calendar.get(Calendar.DAY_OF_WEEK);
        setupMovieList(day);

        // Add classes.
        classList.add("Regular");
        classList.add("Executive");

        // Add includes.
        includeList.add("No");
        includeList.add("Popcorn + Minuman");

        // Create adapter for spMovie
        ArrayAdapter<String> movieAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, movieList);

        // Create adapter for spClass
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, classList);

        // Create adapter for spInclude
        ArrayAdapter<String> includeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, includeList);

        // Append adapters to spinners.
        spClass.setAdapter(classAdapter);
        spInclude.setAdapter(includeAdapter);
        spMovie.setAdapter(movieAdapter);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processBook();
            }
        });

        spMovie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMovie = movieList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedClass = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spInclude.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedInclude = (i == 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void processBook() {
        if (edtQty.getText().length() < 1) {
            edtQty.requestFocus();
            edtQty.setError("Jumlah tiket wajib diisi.");
        } else if (Integer.valueOf(edtQty.getText().toString()) > 5) {
            edtQty.requestFocus();
            edtQty.setError("Maksimum tiket yang bisa dipesan adalah 5 tiket.");
        } else if (Integer.valueOf(edtQty.getText().toString()) < 1) {
            edtQty.requestFocus();
            edtQty.setError("Anda tidak dapat memesan tiket dengan jumlah kosong.");
        } else {
            qty = Integer.valueOf(edtQty.getText().toString());
            Intent bookIntent = new Intent(MainActivity.this, ReviewActivity.class);

            bookIntent.putExtra("selectedMovie", selectedMovie);
            bookIntent.putExtra("selectedClass", selectedClass);
            bookIntent.putExtra("selectedInclude", selectedInclude);
            bookIntent.putExtra("qty", qty);

            startActivity(bookIntent);
        }
    }

    private void setupControls() {
        // Buttons.
        btnBook = (Button) findViewById(R.id.btnBook);

        // Editable Texts.
        edtQty = (EditText) findViewById(R.id.edtQty);
        edtQty.setText("1");

        // Spinners.
        spClass = (Spinner) findViewById(R.id.spClass);
        spMovie = (Spinner) findViewById(R.id.spMovie);
        spInclude = (Spinner) findViewById(R.id.spInclude);
    }

    private void setupMovieList(int day) {
        switch (day) {
            case Calendar.SUNDAY:
                movieList.add("Dunkirk");
                movieList.add("Despicable Me 3");
                movieList.add("Ghost In The Shell");
                movieList.add("Annabelle: Creation");
                movieList.add("Atomic Blonde");
                movieList.add("Baby Driver");
                break;

            case Calendar.MONDAY:
                movieList.add("Detroit");
                movieList.add("The Emoji Movie");
                movieList.add("Girls Trip");
                movieList.add("The Nut Job 2: Nutty By Nature");
                movieList.add("Spider-Man: Homecoming");
                movieList.add("War for the Planet of the Apes");
                break;

            case Calendar.TUESDAY:
                movieList.add("Wish Upon");
                movieList.add("Warkop DKI Reborn: Jangkrik Boss Part 2");
                movieList.add("Wonder Woman");
                movieList.add("Game of Thrones Season V");
                movieList.add("Love Live! Sunshine");
                movieList.add("Your Name.");
                break;

            case Calendar.WEDNESDAY:
                movieList.add("Valerian and the City of a Thousand Planets");
                movieList.add("Star Wars: The Last Jedi");
                movieList.add("Avengers: Infinity War");
                movieList.add("Justice League");
                movieList.add("King Arthur: Legend of the Sword");
                movieList.add("Wolf Warriors II");
                break;

            case Calendar.THURSDAY:
                movieList.add("The Ottoman Lieutenant");
                movieList.add("Descendants 2");
                movieList.add("The Lost City of Z");
                movieList.add("Transformers: The Last Knight");
                movieList.add("Shin Godzilla");
                movieList.add("Get Out");
                break;

            case Calendar.FRIDAY:
                movieList.add("Rogue Warrior: Robot Fighter");
                movieList.add("The Dark Tower");
                movieList.add("xXx: Return of Xander Cage");
                movieList.add("Guardians of the Galaxy Vol. 2");
                movieList.add("Call Me by Your Name");
                movieList.add("The Wall");
                break;

            case Calendar.SATURDAY:
                movieList.add("Sword Art Online: Ordinal Scale");
                movieList.add("Stranger Things");
                movieList.add("Gangnam Blues");
                movieList.add("Super Dark Times");
                movieList.add("The Hitman's Bodyguard");
                movieList.add("Goodbye Christopher Robin");
                break;

            default:
                break;
        }
    }
}

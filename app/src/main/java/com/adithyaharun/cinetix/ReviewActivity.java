package com.adithyaharun.cinetix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class ReviewActivity extends AppCompatActivity {

    TextView txtTitle, txtQty, txtClass, txtInclude, txtTotal;
    Button btnShare;

    String selectedMovie;
    Integer selectedClass, qty, total;
    Boolean selectedInclude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        // Setup controls.
        setupControls();

        // Get intent extras.
        getExtras();

        // Show datas.
        processData();

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareText = "Hore! Saya telah berhasil memesan tiket film \"" + selectedMovie + "\" senilai Rp. " + formatNumber(total) + " dengan menggunakan aplikasi " + getString(R.string.app_name) + ".";
                Intent shareIntent = new Intent();

                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });
    }

    private void processData() {
        String qtyText = String.valueOf(qty) + " Tiket";
        txtTitle.setText(selectedMovie);
        txtQty.setText(qtyText);
        txtClass.setText((selectedClass == 0 ? "Reguler" : "Executive"));
        txtInclude.setText((selectedInclude ? "Popcorn + Minuman" : "Tidak Ada"));

        total = (selectedClass == 0 ? 50000 : 75000) * qty;
        total += (selectedInclude ? 30000 : 0);

        txtTotal.setText(formatNumber(total));
    }

    private String formatNumber(int val) {
        return NumberFormat.getInstance(Locale.ENGLISH).format(val);
    }

    private void setupControls() {
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtQty = (TextView) findViewById(R.id.txtQty);
        txtClass = (TextView) findViewById(R.id.txtClass);
        txtInclude = (TextView) findViewById(R.id.txtInclude);
        txtTotal = (TextView) findViewById(R.id.txtTotal);

        btnShare = (Button) findViewById(R.id.btnShare);
    }

    private void getExtras() {
        Intent myIntent = getIntent();

        selectedMovie = myIntent.getStringExtra("selectedMovie");
        selectedClass = myIntent.getIntExtra("selectedClass", 9);
        selectedInclude = myIntent.getBooleanExtra("selectedInclude", false);
        qty = myIntent.getIntExtra("qty", 1);
    }
}

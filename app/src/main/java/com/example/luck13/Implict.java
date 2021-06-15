package com.example.luck13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URL;

public class Implict extends AppCompatActivity {

    Button btnShare;
    Button btnAlarm;
    EditText etJam, etMenit, etDesc;

    final int REQUEST_CODE = 111;
    Uri imageUri;
    private Button btnfoto;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implict);

        btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("Text/plain");
                i.putExtra(Intent.EXTRA_TEXT, "Praktikum Mobile Implict Intent");
                startActivity(i.createChooser(i,"Share"));
            }
        });

        etJam = findViewById(R.id.jam);
        etMenit = findViewById(R.id.menit);
        etDesc = findViewById(R.id.desc);

        btnAlarm = findViewById(R.id.btnAlarm);
        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jam = Integer.parseInt(etJam.getText().toString());
                int menit = Integer.parseInt(etMenit.getText().toString());
                String desc = String.valueOf(etDesc.getText());

                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_HOUR, jam);
                i.putExtra(AlarmClock.EXTRA_MINUTES, menit);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, desc);

                if (jam <= 24 && menit <= 60) {
                    startActivity(i);
                } else
                    Toast.makeText(getApplicationContext(), "Waktu Salah", Toast.LENGTH_SHORT).show();
            }
        });

        btnfoto = findViewById(R.id.btnFoto);
        imageView = findViewById(R.id.hasilfoto);

        btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);

            if(resultcode == RESULT_OK)
            {
                imageUri = data.getData();
                imageView.setImageURI(imageUri);
            }
    }
}
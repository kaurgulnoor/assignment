package com.example.assignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.assignment.databinding.ActivityMainBinding;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    public EditText phoneNumberEditText;
    public ImageView profileImageView;
    private Button callButton;
    private Button changePictureButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        phoneNumberEditText = findViewById(R.id.editTextPhone);
        profileImageView = findViewById(R.id.imageView);
        callButton = findViewById(R.id.button2);
        changePictureButton = findViewById(R.id.button3);

        // Retrieve email address from previous activity
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("Email address");

        // Implement call functionality
        callButton.setOnClickListener(v -> {
            String phoneNumber = phoneNumberEditText.getText().toString();
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        });

        // Implement change picture functionality
        changePictureButton.setOnClickListener(v -> {
            // Open camera activity to take a new picture
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == YOUR_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                if (bitmap != null) {
                    saveBitmapToInternalStorage(bitmap);
                }
            }
        }
    }

    private void saveBitmapToInternalStorage(Bitmap bitmap) {
        FileOutputStream fOut = null;
        try {
            fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fOut != null) {
                try {
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = null;
        // Check if the file exists
        File file = new File(getFilesDir(), "Picture.png");
        if (file.exists()) {
            // Load the bitmap from the file
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            // Set the bitmap to the ImageView
            imageView.setImageBitmap(bitmap);
        }
    }

}
package com.example.assignment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;
    TextView myText = variableBinding.textView;
    Button btn = variableBinding.button;
    EditText myedit = variableBinding.myedittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        MutableLiveData<Boolean> isSelected = new MutableLiveData<>();

        isSelected.observe(this, selected -> {
            variableBinding.checkBox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);

            Toast.makeText(MainActivity.this, "The value is now: "+selected, Toast.LENGTH_SHORT).show();
        });

        variableBinding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) ->
                isSelected.postValue(isChecked));

        variableBinding.radioButton.setOnCheckedChangeListener((buttonView, isChecked) ->
                isSelected.postValue(isChecked));

        variableBinding.switch1.setOnCheckedChangeListener((buttonView, isChecked) ->
                isSelected.postValue(isChecked));

        variableBinding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display(v);
            }
        });

    }
    public void display(View v) {
        Toast.makeText(MainActivity.this, "ImageView Clicked", Toast.LENGTH_SHORT).show();
    }
}
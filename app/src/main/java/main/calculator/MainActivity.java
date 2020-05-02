package main.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCalculator = findViewById(R.id.buttonSimpleCalculator);
        buttonCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalculator();
            }
        });
    }

    public void openCalculator(){
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }
}

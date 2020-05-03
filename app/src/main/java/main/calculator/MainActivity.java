package main.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonSimpleCalculator;
    private Button buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSimpleCalculator = findViewById(R.id.buttonSimpleCalculator);
        buttonSimpleCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalculator();
            }
        });

        buttonExit = findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });

    }

    public void openCalculator(){
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    private void exit(){
        finishAffinity();
        System.exit(0);
    }
}

package koreanfoodreview.C346.tantynnwei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText etName,etDesc,etLocation;
    Button btnInsert,btnSL;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        etLocation = findViewById(R.id.etLocation);
        btnInsert = findViewById(R.id.btnIns);
        btnSL = findViewById(R.id.btnSL);
        rb = findViewById(R.id.ratingBar);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString().trim();
                String desc = etDesc.getText().toString().trim();
                String location = etLocation.getText().toString().trim();
                if (name.length() == 0 || desc.length() == 0 || location.length() == 0){
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                int stars = (int) rb.getRating();

                DBHelper dbh = new DBHelper(MainActivity.this);
                long result = dbh.insertFood(name, desc, location, stars);

                if (result != -1) {
                    Toast.makeText(MainActivity.this, "Food inserted", Toast.LENGTH_LONG).show();
                    etName.setText("");
                    etDesc.setText("");
                    etLocation.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Insert failed", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,foodList.class);
                startActivity(intent);
            }
        });

    }
}
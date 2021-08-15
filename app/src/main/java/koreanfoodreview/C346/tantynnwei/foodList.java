package koreanfoodreview.C346.tantynnwei;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class foodList extends AppCompatActivity {

    ListView lv;
    ArrayList<Food> foodArrayList;
    Button btn5Stars;
    CustomAdapter adapter;
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        foodArrayList.clear();
        foodArrayList.addAll(dbh.getAllSongs(""));
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);


        lv = (ListView) this.findViewById(R.id.lv);
        btn5Stars = (Button) this.findViewById(R.id.btnShow5);

        DBHelper dbh = new DBHelper(this);
        foodArrayList = dbh.getAllFood("");
        dbh.close();

        adapter = new CustomAdapter(this, R.layout.row, foodArrayList);
        lv.setAdapter(adapter);


        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(foodList.this);
                foodArrayList.clear();
                foodArrayList.addAll(dbh.getAllFoodsByStars(5));
                adapter.notifyDataSetChanged();
            }
        });

    }
}
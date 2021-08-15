package koreanfoodreview.C346.tantynnwei;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Food> foodArrayList;
    TextView tvName, tvDesc, tvLocation;

    public CustomAdapter(Context context, int resource, ArrayList<Food> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        foodArrayList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvDesc = rowView.findViewById(R.id.tvDesc);
        TextView tvLocation = rowView.findViewById(R.id.tvLocation);

        ImageView imageView = rowView.findViewById(R.id.imageView);

        RatingBar ratingBar2 = rowView.findViewById(R.id.ratingBar2);

        // Obtain the Android Version information based on the position
        Food curentFood = foodArrayList.get(position);

        // Set values to the TextView to display the corresponding information
        tvName.setText(curentFood.getName());
        tvDesc.setText(curentFood.getDesc());
        tvLocation.setText(curentFood.getLocation());
        String stars = "";
        for(int i = 0; i < curentFood.getStars(); i++){
            stars += " * ";
        }
        //tvStars.setText(stars);

        ratingBar2.setRating(curentFood.getStars());


        if(curentFood.getStars() == 5){
            imageView.setImageResource(R.drawable.korea);
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        return rowView;
    }

}

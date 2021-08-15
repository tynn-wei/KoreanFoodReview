package koreanfoodreview.C346.tantynnwei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "food.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FOOD = "food";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESC = "description";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTableSQL ="CREATE TABLE " + TABLE_FOOD +"("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESC + " TEXT,"
                + COLUMN_LOCATION + " TEXT,"
                + COLUMN_STARS + " FLOAT)";
        db.execSQL(createTableSQL);
        Log.i("info","created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        // Create table(s) again
        onCreate(db);

    }

    public long insertFood(String name, String desc, String location, float stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESC,desc);
        values.put(COLUMN_LOCATION,location);
        values.put(COLUMN_STARS,stars);
        long result = db.insert(TABLE_FOOD, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Food> getAllFood(String rating) {
        ArrayList<Food> food = new ArrayList<Food>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME +  "," + COLUMN_DESC + "," + COLUMN_LOCATION + "," + COLUMN_STARS + " FROM " + TABLE_FOOD
                + " ORDER BY " + COLUMN_STARS + " DESC ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String foodName = cursor.getString(1);
                String foodDesc = cursor.getString(2);
                String foodLocation = cursor.getString(3);
                float foodStars = cursor.getFloat(4);
                Food food1 = new Food(id,foodName,foodDesc,foodLocation,foodStars);
                food.add(food1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return food;
    }

    public int updateSong(Food data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_DESC,data.getDesc());
        values.put(COLUMN_LOCATION,data.getDesc());
        values.put(COLUMN_STARS,data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_FOOD, values, condition, args);
        if (result < 1) {
            Log.d("DBHelper", "Update failed");
        }
        db.close();
        return result;
    }

    public int deleteSong(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_FOOD, condition, args);
        db.close();
        return result;
    }

    public ArrayList<Food> getAllSongs(String keyword) {
        ArrayList<Food> foods = new ArrayList<Food>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID,COLUMN_NAME,COLUMN_DESC,COLUMN_LOCATION, COLUMN_STARS};
        String condition = COLUMN_STARS + " = 5";
        Cursor cursor = db.query(TABLE_FOOD, columns, condition,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                String location = cursor.getString(3);
                float stars = cursor.getFloat(4);

                Food note = new Food( name, desc,  location, stars);
                foods.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foods;
    }

    public ArrayList<Food> getAllFoodsByStars(int starsFilter) {
        ArrayList<Food> foodList = new ArrayList<Food>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_DESC, COLUMN_LOCATION, COLUMN_STARS};
        String condition = COLUMN_STARS + ">= ?";
        String[] args = {String.valueOf(starsFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_FOOD, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                String location = cursor.getString(3);
                int stars = cursor.getInt(4);

                Food newFood = new Food(id, name, desc, location, stars);
                foodList.add(newFood);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return foodList;
    }
}

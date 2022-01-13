package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Car;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //SQL - Structured Query Language
        String CREATE_CARS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + "INTEGER PRIMARY KEY, "
                + Util.KEY_NAME + "TEXT, "
                + Util.KEY_PRICE + "TEXT" + ");";

        sqLiteDatabase.execSQL(CREATE_CARS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //CRUD
    //Create, Read, Update, Delete

    public void addCar(Car car) { //метод добавления машины в БД
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase(); //строка созд-я переменной класса склбд, позволяющий записывать в бд

        ContentValues contentValues = new ContentValues(); //класс кв, позволяющий взаимодействовать с бд
        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());

        sqLiteDatabase.insert(Util.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public Car getCar(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Util.TABLE_NAME, new String[] {Util.KEY_ID, Util.KEY_NAME,
                Util.KEY_PRICE}, Util.KEY_ID + "=?", new String[] {String.valueOf(id)},
                null, null,
                null, null);
        Car car = new Car();
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                car = new Car(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2));
            } finally {
                cursor.close();
            }

        }

        return car;
    }

    public List<Car> getAllCars() { //List - интерфейс, наследуемый ArrayList'ом. Своего рода абстракция. Поставлен он, т.к. можно вернуть не только ЭррейЛист, но и Stack, например
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        List<Car> carsList = new ArrayList<>();

        String selectAllCars = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(selectAllCars, null);
        if (cursor.moveToFirst()) {
            try {
                do {
                    Car car = new Car();
                    car.setId(Integer.parseInt(cursor.getString(0)));
                    car.setName(cursor.getString(1));
                    car.setPrice(cursor.getString(2));

                    carsList.add(car);
                } while (cursor.moveToNext());
            } finally {
                    cursor.close();
                }

            }

        return carsList;
    }

    public int updateCar(Car car) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());

        return sqLiteDatabase.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?",
                new String [] {String.valueOf(car.getId())});

    }

    public void deleteCar (Car car) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String [] {String.valueOf(car.getId())});

        sqLiteDatabase.close();
    }

    public int getCarsCount () {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor.getCount();
    }
}
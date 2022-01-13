package com.example.carsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DatabaseHandler;
import Model.Car;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);

        databaseHandler.addCar(new Car("Toyota", "30 000 $"));
        databaseHandler.addCar(new Car("BMW", "45 000 $"));
        databaseHandler.addCar(new Car("Audi", "50 000 $"));
        databaseHandler.addCar(new Car("Fiat", "35 000 $"));

        List<Car> carList = databaseHandler.getAllCars();

        for (Car car : carList) {
            Log.i("Car info: ", "ID " + car.getId() + ", name " + car.getName() + ", price " + car.getPrice());
        }
    }
}
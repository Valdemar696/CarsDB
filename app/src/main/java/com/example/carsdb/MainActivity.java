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

        Log.d("CarsCount", String.valueOf(databaseHandler.getCarsCount()));

/*        databaseHandler.addCar(new Car("Toyota", "30 000 $"));
        databaseHandler.addCar(new Car("BMW", "45 000 $"));
        databaseHandler.addCar(new Car("Audi", "50 000 $"));
        databaseHandler.addCar(new Car("Fiat", "35 000 $"));
        databaseHandler.addCar(new Car("Camry 3.5", "69 696 $"));
        databaseHandler.addCar(new Car("Honda", "33 000 $"));
        databaseHandler.addCar(new Car("Nissan", "38 000 $"));
        databaseHandler.addCar(new Car("Subaru", "46 000 $"));*/

        List<Car> carList = databaseHandler.getAllCars();

/*        Car deletingCar = databaseHandler.getCar(8);
        databaseHandler.deleteCar(deletingCar);*/

        for (Car car : carList) {
            Log.d("CarInfo:", "ID " + car.getId() +
                    ", name " + car.getName() +
                    ", price " + car.getPrice());
        }


        /*Car car = databaseHandler.getCar(2);
        Log.d("CarInfo:", "ID " + car.getId() +
                ", name " + car.getName() +
                ", price " + car.getPrice());

        car.setName("Tesla");
        car.setPrice("50 000 $");

        int updatedCarId = databaseHandler.updateCar(car);

        Log.d("CarInfo:", "ID " + car.getId() +
                ", name " + car.getName() +
                ", price " + car.getPrice() + ", updatedCarId " + updatedCarId);*/

    }
}
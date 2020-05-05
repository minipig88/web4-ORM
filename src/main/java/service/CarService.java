package service;

import DAO.CarDao;
import model.Car;

import java.util.List;

public class CarService {
    private static CarService carService;

    private CarService() {
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService();
        }
        return carService;
    }

    public boolean addNewCar(String brand, String model, String licensePlate, Long price) {
        if (CarDao.getInstance().getCountBrandCars(brand) < 10) {
            CarDao.getInstance().addNewCar(brand, model, licensePlate, price);
            return true;
        }
        return false;
    }

    public boolean buyCar(String brand, String model, String licensePlate) {
        Car car = CarDao.getInstance().getCar(brand, model, licensePlate);
        if (car != null) {
            CarDao.getInstance().deleteCar(brand, model, licensePlate);
            AllDailyReportService.salesReport(car.getPrice());
            return true;
        }
        return false;
    }

    public List<Car> getAllCars() {
        return CarDao.getInstance().getAllCars();
    }

    public void deleteAllCars() {
        CarDao.getInstance().deleteAllCars();
    }

}

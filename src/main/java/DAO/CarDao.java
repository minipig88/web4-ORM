package DAO;

import model.Car;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import javax.transaction.Transactional;
import java.util.List;

public class CarDao {
    private static CarDao carDao;
    private SessionFactory sessionFactory;

    private CarDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarDao getInstance() {
        if (carDao == null) {
            carDao = new CarDao(DBHelper.getSessionFactory());
        }
        return carDao;
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }

    @Transactional
    public void addNewCar(String brand, String model, String licensePlate, Long price) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Car newCar = new Car(brand, model, licensePlate, price);
        session.save(newCar);
        transaction.commit();
        session.close();
    }

    @Transactional
    public void deleteCar(String brand, String model, String licensePlate) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Car WHERE brand=:paramBrand AND model=:paramModel AND licensePlate=:paramLicense");
        query.setParameter("paramBrand", brand);
        query.setParameter("paramModel", model);
        query.setParameter("paramLicense", licensePlate);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Transactional
    public List<Car> getAllCars() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        List<Car> list = session.createQuery("FROM Car").list();
        transaction.commit();
        session.close();
        return list;
    }

    @Transactional
    public long getCountBrandCars(String brand) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT COUNT(brand) FROM Car WHERE brand=:paramBrand");
        query.setParameter("paramBrand", brand);
        long countBrand = (long) query.uniqueResult();
        transaction.commit();
        session.close();
        return countBrand;
    }

    @Transactional
    public Car getCar(String brand, String model, String licensePlate) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Car WHERE brand=:paramBrand AND model=:paramModel AND licensePlate=:paramLicense");
        query.setParameter("paramBrand", brand);
        query.setParameter("paramModel", model);
        query.setParameter("paramLicense", licensePlate);
        Car car = (Car) query.uniqueResult();
        transaction.commit();
        session.close();
        return car;
    }

    @Transactional
    public void deleteAllCars() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Car").executeUpdate();
        transaction.commit();
        session.close();
    }
}

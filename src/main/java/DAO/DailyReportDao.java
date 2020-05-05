package DAO;

import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import javax.transaction.Transactional;
import java.util.List;

public class DailyReportDao {
    private static DailyReportDao dailyReportDao;
    private SessionFactory sessionFactory;

    private DailyReportDao( SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DailyReportDao getInstance() {
        if (dailyReportDao == null) {
            dailyReportDao = new DailyReportDao(DBHelper.getSessionFactory());
        }
        return dailyReportDao;
    }

    @Transactional
    public void createDailyReport(long money, long count) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new DailyReport(money, count));
        transaction.commit();
        session.close();
    }

    @Transactional
    public List<DailyReport> getAllDailyReport() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return dailyReports;
    }

    @Transactional
    public DailyReport getLastReport() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        DailyReport lastReport = (DailyReport) session.createQuery(
                "FROM DailyReport WHERE id=(SELECT MAX(id) FROM DailyReport)").uniqueResult();
        transaction.commit();
        session.close();
        return lastReport;
    }

    @Transactional
    public void deleteAllReports() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM DailyReport").executeUpdate();
        transaction.commit();
        session.close();
    }
}

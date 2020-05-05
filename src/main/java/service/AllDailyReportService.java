package service;

import DAO.DailyReportDao;
import model.DailyReport;

import java.util.List;

public class AllDailyReportService {
    private static AllDailyReportService dailyReportService;
    private static long countSoldCar;
    private static long sumSoldCar;
    private static DailyReport lastDayReport;

    private AllDailyReportService() {
    }

    public static AllDailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new AllDailyReportService();
        }
        return dailyReportService;
    }

    protected static void salesReport(long priceSoldCar) {
        countSoldCar++;
        sumSoldCar += priceSoldCar;
    }

    public void createDailyReport() {
        lastDayReport = new DailyReport(sumSoldCar, countSoldCar);
        DailyReportDao.getInstance().createDailyReport(sumSoldCar, countSoldCar);
        sumSoldCar = 0L;
        countSoldCar = 0L;
    }

    public List<DailyReport> getAllDailyReports() {
        return DailyReportDao.getInstance().getAllDailyReport();
    }

    public DailyReport getLastReport() {
        return lastDayReport;
    }

    public void deleteAllReports() {
        DailyReportDao.getInstance().deleteAllReports();
    }

}

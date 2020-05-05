package servlet;

import service.AllDailyReportService;
import service.CarService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDataAndReport extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        CarService.getInstance().deleteAllCars();
        AllDailyReportService.getInstance().deleteAllReports();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}

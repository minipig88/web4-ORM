import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.LastDailyServlet;
import servlet.*;

public class Main {

    public static void main(String[] args) throws Exception {
        ProducerServlet producerServlet = new ProducerServlet();
        AllDailyReportServlet allDailyReportServlet = new AllDailyReportServlet();
        LastDailyServlet lastDailyServlet = new LastDailyServlet();
        DeleteDataAndReport deleteDataAndReport = new DeleteDataAndReport();
        CustomerServlet customerServlet = new CustomerServlet();
        NewDayServlet newDayServlet = new NewDayServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(producerServlet), "/producer");
        context.addServlet(new ServletHolder(deleteDataAndReport), "/report");
        context.addServlet(new ServletHolder(allDailyReportServlet), "/report/all");
        context.addServlet(new ServletHolder(lastDailyServlet),"/report/last");
        context.addServlet(new ServletHolder(customerServlet), "/customer");
        context.addServlet(new ServletHolder(newDayServlet), "/newday");

        ResourceHandler resource_handler = new ResourceHandler();

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        server.join();

    }
}

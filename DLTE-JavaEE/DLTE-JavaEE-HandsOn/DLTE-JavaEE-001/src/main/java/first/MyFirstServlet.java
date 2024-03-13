package first;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="MyFirstServlet",value = "/first/api/")
public class MyFirstServlet extends HttpServlet {
    Logger logger;
    @Override
    public void destroy() {
        logger.info("Destroy Servelet has inititialized");
    }

    @Override
    public void init() throws ServletException {
        logger= LoggerFactory.getLogger(MyFirstServlet.class);
        logger.info("Servelet has inititialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(" Get Servelet has inititialized");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(" POst Servelet has inititialized");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Put Servelet has inititialized");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Delete Servelet has inititialized");
    }
}

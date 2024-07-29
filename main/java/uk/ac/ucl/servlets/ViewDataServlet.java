package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.List;

@WebServlet("/table.html")
public class ViewDataServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Model model = ModelFactory.getModel();
        List<Map<String, String>> allData = model.getAllData();
        List<String> columnNames = model.getColumnNames();
        request.setAttribute("columnNames", columnNames);
        request.setAttribute("data", allData);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/table.jsp");
        dispatch.forward(request, response);
    }
}

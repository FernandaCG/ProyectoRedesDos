package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 * @author fernanda
 */
@WebServlet(name = "ServletArchivo", urlPatterns = {"/ServletArchivo"})
public class ServletArchivo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("img");
        // String name = "5.jpg";
        File file = new File("/home/fernanda/Documentos/Redes2/proyecto/img/" + name);
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        try {
            Socket soc = new Socket("192.168.2.81", 2004);
            OutputStream dout = soc.getOutputStream();
            int count;
            while ((count = in.read(bytes)) > 0) {
                dout.write(bytes, 0, count);
            }
            BufferedReader ina = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String fromClient = ina.readLine();
            System.out.println("received: " + fromClient);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\" crossorigin=\"anonymous\"></script>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" crossorigin=\"anonymous\">");
            out.println("<title> Resultado</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>La placa identificada es: "+fromClient+"</h1>");
            out.println("</body>");
            out.println("</html>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

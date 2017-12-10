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
        try (PrintWriter out = response.getWriter()) {
            try {
                Part filePart = request.getPart("img"); // Retrieves <input type="file" name="file">
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                InputStream fileContent = filePart.getInputStream();
                System.out.println("FileName:" +request.getContextPath()+ fileName);
                File file = new File("/home/fernanda/Documentos/Redes2/5.jpg");
                try (
                        //Direccion IP del servidor.
                        Socket soc = new Socket("192.168.1.81", 2004)) {
                    byte[] bytes = new byte[16 * 1024];
                    InputStream in = new FileInputStream(file);
                    try (OutputStream dout = soc.getOutputStream()) {
                        int count;
                        while ((count = in.read(bytes)) > 0) {
                            dout.write(bytes, 0, count);
                        }
                    }
                }
            } catch (IOException e) {
            }

        }
    }

}

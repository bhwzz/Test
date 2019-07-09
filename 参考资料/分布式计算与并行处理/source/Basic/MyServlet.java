package Basic;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MyServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=GB2312";

    //Process the HTTP Get request
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws
        ServletException, IOException {
        response.setContentType( CONTENT_TYPE );
        PrintWriter out = response.getWriter();
        out.println( "<html>" );
        out.println( "<head><title>MyServlet</title></head>" );
        out.println( "<body bgcolor=\"#ffffff\">" );
        out.println(
            "<p>Hellow World!</p>" );
        out.println( "</body>" );
        out.println( "</html>" );
        out.close();
    }
}

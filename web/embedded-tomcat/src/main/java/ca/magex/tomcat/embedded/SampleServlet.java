package ca.magex.tomcat.embedded;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		res.getWriter().println("<html>");
		res.getWriter().println("<body>");
		if (req.getPathInfo().startsWith("/login")) {
			res.getWriter().println("<form type=\"POST\" action=\"j_security_check\">");
			res.getWriter().println("User: <input name=\"j_username\" /> ");
			res.getWriter().println("Password: <input name=\"j_password\" />");
			res.getWriter().println("<input type=\"submit\">");
			res.getWriter().println("</form>");
		} else if (req.getPathInfo().startsWith("/logout")) {	
			req.getSession().invalidate();
		    res.sendRedirect(req.getContextPath() + "/secure/home");
		} else if (req.getPathInfo().startsWith("/error")) {
			res.getWriter().println("Login error: <a href=\"" + req.getContextPath() + "/secure/home\">login</a>");
		} else if (req.getPathInfo().startsWith("/secure/")) {
			res.getWriter().println("<h1>Hello Servlet</h1>");
			res.getWriter().println("<ul>");
			res.getWriter().println("<li>" + req.getPathInfo() + "</li>");
			res.getWriter().println("<li><a href=\"" + req.getContextPath() + "/secure/home\">home</a></li>");
			res.getWriter().println("<li><a href=\"" + req.getContextPath() + "/logout\">logout</a></li>");
			res.getWriter().println("</ul>");
		} else {	
		    res.sendRedirect(req.getContextPath() + "/secure/home");
		}
		res.getWriter().println("</body>");
		res.getWriter().println("</html>");
	}
	
}

package ch.hsr.traildevils.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.hsr.traildevils.db.TrailProvider;
import ch.hsr.traildevils.domain.Trail;

import com.google.gson.Gson;

/**
 * This servlet returns all available trails in json format.
 */
@WebServlet("/trails")
public class TrailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		List<Trail> trailList = TrailProvider.getInstance().findAll();
		
		Gson gson = new Gson();
		
		String content = gson.toJson(trailList.toArray());
		response.getWriter().print(content);
		response.setContentLength(content.getBytes().length);
		
		response.getWriter().flush();
		response.getWriter().close();
	}
}

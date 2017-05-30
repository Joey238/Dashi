package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.MongoDBConnection;
import db.MySQLDBConnection;

/**
 * Servlet implementation class SearchRestaurants
 */
@WebServlet("/restaurants")
public class SearchRestaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//DBConnection connection = new MySQLDBConnection();
	DBConnection connection = new MongoDBConnection();


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchRestaurants() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * response.setContentType("application/json");
		 * response.addHeader("Access-Control-Allow-Origin", "*"); String
		 * username = ""; String age = ""; PrintWriter out =
		 * response.getWriter(); if (request.getParameter("username") != null &&
		 * request.getParameter("age") != null) { username =
		 * request.getParameter("username"); age = request.getParameter("age");
		 * out.print("Hello " + username + " with age:" + age) ; } out.flush();
		 * out.close();
		 *---------------------------------------------------------------------------
		JSONArray array = new JSONArray();
		try {
			if (request.getParameterMap().containsKey("user_id")
					&& request.getParameterMap().containsKey("lat")
					&& request.getParameterMap().containsKey("lon")) {
				
				String userId = request.getParameter("user_id");
				double lat = Double.parseDouble(request.getParameter("lat"));
				double lon = Double.parseDouble(request.getParameter("lon"));
				// return some fake restaurants
				array.put(new JSONObject().put("name", "Panda Express"));
				array.put(new JSONObject().put("name", "Hong Kong Express"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RpcParser.writeOutput(response, array);
		*/
		
		   JSONArray array = new JSONArray();
			if (request.getParameterMap().containsKey("lat")
					&& request.getParameterMap().containsKey("lon")) {
				// term is null or empty by default
				String term = request.getParameter("term");
				//String userId = (String) session.getAttribute("user");
	                                             String userId = "1111";
				double lat = Double.parseDouble(request.getParameter("lat"));
				double lon = Double.parseDouble(request.getParameter("lon"));
				array = connection.searchRestaurants(userId, lat, lon, term);
			}
			RpcParser.writeOutput(response, array);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

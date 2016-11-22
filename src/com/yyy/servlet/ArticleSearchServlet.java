package com.yyy.servlet;

import java.io.IOException;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;

import com.yyy.dao.HBaseDAO;

/**
 * Servlet implementation class ArticleSearch
 */
@WebServlet("/articleSearch")
public class ArticleSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String search = request.getParameter("search");
		String index = request.getParameter("index");

		System.out.println(search + "-" + index);
		request.setAttribute("title", search);

		Result result = HBaseDAO.get("ARTICLE_TOPIC", index);
		NavigableMap<byte[], byte[]> navigableMap = result.getFamilyMap("topic".getBytes());
		Set<Entry<byte[], byte[]>> set = navigableMap.entrySet();
		for (Entry<byte[], byte[]> entry : set) {
			System.out.println(new String(entry.getKey()) + "-" + new String(entry.getValue()));
		}
		// request.getRequestDispatcher("article_search.jsp").forward(request,
		// response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

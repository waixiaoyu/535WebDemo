package com.yyy.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;

import com.yyy.dao.HBaseDAO;
import com.yyy.model.IndexProb;

/**
 * Servlet implementation class TopicDetailServlet
 */
@WebServlet("/topicDetail")
public class TopicDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String TABLENAME = "TOPIC_WORD";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TopicDetailServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: " + request.getParameter("topicindex"))
				.append(request.getContextPath());
		List<IndexProb> words = getWords(request.getParameter("topicindex"));

		List<String> wordIndex = new ArrayList<>();
		List<String> wordPr = new ArrayList<>();
		for (IndexProb word : words) {
			wordIndex.add(word.getIndex());
			wordPr.add(String.valueOf(word.getProb()).substring(0, 6));
		}

		// set topicIndex and Pr
		request.setAttribute("wordIndex", wordIndex);
		request.setAttribute("wordPr", wordPr);
		request.getRequestDispatcher("topic_detail.jsp").forward(request, response);
	}

	private List<IndexProb> getWords(String index) throws IOException {
		List<IndexProb> words = new ArrayList<IndexProb>();

		Result result = HBaseDAO.get(TABLENAME, index);
		NavigableMap<byte[], byte[]> navigableMap = result.getFamilyMap("word".getBytes());
		Set<Entry<byte[], byte[]>> set = navigableMap.entrySet();
		for (Entry<byte[], byte[]> entry : set) {
			System.out.println(new String(entry.getKey()) + "-" + new String(entry.getValue()));
			result = HBaseDAO.get("ID_WORD", new String(entry.getKey()));
			words.add(
					new IndexProb(new String(CellUtil.cloneValue(result.getColumnLatestCell("word".getBytes(), null))),
							new String(entry.getValue())));
		}
		Collections.sort(words);
		return words;

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

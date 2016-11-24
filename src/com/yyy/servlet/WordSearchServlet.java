package com.yyy.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.record.Index;
import org.json.JSONArray;
import org.json.JSONObject;

import com.yyy.dao.HBaseDAO;
import com.yyy.json.JsonUtils;
import com.yyy.model.IndexProb;

/**
 * Servlet implementation class WordSearchServlet
 */
@WebServlet("/wordSearch")
public class WordSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TABLENAME = "TOPIC_WORD";
	private static String WEBCONTENT_PATH = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WordSearchServlet() {
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
		
		WEBCONTENT_PATH=request.getSession().getServletContext().getRealPath("");
		
		String index = request.getParameter("index");
		System.out.println(index);
		List<String> listTopicIndex = new ArrayList<>();
		List<List<IndexProb>> lists = getTopicWordPr(index, listTopicIndex);
		System.out.println(lists);

		JSONArray jArray = new JSONArray();
		for (int i = 0; i < listTopicIndex.size(); i++) {
			JSONObject jObj = new JSONObject();
			jObj.put("index", listTopicIndex.get(i));
			JSONArray jArrayIndexProb = new JSONArray();
			for (IndexProb ip : lists.get(i)) {
				JSONObject jObjIndexProb = new JSONObject();
				jObjIndexProb.put("word", ip.getIndex());
				jObjIndexProb.put("pr", ip.getProb());
				jArrayIndexProb.put(jObjIndexProb);
			}
			jObj.put("value", jArrayIndexProb);
			jArray.put(jObj);
		}
		System.out.println(WEBCONTENT_PATH);
		JsonUtils.write(jArray.toString(), WEBCONTENT_PATH + File.separatorChar + "word_search.json");

		List<String> word = new ArrayList<>();
		List<String> pr = new ArrayList<>();
		for (List<IndexProb> list : lists) {
			for (IndexProb w : list) {
				word.add(w.getIndex());
				pr.add(String.valueOf(w.getProb()).substring(0, 6));
			}
		}
		request.setAttribute("topicIndex", listTopicIndex);
		request.setAttribute("word", word);
		request.setAttribute("pr", pr);

		request.getRequestDispatcher("word_search.jsp").forward(request, response);

	}

	private List<List<IndexProb>> getTopicWordPr(String index, List<String> listIndex) throws IOException {
		List<List<IndexProb>> lists = new ArrayList<>();

		List<Result> results = HBaseDAO.scanRowKeyByFilter(TABLENAME, null);
		for (int i = 0; i < results.size(); i++) {
			Result result = results.get(i);
			Cell cell = result.getColumnLatestCell("word".getBytes(), index.getBytes());
			if (cell != null) {
				listIndex.add(new String(result.getRow()));
				NavigableMap<byte[], byte[]> navigableMap = results.get(0).getFamilyMap("word".getBytes());
				Set<Entry<byte[], byte[]>> set = navigableMap.entrySet();
				List<IndexProb> list = new ArrayList<>();
				for (Entry<byte[], byte[]> entry : set) {
					result = HBaseDAO.get("ID_WORD", new String(entry.getKey()));
					list.add(new IndexProb(
							new String(CellUtil.cloneValue(result.getColumnLatestCell("word".getBytes(), null))),
							new String(entry.getValue())));
				}
				lists.add(list);
			}
		}
		return lists;
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

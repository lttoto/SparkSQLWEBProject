package java.com.lt.web;

import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.com.lt.dao.VideoAccessTopNDAO;
import java.com.lt.domain.VideoAccessTopN;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by taoshiliu on 2018/2/18.
 */
public class VideoAccessTopNServlet extends HttpServlet {

    private VideoAccessTopNDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new VideoAccessTopNDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String day = req.getParameter("day");
        List<VideoAccessTopN> results =  dao.query(day);
        JSONArray json = JSONArray.fromObject(results);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(json);
        writer.flush();
        writer.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

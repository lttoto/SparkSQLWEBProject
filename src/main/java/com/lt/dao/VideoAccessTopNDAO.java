package java.com.lt.dao;

import java.com.lt.domain.VideoAccessTopN;
import java.com.lt.utils.MySQLUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taoshiliu on 2018/2/18.
 */
public class VideoAccessTopNDAO {

    static Map<String,String> courses = new HashMap<String, String>();
    static {
        courses.put("","");
    }

    public String getCourseName(String id) {
        return courses.get(id);
    }

    public List<VideoAccessTopN> query(String day) {
        List<VideoAccessTopN> list = new ArrayList<VideoAccessTopN>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = MySQLUtils.getConnection();
            String sql = "select cms_id,times from day_video_access_topn_stat where day = ? order BY times DESC limit 5";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString('1',day);
            rs = pstmt.executeQuery();
            VideoAccessTopN videoAccessTopN = null;
            while (rs.next()) {
                videoAccessTopN = new VideoAccessTopN();
                videoAccessTopN.setName(this.getCourseName(rs.getLong("cms_id") + ""));
                videoAccessTopN.setValue(rs.getLong("times"));
                list.add(videoAccessTopN);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            MySQLUtils.release(connection,pstmt,rs);
        }

        return list;

    }

}

package com.campsite.model;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class CampsiteGifReader extends HttpServlet {
    Connection con;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("image/gif");
        ServletOutputStream out = res.getOutputStream();

        try {
            Statement stmt = con.createStatement();
            String column = req.getParameter("column").trim();
            String campId = req.getParameter("camp_id").trim();
            ResultSet rs = stmt.executeQuery(
                    "select " + column + " from campsite where camp_id=" + campId);

            if (rs.next()) {
                BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(column));
                byte[] buf = new byte[4 * 1024]; // 4K buffer
                int len;
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
                in.close();
            } else {
                InputStream in = getServletContext().getResourceAsStream("/NoData/none3.jpg");
                byte[] b = new byte[in.available()];
                in.read(b);
                out.write(b);
                in.close();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
//			System.out.println(e);
            InputStream in = getServletContext().getResourceAsStream("/NoData/null2.jpg");
            byte[] b = new byte[in.available()];
            in.read(b);
            out.write(b);
            in.close();
        }
    }

    public void init() throws ServletException {
        try {
            Context ctx = new javax.naming.InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/camping");
            con = ds.getConnection();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

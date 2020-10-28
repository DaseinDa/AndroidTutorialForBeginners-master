package com.example.obtainnetworkmessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

/**
 * Servlet implementation class CityAction
 */
@WebServlet("/CityAction")
public class CityAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public CityAction() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter writer=response.getWriter();
        String type=request.getParameter("type");
        if(type.equals("json")){
            List<String> list=CityDataSource.getCitysList();//生成json对象,json数据一定要带<K,V>
            Map<String, List<String>>map=new HashMap<String, List<String>>();
            map.put("citys", list);
            String jsonString=JSONSerializer.toJSON(map).toString();
            writer.println(jsonString);//注意生成json字符串时，这个地方一定要是独立干净的
        }

        writer.flush();
        writer.close();
    }

}
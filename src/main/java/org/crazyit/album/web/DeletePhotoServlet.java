package org.crazyit.album.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.crazyit.album.service.AlbumService;
import org.crazyit.album.exception.AlbumException;
import org.crazyit.album.web.base.BaseServlet;

@WebServlet(urlPatterns="/deletePhoto")
public class DeletePhotoServlet extends BaseServlet{
	public void service(HttpServletRequest request
			, HttpServletResponse response)throws IOException,ServletException
		{
		response.setContentType("text/javascript;charset=gbk");
		PrintWriter out = response.getWriter();
		try
		{
			HttpSession session = request.getSession(true);
			String user = (String)request.getSession().getAttribute("curUser");
			String curimg=session.getAttribute("curImg").toString();
			//读取id
			int id = Integer.parseInt(session.getAttribute("curid").toString());
			as.deletePhoto(user , curimg, id);
			System.out.println(id);
			session.removeAttribute("curImg");
			session.removeAttribute("curid");
			
			out.println("alert('您已经成功删除！')");
			System.out.println(curimg);
			
			
		}
		catch (AlbumException ex)
		{
			out.println("alert('" + ex.getMessage()
				+ "删除照片异常')");
		}
		
		}
	

}

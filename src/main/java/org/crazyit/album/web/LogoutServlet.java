package org.crazyit.album.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.crazyit.album.exception.AlbumException;
import org.crazyit.album.web.base.BaseServlet;

@WebServlet(urlPatterns="/logOut")
public class LogoutServlet extends BaseServlet{
	public void service(HttpServletRequest request
			, HttpServletResponse response)throws IOException,ServletException
		{
		response.setContentType("text/javascript;charset=gbk");
		PrintWriter out = response.getWriter();
		try
		{
			HttpSession session = request.getSession(true);
			//session.setAttribute("curUser" , "");
			session.invalidate();
			out.println("alert('您已经成功退出！')");
			out.println("$('#hasLogin').hide(500)");
			out.println("$('#noLogin').show(500)");
			
			
		}
		catch (AlbumException ex)
		{
			out.println("alert('" + ex.getMessage()
				+ "退出失败')");
		}
		
		}

}

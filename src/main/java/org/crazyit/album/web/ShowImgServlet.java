package org.crazyit.album.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.util.*;
import java.io.*;

import org.crazyit.album.service.AlbumService;
import org.crazyit.album.exception.AlbumException;
import org.crazyit.album.web.base.BaseServlet;
import org.crazyit.album.vo.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
@WebServlet(urlPatterns="/showImg")
public class ShowImgServlet extends BaseServlet
{
	public void service(HttpServletRequest request
		, HttpServletResponse response)throws IOException,ServletException
	{
		String img = request.getParameter("img");
		String id = request.getParameter("id");
		String user = request.getParameter("curUser");
		String describepho = request.getParameter("describe");
		
		HttpSession session = request.getSession(true);
		// 将用户正在浏览的图片放入HttpSession中。
		session.setAttribute("curImg" , img);
		// set id
		session.setAttribute("curid", id );
		
		Object pageObj = session.getAttribute("curcommentPage");
		int curcommentPage = pageObj == null ? 1 :(Integer) pageObj;
		//curcommentPage=2;
		// show img 的时候要show comment
		PrintWriter out = response.getWriter();
		try
		{
			List<CommentHolder> comments = as.getCommentByphotoId(id,curcommentPage);
			// 清空id为list的元素
			out.println("var list = $('#commentlist').empty();");
			for (CommentHolder ch : comments)
			{
				// 将每个相片动态添加到id为list的元素中
				out.println("list.append(\"<div align='center'>" +
					"<a href='javascript:void(0)' onclick=\\\"showComment('"
					+ ch.getId() +"');\\\">"
					+ ch.getTitle() + " "+ch.getComment()+"</a></div>\");");
			}
		}
		
		//<a href='javascript:void(0)' onclick=\\\showImg('ph.getFileName()');
		catch (AlbumException ex)
		{
			out.println("alert('" + ex.getMessage() + "请重试！')");
		}
		
		
		
		
		///////////////////
		System.out.println("描述:"+describepho+" id:"+session.getAttribute("curid"));
		
		response.setContentType("text/javascript;charset=gbk");
		// 获取输出流
		//PrintWriter out = response.getWriter();
		String path="C:/Users/Ming/workspaceweb/album/src/main/webapp/"+"uploadfiles/"+img;
		FileInputStream input=new FileInputStream(path);
		
		FileOutputStream output=new FileOutputStream(getServletContext().getRealPath("/")+"uploadfiles/"+img);
		int in=input.read();
		while(in!=-1){
		output.write(in);
		in=input.read();
		}
		System.out.println(getServletContext().getRealPath("/")+"uploadfiles/"+img);

		out.println("$('#describephoto').empty();");
		out.println("$('#describephoto').append('<p>"+describepho+"</p>');");
        out.println("$('#describechange').val('"+describepho  + "');");
		System.out.println(path);
		//out.println("$('#show').attr('src' , 'C:/Users/Ming/workspaceweb/album/src/main/webapp/uploadfiles/" + img+"');");
		out.println("$('#show').attr('src' , 'uploadfiles/"+ img + "');");

	
	}
}

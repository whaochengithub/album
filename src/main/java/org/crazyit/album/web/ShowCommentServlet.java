package org.crazyit.album.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.crazyit.album.exception.AlbumException;
import org.crazyit.album.vo.CommentHolder;
import org.crazyit.album.web.base.BaseServlet;
@WebServlet(urlPatterns="/showComment")
public class ShowCommentServlet extends BaseServlet{
	public void service(HttpServletRequest request
			, HttpServletResponse response)throws IOException,ServletException
		{
		    String id = request.getParameter("id");
		    System.out.println(id);
		    HttpSession session = request.getSession(true);
		    session.setAttribute("curid", id );
		    Object pageObj = session.getAttribute("curcommentPage");
			int curcommentPage = pageObj == null ? 1 :(Integer) pageObj;
			PrintWriter out = response.getWriter();
			try
			{
				List<CommentHolder> comments = as.getCommentByphotoId(id,curcommentPage);
				// 清空id为list的元素
				out.println("var list = $('#commentlist').empty();");
				for (CommentHolder ch : comments)
				{
					String name=as.getUserbyid(ch.getAuthor().getId());
					//System.out.println("user id:"+ch.getAuthor().getId());
					
					// 将每个相片动态添加到id为list的元素中
					out.println("list.append(\"<div align='center'>" +
						"<a href='javascript:void(0)' onclick=\\\"showComment('"
						+ ch.getId() +"');\\\">"+name+" "
						+ ch.getTitle() + " "+ch.getComment()+"</a></div>\");");
				}
			}
			
			//<a href='javascript:void(0)' onclick=\\\showImg('ph.getFileName()');
			catch (AlbumException ex)
			{
				out.println("alert('" + ex.getMessage() + "请重试！')");
			}
		
		}
}

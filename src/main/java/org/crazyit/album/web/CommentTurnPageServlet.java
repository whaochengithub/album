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
@WebServlet(urlPatterns="/commentturnPage")
public class CommentTurnPageServlet extends BaseServlet
{
	public void service(HttpServletRequest request
		, HttpServletResponse response)throws IOException,ServletException
	{
		String turn = request.getParameter("turn");
		HttpSession session = request.getSession(true);
		String id = (String)session.getAttribute("curid");
		Object pageObj = session.getAttribute("curcommentPage");
		// 如果HttpSession中的curPage为null，则设置当前页为第一页
		int curcommentPage = pageObj == null ? 1 :(Integer) pageObj;
		response.setContentType("text/javascript;charset=gbk");
		PrintWriter out = response.getWriter();
		if (curcommentPage == 1 && turn.equals("-1"))
		{
			out.println("alert('现在已经是第一页，无法向前翻页！')");
		}
		else
		{
			// 执行翻页，修改curPage的值。
			curcommentPage += Integer.parseInt(turn);
			try
			{
				List<CommentHolder> comments = as.getCommentByphotoId(id , curcommentPage);
				// 翻页后没有记录
				if (comments.size() == 0)
				{
					out.println("alert('翻页后找不到任何相片记录，"
						+ "系统将自动返回上一页')");
					// 重新返回上一页
					curcommentPage -= Integer.parseInt(turn);
				}
				else
				{
					// 把用户正在浏览的页码放入HttpSession中
					session.setAttribute("curcommentPage" , curcommentPage);
				}
			}
			catch (AlbumException ex)
			{
				out.println("alert('" + ex.getMessage() + "请重试！')");
			}
		}
	}
}

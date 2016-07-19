package org.crazyit.album.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.crazyit.album.exception.AlbumException;
import org.crazyit.album.web.base.BaseServlet;

@WebServlet(urlPatterns="/uploadcomment")
public class Uploadcomment extends BaseServlet{
	public void service(HttpServletRequest request
			, HttpServletResponse response)throws IOException,ServletException
		{
		Iterator iter = null;
		String title = null;
		String comment=null;
		String user=null;
		response.setContentType("text/html;charset=gbk");
		// 获取输出流
		PrintWriter out = response.getWriter();
		try
		{
			// 使用Uploader处理上传
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = upload.parseRequest(request);
			iter = items.iterator();
			// 遍历每个表单控件对应的内容
			while (iter.hasNext())
			{
				FileItem item = (FileItem)iter.next();
				// 如果该项是标题
				if (item.isFormField())
				{
					String name = item.getFieldName();
					if (name.equals("commenttitle"))
					{
						title = item.getString("gbk");
					}
					else
					{
						comment=item.getString("gbk");
						user = (String)request.getSession()
							.getAttribute("curUser");
						}
					
					
				}
				// 如果是评论内容
					
				}
			
			String photo = (String)request.getSession()
					.getAttribute("curid");
			System.out.println(user+title+comment+" photo:"+photo); 
			as.addcomment(user , title , comment, photo);
			out.write("<script type='text/javascript'>"
				+ "parent.callback('恭喜你，评论成功！')"
				+ "</script>");
			
		}
		catch (FileUploadException fue)
		{
			fue.printStackTrace();
			out.write("<script type='text/javascript'>"
				+ "parent.callback('处理上传评论出现错误，请重试！')"
				+ "</script>");
		}
		catch (AlbumException ex)
		{
			ex.printStackTrace();
		}
	}

}
	

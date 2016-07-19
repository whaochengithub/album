package org.crazyit.album.vo;

import java.util.Date;

import org.crazyit.album.domain.User;



public class CommentHolder {
	// comment的id
	private int id;
	//comment的名称
	private String title;
	//comment的内容
	private String comment;
	//comment的作者
	private User author;
	//comment的时间
	private Date updatetime;
	
	//无参数的构造器
		public CommentHolder()
		{
		}
		//初始化全部属性的构造器
		public CommentHolder(int id,String title , String comment,User author,Date updatetime)
		{
			this.id=id;
			this.title = title;
			this.comment = comment;
			this.author = author;
			this.updatetime=updatetime;
			
		}
		//id 相关操作
		public void setId(int id)
		{
			this.id = id;
		}
		public int getId()
		{
			return this.id;
		}
		
		

		//title属性的setter和getter方法
		public void setTitle(String title)
		{
			this.title = title;
		}
		public String getTitle()
		{
			return this.title;
		}

		//fileName属性的setter和getter方法
		public void setComment(String comment)
		{
			this.comment = comment;
		}
		public String getComment()
		{
			return this.comment;
		}
		
		public void setAuthor(User author)
		{
			this.author = author;
		}
		public User getAuthor()
		{
			return this.author;
		}
		
		public void setUpdatetime(Date date)
		{
			this.updatetime = date;
		}
		public Date getUpdatetime()
		{
			return this.updatetime;
		}
		

}

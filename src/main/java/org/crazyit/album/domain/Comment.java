package org.crazyit.album.domain;

import java.util.Date;

//this class is used for comment
public class Comment {
	// 标识属性
		private Integer id;
		// 该评论的id
		private String title;
		//该评论的标题
		private String comment;
		//该评论的内容
		private User author;
		//该评论的作者
		private Photo photo;
		//该评论对应的照片id
		private Date updatetime;
		//该评论的更新时间
		public Comment()
		{
		}
		// 初始化全部成员变量的构造器
		public Comment(Integer id , String title,String comment,User author ,Photo photo,Date updatetime)
		{
			this.id = id;
			this.title = title;
			this.comment = comment;
			this.author = author;
			this.photo=photo;
			this.updatetime=updatetime;
			
		}

		// id的setter和getter方法
		public void setId(Integer id)
		{
			this.id = id;
		}
		public Integer getId()
		{
			return this.id;
		}

		// title的setter和getter方法
		public void setTitle(String title)
		{
			this.title = title;
		}
		public String getTitle()
		{
			return this.title;
		}

		// comment的setter和getter方法
		public void setComment(String comment)
		{
			this.comment = comment;
		}
		public String getComment()
		{
			return this.comment;
		}

		// author的setter和getter方法
		public void setAuthor(User author)
		{
			this.author = author;
		}
		public User getAuthor()
		{
			return this.author;
		}
		
		//photoid的setter和getter方法
		public void setPhoto(Photo photo)
		{
			this.photo = photo;
		}
		public Photo getPhoto()
		{
			return this.photo;
		}
		
		//updatetime的setter和getter方法
		public void setUpdatetime(Date updatetime)
		{
			this.updatetime = updatetime;
		}
		public Date getUpdatetime()
		{
			return this.updatetime;
		}
		
		
}

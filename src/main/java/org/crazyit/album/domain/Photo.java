package org.crazyit.album.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

public class Photo
{
	// 标识属性
	private Integer id;
	// 该相片的名称
	private String title;
	// 相片在服务器上的文件名
	private String fileName;
	// 保存该相片所属的用户
	private User user;
	//图片的描述
	private String describephoto;
	//图片的评论
	private Set<Comment> comments
    = new HashSet<Comment>();
	//图片最新修改时间
	private Date time;

	// 无参数的构造器
	public Photo()
	{
	}
	// 初始化全部成员变量的构造器
	public Photo(Integer id , String title,String describe,String fileName ,User user)
	{
		this.id = id;
		this.title = title;
		this.fileName = fileName;
		this.user = user;
		this.describephoto=describe;
		
		
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

	// fileName的setter和getter方法
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	public String getFileName()
	{
		return this.fileName;
	}

	// user的setter和getter方法
	public void setUser(User user)
	{
		this.user = user;
	}
	public User getUser()
	{
		return this.user;
	}
	
	//describe的setter和getter方法
	public void setdescribephoto(String describe)
	{
		this.describephoto = describe;
	}
	public String getdescribephoto()
	{
		return this.describephoto;
	}
	
	// comments的setter和getter方法
			public void setComments(Set<Comment> comments)
			{
				this.comments = comments;
			}
			public Set<Comment> getComments()
			{
				return this.comments;
			}
			// comments的setter和getter方法
	public void setTime(Date time)
	{
		this.time = time;
	}
	public Date getTime()
	{
		return this.time;
	}
	
}
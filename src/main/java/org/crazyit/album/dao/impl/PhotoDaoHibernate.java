package org.crazyit.album.dao.impl;

import java.util.*;

import org.crazyit.album.domain.*;
import org.crazyit.album.dao.*;
import org.crazyit.common.dao.impl.*;

/**
 * Description:
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class PhotoDaoHibernate extends BaseDaoHibernate4<Photo>
	implements PhotoDao
{
	/**
	 * 查询属于指定用户的相片，且进行分页控制
	 * @param user 查询相片所属的用户
	 * @param pageNo 需要查询的指定页
	 * @return 查询到的相片
	 */
	public List<Photo> findByUser(User user , int pageNo)
	{
		//返回分页查询的结果
		return (List<Photo>)findByPage("select b from Photo b where "
			+ "b.user = ?0" , pageNo , PAGE_SIZE , user);
	}
	public void deletebyname(Photo p){
		String a=p.getFileName();
		a="'"+a+"'";
		deletecurimg("delete Photo where fileName="+a);
	}
	
	public void updatebyname(Photo p){
		String a=p.getFileName();
		String b=p.getdescribephoto();
		a="'"+a+"'";
		b="'"+b+"'";
		updatesql("update Photo set describephoto="+b+" where fileName="+a);
	}
	
	public Photo findById(String id){
		int photoid=Integer.parseInt(id);
		
		List<Photo> a= (List<Photo>)find("select b from Photo b where "
			+ "b.id = ?0",photoid);
		return a.get(0);
		
	}
	public List<Photo> findByTime(){
		//select * from user where date = (select date from user order by date desc limit 1)
		List<Photo> a=(List<Photo>)findtop("select b from Photo b order by time DESC",5);
		return a;
		
	}
	
	public void updatetime(Photo p){
		
		String a=p.getId().toString();
		Date b=p.getTime();
		String c=b.toString();
		System.out.println(b+"     "+c);
		a="'"+a+"'";
		//b="'"+b+"'";
		//updatesql("update Photo set time="+b+" where photo_id="+a);
	}
	
}
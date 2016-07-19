package org.crazyit.album.dao.impl;

import java.util.List;

import org.crazyit.album.domain.*;
import org.crazyit.album.dao.*;
import org.crazyit.common.dao.impl.*;

public class CommentDaoHibernate extends BaseDaoHibernate4<Comment>
implements CommentDao{
	
	public List<Comment> findById(String id){
		int photoid=Integer.parseInt(id);
		//返回查询的结果
		
				return (List<Comment>)find("select b from Comment b where "
					+ "b.photo.id = ?0" ,photoid);
	}
	
	public List<Comment> findByPhoto(String id,int pageNo){
		int photoid=Integer.parseInt(id);
		//返回查询的结果
		
		return (List<Comment>)findByPage("select b from Comment b where "
				+ "b.photo.id = ?0" , pageNo , PAGE_SIZE , photoid);
	}
	

}

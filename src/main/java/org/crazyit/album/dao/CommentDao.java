package org.crazyit.album.dao;

import java.util.List;

import org.crazyit.album.domain.*;
import org.crazyit.album.dao.*;
import org.crazyit.common.dao.BaseDao;

public interface CommentDao extends BaseDao<Comment>{
	//以常量控制每页显示的相片数
		//final int PAGE_SIZE = 3;
		/**
		 * 查询属于指定用户的相片，且进行分页控制
		 * @param photo 查询comment的照片
		 * @param pageNo 需要查询的指定页
		 * @return 查询到的相片
		 */
	
	    final int PAGE_SIZE = 3;
		List<Comment> findById(String id);
		List<Comment> findByPhoto(String id , int pageNo);
		
}

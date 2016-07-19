package org.crazyit.album.service;

import org.crazyit.album.dao.*;
import org.crazyit.album.domain.Photo;
import org.crazyit.album.vo.*;

import java.util.*;

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
public interface AlbumService
{
	/**
	 * 验证用户登录是否成功。
	 * @param name 登录的用户名
	 * @param pass 登录的密码
	 * @return 用户登录的结果，成功返回true，否则返回false
	 */
	boolean userLogin(String name , String pass);

	/**
	 * 注册新用户
	 * @param name 新注册用户的用户名
	 * @param pass 新注册用户的密码
	 * @return 新注册用户的主键
	 */
	
	int registUser(String name , String pass);

	/**
	 * 添加照片
	 * @param user 添加相片的用户
	 * @param title 添加相片的标题
	 * @param describe 添加图片的描述
	 * @param fileName 新增相片在服务器上的文件名
	 * @return 新添加相片的主键
	 */
	int addPhoto(String user , String title , String fileName,String describe);
	
	/**
	 * 添加照片
	 * @param user 删除相片的用户
	 * @param curimg 删除照片的名字
	 * @param curing 删除照片的id
	 */
	void deletePhoto(String user , String curimg,int id);

	/**
	 * 根据用户获得该用户的所有相片
	 * @param user 当前用户
	 * @param pageNo 页码
	 * @return 返回属于该用户、指定页的相片
	 */
	List<PhotoHolder> getPhotoByUser(String user , int pageNo);

	/**
	 * 验证用户名是否可用，即数据库里是否已经存在该用户名
	 * @param name 需要校验的用户名
	 * @return 如果该用户名可用，返回true，否则返回false。
	 */
	boolean validateName(String name);
	/**
	 * 添加评论
	 * @param user 添加评论的用户
	 * @param title 添加评论的标题
	 * @param comment 新增评论在服务器上的内容
	 * @return 新添加评论的主键
	 */
	int addcomment(String user , String title  , String comment,String photo);
	/**
	 * 添加评论
	 * @param user 修改描述的用户
	 * @param curimg 当前图片名称
	 * @param describe 修改评论在服务器上的内容
	 * @return 新添加评论的主键
	 */
	void updatedescribe(String user , String curimg  , String describe,String id);
	
	List<CommentHolder> getCommentByphotoId(String id,int pageNo);
	
	List<PhotoHolder> getPhotoByTime();
	
	String getUserbyid(int id);  
}

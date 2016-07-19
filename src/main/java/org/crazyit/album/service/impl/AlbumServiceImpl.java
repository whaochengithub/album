package org.crazyit.album.service.impl;

import org.crazyit.album.domain.*;
import org.crazyit.album.service.*;
import org.crazyit.album.exception.*;
import org.crazyit.album.dao.*;
import org.crazyit.album.vo.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;

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
public class AlbumServiceImpl implements AlbumService
{
	//业务逻辑组件所依赖的2个DAO组件
	private UserDao ud = null;
	private PhotoDao  pd = null;
	private CommentDao cd=null;
	//依赖注入3个DAO组件所需的setter方法
	public void setUserDao(UserDao ud)
	{
		this.ud = ud;
	}
	public void setPhotoDao(PhotoDao pd)
	{
		this.pd = pd;
	}
	public void setCommentDao(CommentDao cd)
	{
		this.cd = cd;
	}

	/**
	 * 验证用户登录是否成功。
	 * @param name 登录的用户名
	 * @param pass 登录的密码
	 * @return 用户登录的结果，成功返回true，否则返回false
	 */
	public boolean userLogin(String name , String pass)
	{
		try
		{
			//使用UserDao根据用户名查询用户
			User u = ud.findByName(name);
			if (u != null && u.getPass().equals(pass))
			{
				return true;
			}
			return false;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new AlbumException("处理用户登录出现异常！");
		}
	}
	
	/**
	 * 注册新用户
	 * @param name 新注册用户的用户名
	 * @param pass 新注册用户的密码
	 * @return 新注册用户的主键
	 */
	public int registUser(String name , String pass)
	{
		try
		{
			//创建一个新的User实例
			User u = new User();
			u.setName(name);
			u.setPass(pass);
			//持久化User对象
			ud.save(u);
			return u.getId();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new AlbumException("新用户注册出现异常！");
		}
	}

	/**
	 * 添加照片
	 * @param user 添加相片的用户
	 * @param title 添加相片的标题
	 * @param fileName 新增相片在服务器上的文件名
	 * @return 新添加相片的主键
	 */
	public int addPhoto(String user , String title , String fileName,String describe)
	{
		try
		{
			//创建一个新的Photo实例
			Photo p = new Photo();
			p.setTitle(title);
			p.setFileName(fileName);
			p.setdescribephoto(describe);
			p.setUser(ud.findByName(user));
			Date time=new Date();
			p.setTime(time);
			System.out.println(p.getdescribephoto());
			//持久化Photo实例
			pd.save(p);
			return p.getId();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new AlbumException("添加相片过程中出现异常！");
		}
	}
	
	public void deletePhoto(String user , String curimg,int id){

		try
		{
			//创建一个新的Photo实例
			Photo p = new Photo();
			//必须setid 不然无法调用hibernatedelete方法
			p.setId(id);
			p.setFileName(curimg);
			p.setUser(ud.findByName(user));
			//持久化Photo实例
			//pd.deletebyname(p);
			pd.delete(p);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new AlbumException("删除相片过程中出现异常！");
		}
		
		
	}

	/**
	 * 根据用户获得该用户的所有相片
	 * @param user 当前用户
	 * @param pageNo 页码
	 * @return 返回属于该用户、指定页的相片
	 */
	public List<PhotoHolder> getPhotoByUser(String user , int pageNo)
	{
		try
		{
			List<Photo> pl = pd.findByUser(ud.findByName(user) , pageNo);
			List<PhotoHolder> result = new ArrayList<PhotoHolder>();
			for (Photo p : pl )
			{
				result.add(new PhotoHolder(p.getTitle() , p.getFileName(),p.getdescribephoto(),p.getId()));
			}
			return result;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new AlbumException("查询相片列表的过程中出现异常！");
		}
	}

	/**
	 * 验证用户名是否可用，即数据库里是否已经存在该用户名
	 * @param name 需要校验的用户名
	 * @return 如果该用户名可用，返回true，否则返回false。
	 */
	public boolean validateName(String name)
	{
		try
		{
			//根据用户名查询对应的User实例
			User u = ud.findByName(name);
			if (u != null)
			{
				return false;
			}
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new AlbumException("验证用户名是否存在的过程中出现异常！");
		}
	}
	/**
	 * 添加评论
	 * @param user 添加评论的用户
	 * @param title 添加评论的标题
	 * @param comment 新增评论在服务器上的内容
	 * @return 新添加评论的主键
	 */
	 public int addcomment(String user , String title  , String comment,String photo){ 
			try
			{
				//创建一个新的comment实例
				Comment p = new Comment();
				p.setTitle(title);
				p.setComment(comment);
				p.setAuthor(ud.findByName(user));
				Photo p2=pd.findById(photo);
				p.setPhoto(p2);
				
				Date date = new Date();
				//DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
				//Date date = format.parse(string);
				p.setUpdatetime(date);
				//对图片的时间数据进行修改
				int id=Integer.parseInt(photo);
				p2.setId(id);
				p2.setTime(date);
				
				pd.update(p2);
				
				
				//持久化comment实例
				cd.save(p);
				System.out.println(p.getTitle());
				System.out.println(p.getComment());
				System.out.println(p.getAuthor().getName());
				System.out.println(p.getPhoto().getId());
				return p.getId();
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				throw new AlbumException("增加评论过程中出现异常！");
			}  
	 }
	 
	 public void updatedescribe(String user , String curimg  , String describe,String id){
		 try
			{
		    Photo p = new Photo();
		    p.setId(Integer.parseInt(id));
		    //p.setTitle("555");
			p.setFileName(curimg);
			p.setUser(ud.findByName(user));
			p.setdescribephoto(describe);
			//持久化Photo实例
			pd.update(p);
		 
	 }catch(Exception ex)
			{
			ex.printStackTrace();
			throw new AlbumException("修改相片描述过程中出现异常！");
		}
	
	
	 }
	 
	 public List<CommentHolder> getCommentByphotoId(String id,int pageNo){
		 try
			{
				List<Comment> cl = cd.findByPhoto(id,pageNo);
				List<CommentHolder> result = new ArrayList<CommentHolder>();
				for (Comment c : cl )
				{
					result.add(new CommentHolder(c.getId(),c.getTitle() , c.getComment(),c.getAuthor(),c.getUpdatetime()));
				}
				return result;
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				throw new AlbumException("查询评论列表的过程中出现异常！");
			}
		 
	 }
	 
	 public List<PhotoHolder> getPhotoByTime(){
		 try
			{
		 List<Photo> pl = pd.findByTime();
		 List<PhotoHolder> result = new ArrayList<PhotoHolder>();
		 for (Photo p : pl )
			{
				System.out.println(p.getId()+" "+p.getTime());
				result.add(new PhotoHolder(p.getTitle() , p.getFileName(),p.getdescribephoto(),p.getId()));
				
			}
			return result;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new AlbumException("查询评论列表的过程中出现异常！");
		}
	 }
	 
	 public String getUserbyid(int id){
		 User a=ud.findById(id);
		 System.out.println(a.getName());
		 return a.getName();
		 
	 }
}



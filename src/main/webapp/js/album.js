$(document).ready(function()
{
	// 页面加载时向pageLoad发送请求
	$.getScript("pageLoad");
	
});




function reset()
{
	// 清空user、pass两个单行文本框
	$("#user").val("");
	$("#pass").val("");
}
// 切换到注册对话框
function changeRegist()
{
	// 隐藏登录用的两个按钮
	$("#loginDiv").hide("500");
	// 显示注册用的两个按钮
	$("#registDiv").show("500");
}
// 处理用户登录的函数
function proLogin()
{
	// 获取user、pass两个文本框的值
	var user = $.trim($("#user").val());
	var pass = $.trim($("#pass").val());
	if (user == null || user == "" 
		|| pass == null|| pass =="")
	{
		alert("必须先输入用户名和密码才能登录");
		return false;
	}
	else
	{
		// 向proLogin发送异步、POST请求
		$.post("proLogin", $('#user,#pass').serializeArray()
			, null , "script");
	}
	
	Userhall();
	
}
//处理用户退出的函数
function Logout(){
	
	 if (confirm("你确认退出吗？")) {  
		 $.post("logOut","script"); 
		 
     }  
     else {  
        
     }  
}

function deletephoto(){
	 if (confirm("你确认删除当前吗？")) {  
		 $.post("deletePhoto","script"); 
     }  
     else {  
        
     }  
	
}

//修改评论
function showdescribe(){
	//alert("describe");
	$("#Describeupdate").show()
		.dialog(
		{
			modal: true,
			title: '修改描述',
			resizable: false,
			width: 428,
			height: 200,
			overlay: {opacity: 0.5 , background: "black"}
		});
	
}

//书写评论
function writecomment(){

	$("#Commentupload").show()
		.dialog(
		{
			modal: true,
			title: '新建评论',
			resizable: false,
			width: 428,
			height: 220,
			overlay: {opacity: 0.5 , background: "black"}
		});
	
}




// 处理用户注册的函数
function regist()
{
	// 获取user、pass两个文本框的值
	var user = $.trim($("#user").val());
	var pass = $.trim($("#pass").val());
	if (user == null || user == "" || pass == null || pass =="")
	{
		alert("必须先输入用户名和密码才能注册");
		return false;
	}
	else
	{
		// 向proRegist发送异步、POST请求
		$.post("proRegist", $('#user,#pass').serializeArray()
			, null , "script");
	}
}

// 验证用户名是否可用
function validateName()
{
	// 获取user文本框的值
	var user = $.trim($("#user").val());
	if (user == null || user == "")
	{
		alert("您还没有输入用户名！");
		return false;
	}
	else
	{
		// 向validateName发送异步、POST请求
		$.post("validateName", $('#user').serializeArray()
			, null , "script");
	}
}

// 周期性地获取当前用户、当前页的相片
function onLoadHandler()
{
	// 向getPhoto发送异步、GET请求
	$.getScript("getPhoto");
	// 指定1秒之后再次执行此方法
	setTimeout("onLoadHandler()", 10000);
}

// 上传文件的回调函数
function callback(msg)
{
	alert(msg);
	// 隐藏文件上传的对话框
	$('#uploadDiv').dialog('close');
	// 清空title、file,describe两个表单域。
	$('#title,#file,#describephoto').val('');
	$('#hideframe').attr('src' , '');
}

function callback2(msg)
{
	alert(msg);
	// 隐藏文件上传的对话框
	$('#Describeupdate').dialog('close');
	// 清空title、file,describe两个表单域。
	$('#describechange').val('');
	$('#hideframe').attr('src' , '');
}

// 显示照片
function showImg(fileName,filedescribe,id)
{
	$.getScript("showImg?img=" + fileName+"&describe="+filedescribe+"&id="+id);
}
//显示comment
function showComment(id)
{
	$.getScript("showComment?id="+id);
}

// 处理翻页的函数
function turnPage(flag)
{
	$.getScript("turnPage?turn=" + flag);
}

//处理comment翻页的函数
function commentturnPage(flag){
	
	$.getScript("commentturnPage?turn="+flag);
}

// 打开上传窗口
function openUpload()
{

	$("#uploadDiv").show()
		.dialog(
		{
			modal: true,
			title: '上传照片',
			resizable: false,
			width: 428,
			height: 250,
			overlay: {opacity: 0.5 , background: "black"}
		});
}

function Hall()
{
	$("#userimg").hide("500");
	$("#allhall").hide("500");
	$("#userhall").show("500");
	$("#halllist").show("500");
}

function Userhall()
{
	$("#userimg").show("500");
	$("#allhall").show("500");
	$("#userhall").hide("500");
	$("#halllist").hide("500");

}


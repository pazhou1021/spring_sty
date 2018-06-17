<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
 
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style type="text/css">
   <!--
   a:link { text-decoration: none;color:#006a92}
   a:active { text-decoration:blink}
   a:visited { text-decoration: none;color: green}
     img{border:0px}  
   a:hover{
 		text-decoration:underline;  /*鼠标放上去有下划线*/
		  }
   --> 
</style>
  
 
 	<script language="javascript">//标题栏显示时间的脚本
	var clocktext;
	var pagetitle=document.title;
	function scroll(){
		var today = new Date();
		sec=today.getSeconds();
		hr=today.getHours();
		mi=today.getMinutes();
		if(hr<10)hr="0"+hr;
		if(mi<10)hr="0"+mi;
		if(sec<10)sec="0"+sec;
		var clocktext="HiQ注册 NOW:"+hr+":"+mi+":"+sec;
	
		setTimeout("scroll()",1000);
		document.title=pagetitle+clocktext;
	}
	if(document.all){
		scroll();
	}
	function Reset()
	{
		var result=confirm("你确定重置吗？");
		return result;
	}
	function isEmpty(s) 
	{
  		if (s == null || s.length == 0)
    		return true;
  					// 如果有一个为非空，则test返回true，表示字符串非空。
						// 若test返回true，表示字符串为空。
 		 return !/\S/.test(s);
	}

	function check()
	{
		var s=document.getElementById("register");
		var len=document.getElementById("register").length;
		
		for(var i=0;i<len;i++){
			if(s.elements.item(i).value==""){
				alert("资料填写不完整！");
				return false;
			}
		}
	
	
	
		
	
	}
	function acc()
	{
		var a=document.getElementById("accept");
		var b=document.getElementById("notaccept");
		if(a.value=="")
		{
			alert("请选择同意");
			return false;
		}
		
	}
 	function name(userName){
	   	  var username=document.getElementById("userName");
		 
		  if(username.value=="")
		  {
		  		alert("用户名不能为空");
			  return false;
			 
		  }
		 return true;
	  }
	  function password(password){
	   	  var pass=document.getElementById("password");
		 
		  if(pass.value=="")
		  {
		  		alert("用户名不能为空");
			  return false;
			 
		  }
		 return true;
	  }

	function checkemail()
	{
		//判断输入是否为空
		if(document.getElementById("email").value.length!=0)
		{
			//分别检测是否包含字符“@”、“.”及其位置
			if(document.getElementById("email").value.charAt(0)=="."||
   				document.getElementById("email").value.charAt(0)=="@"||
   				document.getElementById("email").value.indexOf('@',0)==-1||
   				document.getElementById("email").value.indexOf('.',0)==-1||
   				document.getElementById("email").value.lastIndexOf('@')==document.zhuce.email.value.length-1||
   				document.getElementById("email").value.lastIndexOf('.')==document.zhuce.email.value.length-1
  				)
			{
				//如果格式不正确
				alert("E-mail 不合法!");
				document.getElementById("email").focus();
				return false;
			}
		}
		else
		{
			alert("Email 不可为空");
			document.getElementById("email").focus();
			return false;
		}
		return false;
	}




	function qr(password,querenpassword){
		  var a=document.getElementById(password);
		  var b=document.getElementById(querenpassword);
		  if(a.value!=b.value){
		  		alert("密码不一致");
		  		return false;
			}
		  return true;
	}
	//设置生日格式
	function choiseuserBirthday()
	{
		document.getElementById("userBirthday").value = "";
	}
	//设置邮箱格式
	function choiseemail()
	{
		document.getElementById("email").value = "";
	}
	

</script>
  <!--  <base href="http://localhost:8888/HappyBookshop/"> --> 
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 
  </head>
  
  <body  bgcolor="" >
  <table width="70%" height="20" border="0" bgcolor="" align="center">
     <tr><td align="center"><font family="微软雅黑" size="5"><b> HiQ 账号注册 </b></font></td></tr>
   </table>
        
             <hr size="1" color="#C5a5a7" width="70%"> 
        <table align="center" cellspacing="0" cellpadding="60" width="600"  border="1" >
        <tr>
        	
        	<td width="620" border="1">
        		
 			<table  cellspacing="0" cellpadding="" width="525" height="525"  border="5"  bgColor="#1cf9ee"  align="center">
    			
    			<tr>
    				<td background="images/whit.jpg">
    					<form action="registerServlet" method="post" id="register" onreset="return Reset()"  onsubmit="return check()" >	
    					<font color=""  size="4" face="微软雅黑"> &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; 请填写您的真实信息</font><br/><p>
    	
    				<center><font color=""  size="3" face="微软雅黑">昵  &nbsp; &nbsp;&nbsp;&nbsp;   称:</font>&nbsp;&nbsp;
    				<input style="background-color:#BFEEFF" type="text" name="userNikName" id="userNikName"  width="100" size="25"  onblur="name('userNikName')"><br><p>
    				
    			
    				<font color=""  size=3" face="微软雅黑">密  &nbsp; &nbsp;&nbsp;&nbsp;   码:</font>&nbsp;&nbsp;
    				<input style="background-color:#BFEEFF" type="password" name="userPassword" id="password" width="100" size="27" onblur="password('userPassword')"><br><p>
    				
    		<font color=""  size="3" face="微软雅黑">确认密码:</font>&nbsp;&nbsp;
    				<input style="background-color:#BFEEFF" type="password" name="userPassword" id="querenpassword" width="100" size="27" onblur="qr('password','querenpassword')"><br><p>
    			<font color=""  size="3" face="微软雅黑">家庭地址:</font>&nbsp;&nbsp;
    				<input style="background-color:#BFEEFF" type="text" name="userAddress" id="add" width="100" size="25"><br><p>
    			<font color=""  size="3" face="微软雅黑">年 &nbsp;&nbsp;&nbsp;&nbsp;   龄 :</font>&nbsp;&nbsp;
    				<input style="background-color:#BFEEFF" type="text" name="userAge" id="userAge" width="100" size="25"><br><p>
    				
    				<font color=""  size="3" face="微软雅黑">生 &nbsp;&nbsp;&nbsp;&nbsp;  日 :</font>&nbsp;&nbsp;
    				<input style="background-color:#BFEEFF" type="text" name="userBirthday" id="userBirthday" width="100" size="25" value="1990-11-14"  onfocus="choiseuserBirthday()"><br><p>
    				
    				<font color=""  size="3" face="微软雅黑">性 &nbsp;&nbsp;&nbsp;&nbsp;  别 :</font>&nbsp;&nbsp;
    				<!-- <input style="background-color:#BFEEFF" type="text" name="userSex" id="userSex" width="100" size="25"><br><p> -->
    				<select name="userSex",id="userSex">
    					<option value="1">男</option>
    					<option value="0">女</option>
    				</select> &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;<p>
    			
    				<font color=""  size="3" face="微软雅黑">电子邮件:</font>&nbsp;&nbsp;
    				<input style="background-color:#BFEEFF" type="text" name="userEmail" id="email" width="100" size="25" value="例gbx@163.com" onfocus="choiseemail()"><br><p></center>
    								
    				
    		<center>
    			<font color=""  size="3" face="微软雅黑">个性签名:</font>&nbsp;&nbsp;
    			<input style="background-color:#BFEEFF" type="text" name="userSpeak" id="userSpeak" width="100" size="25"><br>
    		</center><p>
    			<center>
    				<input type="submit" value="注册" name="submit">&nbsp; &nbsp; &nbsp;&nbsp;
    				<input type="reset" value="重置" name="reset"></center></form>
    			</td></tr>
	        </table>
   		</td></tr>	
      </table>
     <hr size="1" color="#C5a5a7" width="70%">
     <table width="70%" height="20" border="0" bgcolor="" align="center">
     <tr><td align="center"><font family="微软雅黑" size="2"> &nbsp;&nbsp;Copyright&copy;&nbsp;2012-2013 TNT小组 版权所有 </font></td></tr>
   </table>
  
  </body>
</html>


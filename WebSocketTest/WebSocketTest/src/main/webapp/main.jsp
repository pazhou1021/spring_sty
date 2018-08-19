<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/mq.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/ptloginout.js"></script>
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/mq.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
	<style type="text/css">
		.flot {
			width: 63%;
			position: relative;
			float: right;
			margin-right: 30%;
		}
		
		.myself {
			text-align: right;
			padding: 10px;
		}
		
		.myimg {
			float: right;
			width: 40px;
			height: 40px;
			margin-right: 10px;
		}
		p,a{
			cursor: pointer;
		}
		span{
		    border-radius: 9px;
		}
	</style>
</head>
<body>
	<p>当前用户：${user.name }</p>
	<div class="panel main-panel" id="panel-0" style="position: absolute;float: right;margin-left: 71em;margin-right: 3%;display: none;transition: -webkit-transform 0.4s cubic-bezier(0, 1, 0, 1);transform: translate3d(-100%, 0px, 0px);">
		<div id="panelBodyWrapper-0" class="panel_body_container" style=" bottom: 50px;">
			<div id="panelBody-0" class="panel_body ">
				<div class="panel selected" id="panel-1">
					<header id="panelHeader-1" class="panel_header">
						<h1 id="panelTitle-1" class="text_ellipsis padding_20">会话</h1>
						<input type="hidden" id="userName" value="${user.name }"/>
					</header>

					<div id="panelBodyWrapper-1" class="panel_body_container" style="top: 45px; ">
						<div id="panelBody-1" class="panel_body ">
							<div id="current_chat_scroll_area" class="scrollWrapper" style="overflow: auto;" cmd="void">
								<ul id="current_chat_list" class="list list_white" style="transition-property: -webkit-transform; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) translateZ(0px);">
									<c:forEach items="${usersMap }" var="map">
									<li id="recent-item-friend-78636695" class="list_item" _uin="78636695" _type="friend" cmd="clickMemberItem">
										<a class="avatar" cmd="clickMemberAvatar" _uin="78636695" _type="friend">
											<img src="${pageContext.request.contextPath }/img/girl.jpeg">
										</a>
										<p class="member_nick" id="userNick-78636695">${map.value.name }</p>
									</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="panel chat-panel flot" id="panel-5" cmd="void" style="transition: -moz-Transform 0.4s cubic-bezier(0, 1, 0, 1) 0s; transform: translate3d(0px, 0px, 0px); display: block;">
		<header style="background-color: #1a1919;" id="panelHeader-5" class="panel_header">
			<h1 id="toName" class="text_ellipsis padding_20" style="color: white;padding-left: 100px;">${user.name }</h1>

			<!-- <button id="panelRightButton-5" class="btn btn_small btn_right btn_black btn_setting" cmd="clickRightButton">
		        <span id="panelRightButtonText-5" class="btn_text">关闭</span>
		    </button>
		     -->
		</header>

		<div id="panelBodyWrapper-5" class="panel_body_container" style="top: 45px; bottom: 50px; overflow: auto;">
			<div id="panelBody-5" class="panel_body chat_container ${user.name }" style="transition-property: -webkit-transform; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) scale(1) translateZ(0px);">
				
			</div>
		</div>

		<footer id="panelFooter-5" class="chat_toolbar_footer">
			<div class="chat_toolbar">
				<div id="add_face_btn" class="btn btn_face">
					<span class="btn_img"></span>
				</div>
				<textarea id="info" class="input input_white chat_textarea"></textarea>
				<button onclick="send1()" id="send_chat_btn" class="btn btn_small btn_blue" cmd="sendMsg">
			        <span class="btn_text">发送</span>
			    </button>
				<textarea id="" class="input input_white chat_textarea hidden_textarea" style="height: 32px; width: 564px;"></textarea></div>
			<!--<iframe id="panel_uploadFilIframe" name="panel_uploadFilIframe" style="display:none"></iframe>-->
		</footer>

	</div>
</body>
<script type="text/javascript">
	$(function(){
		$(".list_white").find("li").each(function(i,dt){
			$(this).click(function(){
				$(".chat_container").css("display","none");
				if($("div").is("."+$(this).children("p").html())){
					$("."+$(this).children("p").html()).css("display","");
				}else{
					$("#panelBodyWrapper-5").after(
							"<div class='panel_body chat_container "+$(this).children("p").html()+
							"' style='transition-property: -webkit-transform; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) scale(1) translateZ(0px);'></div>");
				}
				$(this).children("span").remove();
				$("#toName").html($(this).children("p").html());
			});
		});
	});
	
	var websocket = null;  
	//var username = sessionStorage.getItem("name");
	  
	//判断当前浏览器是否支持WebSocket  
	if ('WebSocket' in window) {  
	    websocket = new WebSocket("ws://" + document.location.host + "/WebSocketTest/websocket/" + $("#userName").val());  
	} else {  
	    alert('当前浏览器 Not support websocket')  
	}  
	  
	//连接发生错误的回调方法  
	websocket.onerror = function() {  
	    console.log("WebSocket连接发生错误");  
	};  
	  
	//连接成功建立的回调方法  
	websocket.onopen = function() {  
		console.log("WebSocket连接成功");  
	}  
	  
	//接收到消息的回调方法  
	websocket.onmessage = function(event) {
		var info=event.data.split(",");
		if(!$("div").is("."+info[1])){
			$("#panelBodyWrapper-5").after("<div class='panel_body chat_container "+info[1]+"' style='display:none; transition-property: -webkit-transform; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) scale(1) translateZ(0px);'>"+
			"<div class='chat_content_group buddy  ' _sender_uin='1564776288'>"+
			"<img class='chat_content_avatar' src='${pageContext.request.contextPath }/img/girl.jpeg' height='40px' width='40px'>"+
			"<p class='chat_nick'>"+info[1]+"</p><p class='chat_content '>"+info[0]+"</p></div></div>");
			$(".list_white").find("li").each(function(i,dt){
				if(info[1]==$(this).children("p").html()){
					$(this).children("p").after("<span class='layui-badge'>1</span>");
				};
			});
		}else{
			$("."+info[1]).append("<div class='chat_content_group buddy ' _sender_uin='1564776288'>"+
			"<img class='chat_content_avatar' src='${pageContext.request.contextPath }/img/girl.jpeg' height='40px' width='40px'>"+
			"<p class='chat_nick'>"+info[1]+"</p><p class='chat_content '>"+info[0]+"</p></div>");
		}
	}  
	  
	//连接关闭的回调方法  
	websocket.onclose = function() {  
		console.log("WebSocket连接关闭");
	}  
	  
	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。  
	window.onbeforeunload = function() {  
	    //closeWebSocket();  
	}  
	  
	//关闭WebSocket连接  
	function closeWebSocket() {  
		console.log("close");
	    websocket.close();  
	} 
</script>

<script type="text/javascript">
	function send1(){
		var employees ={"To":$("#toName").html(),"massege":$("#info").val()}
		websocket.send(JSON.stringify(employees));
		$("."+$("#toName").html()).append("<div class='myself' _sender_uin='1564776288'><img class='myimg' src='${pageContext.request.contextPath }/img/girl.jpeg' height='40px' width='40px'>"+
				"<p class='chat_nick' style='margin-right: 61px;position: relative;'>"+$("#userName").val()+"</p>"+
				"<p class='chat_content ' style='margin-right: 9px;'>"+$("#info").val()+"</p></div>");
		$("#info").val("");
	}
</script>
</html>
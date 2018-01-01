<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
<base href="${ctp}/">
<title>wcc</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="static/login/bootstrap.min.css" />
<link rel="stylesheet" href="static/login/css/camera.css" />
<link rel="stylesheet" href="static/login/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="static/login/matrix-login.css" />
<link href="static/login/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="static/login/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="windou/js/quanping.js"></script>


 <style type="text/css">

      .cavs{
    	z-index:1;
    	position: fixed;
    	width:95%;
    	margin-left: 20px;
    	margin-right: 20px;
    }
  </style>
  <script>
  		//window.setTimeout(showfh,3000); 
  		var timer;
		function showfh(){
			fhi = 1;
			//关闭提示晃动屏幕，注释掉这句话即可
			timer = setInterval(xzfh2, 10); 
		};
		var current = 0;
		function xzfh(){
			current = (current)%360;
			document.body.style.transform = 'rotate('+current+'deg)';
			current ++;
			if(current>360){current = 0;}
		};
		var fhi = 1;
		var current2 = 1;
		function xzfh2(){
			if(fhi>50){
				document.body.style.transform = 'rotate(0deg)';
				clearInterval(timer);
				return;
			}
			current = (current2)%360;
			document.body.style.transform = 'rotate('+current+'deg)';
			current ++;
			if(current2 == 1){current2 = -1;}else{current2 = 1;}
			fhi++;
		};
	</script>
 
</head>
<body>
	<div style="display: none">
	    <!-- <audio src="static/login/music/fh1.mp3" autoplay=""></audio>-->
	</div>	
	<canvas class="cavs"></canvas>
	<div style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		
		<!-- 登录 -->
		<div id="windows1">
		<input id="error" type="hidden" value="${error}">
		<div id="loginbox" >
			<form action="page/login" method="post" >
				<div class="control-group normal_text">
					<h3>
						<img src="static/login/logo.png" alt="Logo" />
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i><img height="37" src="static/login/user.png" /></i>
							</span><input type="text" name="username" id="loginname" value="" placeholder="请输入用户名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly">
							<i><img height="37" src="static/login/suo.png" /></i>
							</span><input type="password" name="password" id="password" placeholder="请输入密码" class="keypad" keypadMode="full" allowKeyboard="true" value=""/>
						</div>
					</div>
				</div>
				<div style="float:right;padding-right:10%;">
					<div style="float: left;margin-top:3px;margin-right:2px;">
						<font color="white">记住我</font>
					</div>
					<div style="float: left;">
						<input name="rememberMe"  type="checkbox" style="padding-top:0px;" />
					</div>
				</div>
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						<span class="pull-right" style="padding-right:3%;"><a href="javascript:changepage(1);" class="btn btn-success">注册</a></span>
						<span class="pull-right"><input type="submit"  class="flip-link btn btn-info" value="登录"></span>
						 
					</div>
				</div>
			</form>
			<div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr">Copyright © Qyfq294908296@qq.com</span></font>
				</div>
			</div>
		</div>
		
		</div>
		<!-- 注册 -->
		<div id="windows2" style="display: none;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm" id="loginForm">
				<div class="control-group normal_text">
					<h3>
						<img src="static/login/logo.png" alt="Logo" />
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i>帐号</i>
							</span><input type="text" name="USERNAME" id="USERNAME" value="" placeholder="请输入用户名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly">
							<i>密码</i>
							</span><input type="password" name="PASSWORD" id="PASSWORD" placeholder="请输入密码" class="keypad" keypadMode="full" allowKeyboard="true" value=""/>
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly">
							<i>重输</i>
							</span><input type="password" name="chkpwd" id="chkpwd" placeholder="请重新输入密码" class="keypad" keypadMode="full" allowKeyboard="true" value=""/>
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i>姓名</i>
							</span><input type="text" name="NAME" id="name" value="" placeholder="请输入姓名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i>邮箱</i>
							</span><input type="text" name="EMAIL" id="EMAIL" value="" placeholder="请输入邮箱" />
						</div>
					</div>
				</div>
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						<span class="pull-right" style="padding-right:3%;"><a href="javascript:changepage(2);" class="btn btn-success">取消</a></span>
						<span class="pull-right"><a onclick="register();" class="flip-link btn btn-info" id="to-recover">提交</a></span>
					</div>
				</div>
			</form>
			<div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr">Copyright © FHqq313596790 2100</span></font>
				</div>
			</div>
		</div>
		</div>
		
	</div>
	<div id="templatemo_banner_slide" class="container_wapper">
		<div class="camera_wrap camera_emboss" id="camera_slide">
			<!-- 背景图片 -->
			<c:choose>
				<c:when test="${not empty pd.listImg}">
					<c:forEach items="${pd.listImg}" var="var" varStatus="vs">
						<div data-src="static/login/images/${var.FILEPATH }"></div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div data-src="static/login/images/banner_slide_01.jpg"></div>
					<div data-src="static/login/images/banner_slide_02.jpg"></div>
					<div data-src="static/login/images/banner_slide_03.jpg"></div>
					<div data-src="static/login/images/banner_slide_04.jpg"></div>
					<div data-src="static/login/images/banner_slide_05.jpg"></div>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- #camera_wrap_3 -->
	</div>

	<script type="text/javascript">
	$(function name() {
		var error=$("#error").val();
		$("#loginname").tips({
			side : 1,
			msg : error,
			bg : '#FF5080',
			time : 3
		});
	})
		//客户端校验
		function check() {
			if ($("#loginname").val() == "") {
				$("#loginname").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});
				showfh();
				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}
			if ($("#password").val() == "") {
				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});
				showfh();
				$("#password").focus();
				return false;
			}
			if ($("#code").val() == "") {
				$("#code").tips({
					side : 1,
					msg : '验证码不得为空',
					bg : '#AE81FF',
					time : 3
				});
				showfh();
				$("#code").focus();
				return false;
			}
			$("#loginbox").tips({
				side : 1,
				msg : '正在登录 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});

			return true;
		}

		
		//登录注册页面切换
		function changepage(value) {
			if(value == 1){
				$("#windows1").hide();
				$("#windows2").show();
				changeCode2();
			}else{
				$("#windows2").hide();
				$("#windows1").show();
				changeCode1();
			}
		}
		
	//注册
	function rcheck(){
		if($("#USERNAME").val()==""){
			$("#USERNAME").tips({
				side:3,
	            msg:'输入用户名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USERNAME").focus();
			$("#USERNAME").val('');
			return false;
		}else{
			$("#USERNAME").val(jQuery.trim($('#USERNAME').val()));
		}
		if($("#PASSWORD").val()==""){
			$("#PASSWORD").tips({
				side:3,
	            msg:'输入密码',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PASSWORD").focus();
			return false;
		}
		if($("#PASSWORD").val()!=$("#chkpwd").val()){
			$("#chkpwd").tips({
				side:3,
	            msg:'两次密码不相同',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#chkpwd").focus();
			return false;
		}
		if($("#name").val()==""){
			$("#name").tips({
				side:3,
	            msg:'输入姓名',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#name").focus();
			return false;
		}
		if($("#EMAIL").val()==""){
			$("#EMAIL").tips({
				side:3,
	            msg:'输入邮箱',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#EMAIL").focus();
			return false;
		}else if(!ismail($("#EMAIL").val())){
			$("#EMAIL").tips({
				side:3,
	            msg:'邮箱格式不正确',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#EMAIL").focus();
			return false;
		}
		return true;
	}
	
	//提交注册
	function register(){
		if(rcheck()){
			$.ajax({
				type: "POST",
				url: '${pageContext.request.contextPath}/registration',
		    	data: {loginacct:$("#USERNAME").val(),userpswd:$("#PASSWORD").val(),username:$("#name").val(),email:$("#EMAIL").val()},
				dataType:'json',
				cache: false,
				success: function(result){
					if (result.success) {
						$("#windows2").hide();
						$("#windows1").show();
						$("#loginbox").tips({
							side : 1,
							msg : result.msg,
							bg : '#68B500',
							time : 3
						});
						changeCode1();
					} else {
						$("#USERNAME").tips({
							side : 1,
							msg : "注册失败请重新注册",
							bg : '#FF5080',
							time : 15
						});
						showfh();
						$("#USERNAME").focus();
					}
				}
			});
		}
	}
	
	//邮箱格式校验
	function ismail(mail){
		return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
	}
	//js  日期格式
	function date2str(x,y) {
	     var z ={y:x.getFullYear(),M:x.getMonth()+1,d:x.getDate(),h:x.getHours(),m:x.getMinutes(),s:x.getSeconds()};
	     return y.replace(/(y+|M+|d+|h+|m+|s+)/g,function(v) {return ((v.length>1?"0":"")+eval('z.'+v.slice(-1))).slice(-(v.length>2?v.length:2))});
	 	};
	</script>

	<script src="static/login/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-1.7.2.js"></script>
	<script src="static/login/js/jquery.easing.1.3.js"></script>
	<script src="static/login/js/jquery.mobile.customized.min.js"></script>
	<script src="static/login/js/camera.min.js"></script>
	<script src="static/login/js/templatemo_script.js"></script>
	<script src="static/login/js/ban.js"></script>
	<script type="text/javascript" src="static/js/jQuery.md5.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
	

</body>

</html>
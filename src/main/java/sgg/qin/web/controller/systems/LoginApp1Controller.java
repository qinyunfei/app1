package sgg.qin.web.controller.systems;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import sgg.qin.util.HttpClientUtil;

@Controller
@RequestMapping(value = "/page")
public class LoginApp1Controller {

	@Autowired
	private HttpClientUtil httpClientUtil;

	// 客户端登录操作
	@RequestMapping(value = "/tologin")
	// token="+token+"&sessionkey"+"&uservalue="+username;
	public String showLoginForm(@RequestParam(value = "token", required = false) String token,
			@CookieValue(value = "JSESSIONID", required = false) String sessionId, HttpSession session,
			ModelMap modelMap) throws ClientProtocolException, URISyntaxException, IOException {
		System.out.println(">>>>>"+session.getAttribute("isLogin"));
		Object isLogin = session.getAttribute("isLogin");
		// 首先判定局部会话是否存在
		if (isLogin!=null) {
			//局部会话存在重定向到首页即可
			return "redirect:/page/index";
			
		} else {
			// 局部会话不存 判定token是否存在
			if (StringUtils.isEmpty(token)) {
				//token不存在重定向到认证中心 获取登录页面
				return "redirect:http://localhost:8080/noclient/page/tologin?service=http://localhost:8080/app1/page/tologin";
			} else {
				// 如果token存在则去认证中心验证token是否有效 
				Map<String, String> map = new HashMap<>();
				map.put("token", token);
				map.put("localld", sessionId);
				// 获取响应的验证结果
				Map<String, Object> doHttp = httpClientUtil.doHttp(RequestMethod.GET, "http", "localhost", 8080,
						"noclient/page/verify", map, null, new ResponseHandler<Map<String, Object>>() {

							@Override
							public Map<String, Object> handleResponse(HttpResponse response)
									throws ClientProtocolException, IOException {
								// TODO Auto-generated method stub
								StatusLine statusLine = response.getStatusLine();
								HttpEntity entity = response.getEntity();
								if (statusLine.getStatusCode() >= 300) {
									throw new HttpResponseException(statusLine.getStatusCode(),
											statusLine.getReasonPhrase());
								}
								if (entity == null) {
									throw new ClientProtocolException("响应不包含内容");
								}
								return JSON.parseObject(entity.getContent(), Map.class);
							}
						});

				Integer status = (Integer) doHttp.get("status");
				System.out.println("开始验证");
				if (status == 0) {
					//验证成功就设置session为登录状态
					System.out.println("验证成功 设置局部会话状态isLogin：true");
					
					session.setAttribute("isLogin",true);
					session.setAttribute("user", doHttp.get("user"));
					session.setAttribute("sessionkey", doHttp.get("sessionkey"));
					return "redirect:/page/index";
				} else {
					// 验证失败返回错误页面
					modelMap.put("msg", doHttp.get("msg"));
					return "errer";
				}
			}

		}

	}

	@RequestMapping(value = "/index")
	public String toindex() {
		return "index";
	}

}

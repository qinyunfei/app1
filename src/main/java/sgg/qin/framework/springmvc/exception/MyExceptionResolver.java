package sgg.qin.framework.springmvc.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;

//自定义异常处理程序解析器 
public class MyExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		ModelAndView mv = new ModelAndView();
		System.out.println("==============异常开始=============");
		ex.printStackTrace();
		System.out.println("==============异常结束=============");
		// handler为出异常的方法对象
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 判断方法有没有实现ResponseBody注解
		ResponseBody methodAnnotation = handlerMethod.getMethodAnnotation(ResponseBody.class);
		if (methodAnnotation != null) {
			FastJsonJsonView view = new FastJsonJsonView();
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("success", false);
			if (ex.getMessage()!=null) {
				if (ex.getMessage().startsWith("#")) {
					attributes.put("msg", ex.getMessage());
				} else {
					attributes.put("msg", "操作失败");
				}
			}else {
				attributes.put("msg", "操作失败");
			}
			
			attributes.put("exception", ex.getMessage());
			view.setAttributesMap(attributes);
			mv.setView(view);
		} else {
			mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
			mv.setViewName("error-500");
		}
		return mv;

	}

	// 通过aop 判断异常类型决定调用那个方法
	public ModelAndView myException(HttpServletRequest request, HttpServletResponse response, Object handler,
			UnauthorizedException ex) {
		// TODO Auto-generated method stub
		System.out.println("==============UnauthorizedException异常开始=============");
		ex.printStackTrace();
		System.out.println("==============UnauthorizedException异常结束=============");
		ModelAndView mv = new ModelAndView();
		// handler为出异常的方法对象
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 判断方法有没有实现ResponseBody注解 有返回json数据 无返回视图
		ResponseBody methodAnnotation = handlerMethod.getMethodAnnotation(ResponseBody.class);

		if (methodAnnotation != null) {
			FastJsonJsonView view = new FastJsonJsonView();
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("success", false);
			attributes.put("msg", "没有权限");
			attributes.put("exception", ex.getMessage());
			view.setAttributesMap(attributes);
			mv.setView(view);
		} else {
			mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
			mv.setViewName("error-406");
		}

		return mv;

	}

}

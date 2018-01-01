package sgg.qin.framework.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import sgg.qin.util.PageData;
import sgg.qin.web.controller.base.BaseController;

@Aspect // 声明切面 该切面为springmvc切面需要注入到springmvc中
public class ActionAspec {

	// 定义环绕通知 封装action参数为PageData
	//@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object PageData(ProceedingJoinPoint pjp) throws Throwable {
		// 获取被拦截的方法对象;
		Method method = ((MethodSignature) pjp.getSignature()).getMethod();
		Object target = pjp.getTarget();
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paraNameArr = u.getParameterNames(method);
		Object[] args = pjp.getArgs();
		PageData<String, Object> pageData = new PageData();
		for (int i = 0; i < paraNameArr.length; i++) {
			pageData.put(paraNameArr[i], args[i]);
		}
		BaseController.threadLocal.set(pageData);

		Object retVal = pjp.proceed();
		return retVal;
	}

	// 定义环绕通知 对springmvc的异常处理进行细分
	@Around("execution(* sgg.qin.framework.springmvc.exception.MyExceptionResolver.resolveException(..))")
	public Object MyHandlerExceptionResolver(ProceedingJoinPoint pjp) throws Throwable {
		try {
			// 获取参数
			Object[] args = pjp.getArgs();
			// 获取异常对象
			Object object = args[args.length - 1];
			Method method = ((MethodSignature) pjp.getSignature()).getMethod();
			Class<?>[] parameterTypes = method.getParameterTypes();
			if (object.getClass() != Exception.class) {
				parameterTypes[args.length - 1] = object.getClass();
				Method declaredMethod = pjp.getTarget().getClass().getDeclaredMethod("myException", parameterTypes);
				Object invoke = declaredMethod.invoke(pjp.getTarget(), args);
				return invoke;
			} else {
				return pjp.proceed();
			}

		} catch (Exception e) {
			// TODO: handle exception
			return pjp.proceed();
		}

	}

}

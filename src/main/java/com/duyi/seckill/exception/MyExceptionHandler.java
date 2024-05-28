package com.duyi.seckill.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Component
public class MyExceptionHandler implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);

        ex.printStackTrace();

        // 根据不同错误转向不同页面
        if (ex instanceof UserException) {
            return new ModelAndView("error", model);
        } else if (ex instanceof SeckillException) {
            return new ModelAndView("error", model);
        } else {
            return new ModelAndView("error", model);
        }
    }
}
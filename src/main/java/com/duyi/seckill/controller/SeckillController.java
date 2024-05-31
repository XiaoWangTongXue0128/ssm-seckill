package com.duyi.seckill.controller;


import cn.hutool.core.util.ObjectUtil;
import com.duyi.seckill.dto.ResponseResult;
import com.duyi.seckill.dto.SeckillUrl;
import com.duyi.seckill.entity.SeckillItem;
import com.duyi.seckill.entity.SeckillOrder;
import com.duyi.seckill.entity.User;
import com.duyi.seckill.exception.SeckillException;
import com.duyi.seckill.exception.UserException;
import com.duyi.seckill.service.SeckillItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    SeckillItemService seckillItemService;


    @RequestMapping("/seckillList")
    public ModelAndView seckillList() {
        // 参数Model

        List<SeckillItem> list = seckillItemService.getSeckillList();

        ModelAndView mv = new ModelAndView();

        mv.addObject("list", list);
        mv.setViewName("seckill_list");

        return mv;
    }

    @RequestMapping(value = "/detail/{seckillId}", method = RequestMethod.GET)
    public String seckillDetail(@PathVariable("seckillId") Integer seckillId, Model model,HttpSession session) {
        // 参数Model

//        获取秒杀项目商品
        SeckillItem item = seckillItemService.getSeckillItem(seckillId);

        if (ObjectUtil.isEmpty(item)) {
            throw new SeckillException("商品参数错误");
        }

        model.addAttribute("item", item);
        session.setAttribute("sum_price",item.getPrice());

        return "detail";
    }


    /**
     * 获取服务器时间
     *
     * @return
     */
    @RequestMapping(value = "/now", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<Long> now() {
        Date now = new Date();
        return new ResponseResult<Long>(true, now.getTime(), "ok");
    }

    /**
     * 获取商品的秒杀地址
     */
    @RequestMapping(value = "/getSeckillUrl/{seckillId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<SeckillUrl> getSeckillUrl(@PathVariable("seckillId") Integer seckillId, HttpSession session) {

        // 判断用户是否登录，没有登录直接报错
        User user = (User) session.getAttribute("user");
        if (ObjectUtil.isEmpty(user)) {
            // 没有登录
            throw new UserException("没有登录");
        }

        ResponseResult<SeckillUrl> result = new ResponseResult<SeckillUrl>();
        // 获取秒杀商品的下单地址URL


        try {
            SeckillUrl seckillUrl = seckillItemService.getSeckillUrl(seckillId);
            result.setData(seckillUrl);
            result.setSuccess(true);
            result.setMessage("ok");
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            return result;
        }

    }


    @RequestMapping("/orderPay")
    public String orderPay(String orderCode, Model model) {

        SeckillOrder seckillOrder = seckillItemService.getSeckillOrder(orderCode);

        model.addAttribute("seckillOrder", seckillOrder);
        model.addAttribute("item", seckillItemService.getSeckillItem(seckillOrder.getSeckillItemId()));
        // 每次请求订单，时间固定的
        model.addAttribute("orderTime", seckillOrder.getCreateTime());

        return "order";
    }

    /**
     * 秒杀下单
     */
    @RequestMapping(value = "/execute/{seckillId}/{md5}", method = RequestMethod.GET)
//    @ResponseBody
    public String executeSeckill(@PathVariable("seckillId") Integer seckillId,
                                 @PathVariable("md5") String md5, HttpSession session) {

//        ResponseResult<SeckillUrl> result = new ResponseResult<SeckillUrl>();

        // 1 验证请求的URL是否正确
        boolean access = seckillItemService.verifySeckillMD5(seckillId, md5);
        if (!access) {
            // 请求的URL,MD5验证不通过
//            result.setSuccess(false);
//            result.setMessage("md5 fail");
//            return result;

            throw new SeckillException("请求的URL,MD5验证不通过");
        }
        // 2 是否已经登录
        User user = (User) session.getAttribute("user");
        if (ObjectUtil.isEmpty(user)) {
//            result.setSuccess(false);
//            result.setMessage("user not login");
//            return result;

            throw new SeckillException("用户没有登录");
        }

        // 3 限制每一个用户只可以发送一次请求，第二次请求（在5分钟内）不在处理


        // 减库存，redis做并发减库存操作
        boolean success = seckillItemService.executeSeckill(user, seckillId);
        // 下订单
        if (!success) {
//            result.setSuccess(false);
//            result.setMessage("order fail");
//            return result;

            throw new SeckillException("下单失败");
        }

        SeckillOrder seckillOrder = seckillItemService.createOrder(user, seckillId);


        return "redirect:/seckill/orderPay?orderCode=" + seckillOrder.getOrderCode();
    }

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public ModelAndView pay(String orderCode) {
        ModelAndView mv = new ModelAndView();

        // 超时订单不能支付
        SeckillOrder seckillOrder = seckillItemService.getSeckillOrder(orderCode);

        if (seckillOrder.getState() == 4) {
            mv.setViewName("error");
            mv.addObject("ex","order expire");
            return mv;
        }

        // 重复支付，需要判断，如果已经支付成功，不要在跳转支付宝或微信


        // 支付验证
        seckillItemService.pay(orderCode);

        mv.addObject("orderId", orderCode);
        mv.setViewName("alipay/alipay");

        return mv;
    }

}

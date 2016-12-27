package co.bugu.sso.controller;

import co.bugu.sso.constant.GlobalConstant;
import co.bugu.sso.model.SsoUser;
import co.bugu.sso.service.ISsoUserService;
import co.bugu.sso.util.UserUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import co.bugu.framework.util.EncryptUtil;
import co.bugu.framework.util.JedisUtil;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by daocers on 2016/6/29.
 */
@RequestMapping("/auth")
@Controller
public class AuthController {

    @Autowired
    ISsoUserService userService;
    @Autowired
    RequestMappingHandlerMapping handlerMapping;

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(String username, String password,
                         @RequestParam(value = "url", required = false) String url, ModelMap modelMap, RedirectAttributes attributes,
                         HttpServletRequest request,
                         HttpServletResponse response){
        try{
            SsoUser user = new SsoUser();
            user.setUsername(username);
            password = EncryptUtil.md5(password);
            user.setPassword(password);
            user = userService.selectByObject(user);
            if(user == null){
                modelMap.put("message", "账号/密码错误");
                modelMap.put("username", username);
                modelMap.put("password", password);
            }else{
                logger.debug("登录成功");
                String ticket = UserUtil.getTicket(username);
                logger.debug("登录后成功票据信息： {}", ticket);
                JedisUtil.set(ticket, user.getId() + "");
                JedisUtil.expire(ticket, GlobalConstant.getInt("expire-time") * 60);
                Cookie cookie = new Cookie("ticket", ticket);
                cookie.setPath("/");
                cookie.setDomain(request.getServerName());
                response.addCookie(cookie);
                logger.debug("登录成功，准备跳转到原始url，附上ticket信息");
                attributes.addAttribute("ticket", ticket);
                return "redirect:" + url;
//                response.sendRedirect(url + "?ticket=" + ticket);
//                return null;
            }
        }catch (Exception e){
            logger.error("登录异常", e);
            modelMap.put("message", "系统异常");
        }
        return "login";
    }

    @RequestMapping(value = "/singOut")
    public String signOut(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            logger.warn("数据紊乱， 没有登录的账户无法请求注销操作");
            return "login";
        }else{
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("ticket")){
                    JedisUtil.del(cookie.getValue());
                }
            }
        }
        return "login";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String toLogin(@RequestParam(value = "url", required = false) String url, ModelMap modelMap){
        if(url == null || "".equals(url)){
            url = "/";
        }
        modelMap.put("url", url);
        return "login";
    }

    @RequestMapping(value = "/check")
    public String check(HttpServletRequest request,
                        String url, RedirectAttributes attributes){
        try{
            logger.debug("校验中心，票据校验开始");
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                logger.debug("校验中心，有cookie，开始筛选");
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("ticket")){
                        logger.debug("校验中心，cookie中发现票据");
                        String ticket = cookie.getValue();
//                        response.sendRedirect(url + "?ticket=" + ticket);
                        logger.debug("校验中心，准备跳转原始url，带上ticket信息");
                        attributes.addAttribute("ticket", ticket);
                        return "redirect:" + url;
                    }
                }
            }
            logger.debug("校验中心，没有cookie");

        }catch (Exception e){
            logger.error("校验失败");
        }
        if(url == null){
            url = "/";
        }
        attributes.addAttribute("url", url);
        return "redirect:login";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> map = handlerMapping.getHandlerMethods();
        Iterator<Map.Entry<RequestMappingInfo, HandlerMethod>> iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Map<String, Object> info = new HashedMap();
            Map.Entry<RequestMappingInfo, HandlerMethod> entry = iter.next();
            RequestMappingInfo requestMappingInfo = entry.getKey();
            HandlerMethod handlerMethod = entry.getValue();
            String url = requestMappingInfo.getPatternsCondition().getPatterns().toArray(new String[1])[0];
            String beanName =handlerMethod.getBean().toString();
            String controllerPath = handlerMethod.getBeanType().getDeclaredAnnotation(RequestMapping.class).value()[0];
            String actionPath = handlerMethod.getMethodAnnotation(RequestMapping.class).value()[0];
            RequestMethod[] method = handlerMethod.getMethod().getDeclaredAnnotation(RequestMapping.class).method();
            StringBuilder methodBuffer = new StringBuilder();
            if(method.length > 0){
                for(RequestMethod mtd: method){
                    methodBuffer.append(mtd.name()).append(",");
                }
            }
            boolean isBeanRestController = handlerMethod.getBeanType().getDeclaredAnnotation(RestController.class) != null;
            boolean isMethodResponseBody = handlerMethod.getMethodAnnotation(ResponseBody.class) != null;

            String allowedMethod = methodBuffer.length() > 0
                    ? methodBuffer.substring(0, methodBuffer.length() -1) : "";
            info.put("url", url);
            info.put("beanName", beanName);
            info.put("controllerPath", controllerPath);
            info.put("actionPath", actionPath);
            info.put("allowedMethod", allowedMethod);
            info.put("isApi", isBeanRestController ? true : isMethodResponseBody);
            list.add(info);
        }
        return JSON.toJSONString(list, SerializerFeature.WriteNullStringAsEmpty);
    }

    @RequestMapping("list")
    public String list(){
        return "template/frame";
    }

    @RequestMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        String originalName = file.getOriginalFilename();
        String fileName = "d:/img/" + originalName;
        File target = new File(fileName);
        target.mkdirs();
        file.transferTo(target);
        return "1";

    }

}

package com.bookStore.client.user.handler;

import com.bookStore.client.user.service.UserService;
import com.bookStore.utils.ActiveCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.bookStore.commons.beans.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/client/user")
public class UserHandler {

    @Autowired
    UserService userService;
    //判断验证码是否正确
    @ResponseBody
    @RequestMapping("checkCode.do")
    public String checkCode(HttpSession session,String checkCode){
        String checkcode= (String) session.getAttribute("checkcode_session");
        if(checkCode.equals(checkcode)){
            return "T";
        }
        return "F";
    }

    @ResponseBody
    @RequestMapping("checkEmail.do")
    public String checkEmail(String email){
        User user=userService.findEmail(email);
        if(user!=null){
            return "T";
        }
        return "F";
    }

    @ResponseBody
    @RequestMapping("checkUsername.do")
    public String checkUsername(String username){
        User user=userService.findUsername(username);
        if(user!=null){
            return "T";
        }
        return "F";
    }

//    用户注册
    @RequestMapping("register.do")
    public String register(User user, HttpServletRequest request){
        user.setActiveCode(ActiveCodeUtils.createActiveCode());
        int row = userService.addUser(user,request);
        if(row>0){
            return "/client/registersuccess.jsp";
        }else{
            return "/client/register.jsp";
        }

    }
//   用户激活
    @RequestMapping("activeUser.do")
    public String activeUser(String activeCode){
        int row = userService.activeUer(activeCode);
        if(row>0){
            return "/client/activesuccess.jsp";
        }else{
            return "/client/activefail.jsp";
        }
    }
    @RequestMapping("/myAccount.do")
    public String myAccount(HttpSession session,HttpServletRequest request){
        User user =(User)session.getAttribute("login_user");
        if(user!=null){
            return "/client/myAccount.jsp";
        }else {
            user=autologin(request);
            if(user!=null){
                session.setAttribute("login_user",user);
                return "/client/myAccount.jsp";
            }
            return "/client/login.jsp";
        }
    }

    private User autologin(HttpServletRequest request) {
        String username = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        User user = new User();
        try {
            for(Cookie cookie:cookies){
                if("cookie_username".equals(cookie.getName())){
                    username=URLEncoder.encode(cookie.getValue(),"utf-8");
                }
            }
            for(Cookie cookie:cookies){
                if("cookie_password".equals(cookie.getName())){
                    password=URLEncoder.encode(cookie.getValue(),"utf-8");
                }
            }
        }catch (Exception e){
        }
        user.setUsername(username);
        user.setPassword(password);
        return userService.findUserByLogin(user);
    }

    @RequestMapping("/login.do")
    public String login(User user, String remember, String autologin,HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model){
        User login_user = userService.loginUser(user);
        if(login_user!=null){
            if(login_user.getState()==1){
                if("1".equals(autologin)){
                    addCookie(autologin,user,response,request);
                }else if("1".equals(remember)){
                    addCookie(autologin,user,response,request);
                }
                session.setAttribute("login_user",login_user);
                return "/client/index.jsp";
            }else {
                model.addAttribute("login_error","用户未激活，请激活后使用！");
                return "/client/login.jsp";
            }

        }else {
            model.addAttribute("login_error","用户或密码不正确！");
            return "/client/login.jsp";
        }
    }

    private void addCookie(String autologin, User user, HttpServletResponse response, HttpServletRequest request) {
        try {
            if("1".equals(autologin)){
                Cookie cookie=new Cookie("cookie_username", URLEncoder.encode(user.getUsername(),"utf-8"));
                cookie.setMaxAge(60*60*24*3);
                cookie.setPath(request.getContextPath()+"/");
                //指定cookie的顶级访问路径，
                // 并不是把cookie存在该目录下，只有当访问该目录的子目录时，才可以使用该cookie
                //Cookie的SetPath设置cookie的路径，这个路径直接决定服务器的请求是否会从浏览器中加载某些cookie。
                //
                //首先默认情况如果不设置cookie的path，默认是 /项目名称/当前路径的上一层地址如：
                // 请求路径：/cookie_demo/servlet/login， cookie的路径：/cookie_demo/servlet
                //
                //如果我们设置path，如果当前访问的路径包含了cookie的路径（当前访问路径在cookie路径基础上要比cookie的范围小）cookie就会加载到request对象之中。
                response.addCookie(cookie);
                Cookie cookie1 = new Cookie("cookie_password",URLEncoder.encode(user.getPassword(),"utf-8"));
                cookie1.setMaxAge(60*60*24*3);
                cookie1.setPath(request.getContextPath()+"/");
                response.addCookie(cookie1);


            }else{
                Cookie cookie = new Cookie("cookie_username",URLEncoder.encode(user.getUsername(),"utf-8"));
                cookie.setMaxAge(60*60*24*3);
                cookie.setPath(request.getContextPath()+"/");
                response.addCookie(cookie);
                //移除密码
                Cookie cookie1 = new Cookie("cookie_password",null);
                cookie1.setMaxAge(60*60*24*3);
                cookie1.setPath(request.getContextPath()+"/");
                response.addCookie(cookie1);

            }
        }catch (Exception e){

        }
    }

    @RequestMapping("/modifyuserinfo.do")
    public String modifyuserinfo(User user,HttpSession session){
        System.out.println(user+" "+"123");
        int row =userService.modifyuserinfo(user);
        User login_user = userService.loginUser(user);
        session.setAttribute("login_user",login_user);
        if(row>0){
            session.setAttribute("login_user",null);//修改成功退出登录，置session为null防止，通过session登录成功
            session.setAttribute("login_error","用户信息修改成功，请重新登录！");
            return "/client/login.jsp";
        }else {
            session.setAttribute("login_error","用户信息修改失败，请稍后重试");
            return "/client/modifyuserinfo.jsp";
        }
    }
    @RequestMapping("/logout.do")
    public String logout(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        session.removeAttribute("login_user");
//        ***把cookie置为null需要以下三行，需要cookie名和cookie设置目录***
//        Cookie cookie= new Cookie("cookie_username",null);
//        cookie.setPath(request.getContextPath()+"/");
//        response.addCookie(cookie);
//        Cookie cookie1= new Cookie("cookie_password",null);
//        cookie1.setPath(request.getContextPath()+"/");
//        response.addCookie(cookie1);
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if("cookie_username".equals(cookie.getName())){
                //**把cookie删除需要以下三行，时间为0，路径保持不变，
                cookie.setMaxAge(0);//0，表示从客户端电脑或浏览器内存中删除此cookie。
                cookie.setPath(request.getContextPath()+"/");
                response.addCookie(cookie);

            }
            if("cookie_password".equals(cookie.getName())){
                cookie.setMaxAge(0);//0，表示从客户端电脑或浏览器内存中删除此cookie。
                cookie.setPath(request.getContextPath()+"/");
                response.addCookie(cookie);
            }
        }
        return "/client/login.jsp";
    }
}

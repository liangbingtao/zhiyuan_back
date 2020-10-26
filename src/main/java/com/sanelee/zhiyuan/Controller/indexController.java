package com.sanelee.zhiyuan.Controller;

import com.sanelee.zhiyuan.Enums.ResultEnum;
import com.sanelee.zhiyuan.Mapper.UserExtMapper;
import com.sanelee.zhiyuan.Mapper.UserMapper;
import com.sanelee.zhiyuan.Model.Result;
import com.sanelee.zhiyuan.Model.User;
import com.sanelee.zhiyuan.Model.UserExample;
import com.sanelee.zhiyuan.Service.ProfessionService;
import com.sanelee.zhiyuan.Util.MD5Util;
import com.sanelee.zhiyuan.Util.PhoneCode;
import com.sanelee.zhiyuan.Util.ResultUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/front/*")
public class indexController {
    @Autowired
    private UserExtMapper userExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProfessionService professionService;

    @Data
    class UserInfo {
        private String phone;
        private String code;
    }

    @GetMapping("/")
    @ResponseBody
    public Result index(HttpServletRequest request) {

        String token = request.getParameter("token");
        User user = userExtMapper.findByToken(token);
        if (user == null){
            return (ResultUtil.error(ResultEnum.USER_NOTFOUND));
        }else{
            return ResultUtil.success(user);
        }
//        return user;

//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if ("token".equals(cookie.getName())) {
//                String token = cookie.getValue();
//                User user = userExtMapper.findByToken(token);
//                if (user != null) {
//                    request.getSession().setAttribute("user", user);
//                }
//            }
//        }
//        return "index";
    }


    //注册方法
    @RequestMapping("/addregister")
    @ResponseBody
    public Result register(HttpServletRequest request, Map<String, Object> map, Model model) {
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        String userArea = request.getParameter("userArea");
        String userSort = request.getParameter("userSort");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        if (userArea.equals("暂无")) {
            map.put("msg", "请选择考生所在省份!");
            return ResultUtil.success(map);
//            return "请选择考生所在省份!";
        } else if (userSort.equals("暂无")) {
            map.put("msg", "请选择考生文理科!");
            return ResultUtil.success(map);
//            return "请选择考生文理科";
        }
        UserExample userExample1 = new UserExample();
        UserExample userExample2 = new UserExample();
        userExample1.createCriteria()
                .andUsernameEqualTo(username);
        userExample2.createCriteria()
                .andUserphoneEqualTo(userPhone);
        List<User> users1 = userMapper.selectByExample(userExample1);
        List<User> users2 = userMapper.selectByExample(userExample2);
        if (password.equals(password2) && users1.size() == 0 && users2.size() == 0) {
            User user = new User();
            user.setUsername(username);
            user.setUserphone(userPhone);
            user.setUserarea(userArea);
            user.setUsersort(userSort);
            String pwd = MD5Util.string2MD5(password);
            user.setPassword(pwd);
            userMapper.insertSelective(user);
            System.out.println(username);
            System.out.println(userPhone);
            map.put("msg", "注册成功,请登录！");
            return ResultUtil.success(map);
//            return "login";
        } else if (users1.size() != 0) {
            map.put("msg", "该用户名已存在！");
            return ResultUtil.error(ResultEnum.USERNAME_EXISTED);
//            return "该用户名已存在！";
        } else if (users2.size() != 0) {
            map.put("msg", "该手机号已经注册！");
            return ResultUtil.error(ResultEnum.PHONE_REGISTED);
//            return "该手机号已经注册！";
        } else {
            map.put("msg", "密码不一致或用户名已存在！");
            return ResultUtil.error(ResultEnum.PASSWORD_NOTSAME);
//            return "密码不一致或用户名已存在！";
        }
    }

    //登陆方法
    @RequestMapping("/addlogin")
    @ResponseBody
    public Result addlogin(HttpServletRequest request,
                           HttpServletResponse response,
                           Map<String, Object> map,
                           Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String pwd = MD5Util.string2MD5(password);
        HttpSession session = request.getSession();


        User user = userExtMapper.loginquery(username, pwd);
        return ResultUtil.success(user);
//        return user;

//        if (user != null) {
//            String token = UUID.randomUUID().toString();
//            User updateUser = new User();
//            updateUser.setToken(token);
//            UserExample example = new UserExample();
//            example.createCriteria().andUsernameEqualTo(user.getUsername());
//            userMapper.updateByExampleSelective(updateUser, example);
//            response.addCookie(new Cookie("token", token));
//            session.setAttribute("loginUser", user);
//            session.setAttribute(user.getUsername(), token);
//            map.put("msg", "登陆成功");
//            model.addAttribute("user", username);
//            System.out.println(session.getId());
//            System.out.println(token);
//            return "redirect:/";
//        } else {
//            map.put("msg", "密码或账号错误！");
//            return "login";
//        }
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Result updateUser(@RequestBody User user) {

        userExtMapper.updateUser(user);
        return ResultUtil.success("success");
//        return "success";
    }

    //手机号登录
    @RequestMapping("/sendcode")
    @ResponseBody
    public Result sendcode(HttpServletRequest request, Map<String, Object> map) {
        String phone = request.getParameter("phonenumber");
        User user = userExtMapper.selectByPhone(phone);
        return ResultUtil.success(user);
//        return user;
//        if (user != null) {
//            HttpSession session = request.getSession();
//            String code = PhoneCode.vcode();
//            UserInfo userInfo = new UserInfo();
//            userInfo.setPhone(phone);
//            userInfo.setCode(code);
//            String sms = PhoneCode.getPhonemsg(phone, code);
//            if (sms.equals("-1")) {
//                map.put("msg", "获取验证码失败,请稍后重试或联系管理员！");
//                return "sendcode";
//            }
//            session.setAttribute("userInfo", userInfo);
//            return "phonelogin";
//        } else {
//            map.put("msg", "手机号未注册，请先注册！");
//            return "sendcode";
//        }

    }

    //获取验证码
    @RequestMapping("/getMessage")
    @ResponseBody
    public Result getMessage(@RequestParam(name = "phone") String phone, Map<String, String> map) {
        String code = PhoneCode.vcode();
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(phone);
        userInfo.setCode(code);
        String sms = PhoneCode.getPhonemsg(phone, code);
        if (sms.equals("-1")||sms.equals("由于系统维护，暂时无法注册！！！")) {
            map.put("sms", "-1");
            return ResultUtil.error(ResultEnum.MESSAGE_ERROR);
//            return JSON.toJSONString(map);
        } else {
            map.put("sms", "1");
            map.put("code", code);
            return ResultUtil.success(map);
//            return JSON.toJSONString(map);
        }
//        session.setAttribute("userInfo", userInfo);
//        return "phonelogin";
    }

    //验证用户输入的验证码
    @RequestMapping("/phonelogin")
    @ResponseBody
    public Result phonelogin(HttpServletRequest request,
                             HttpServletResponse response,
                             Map<String, Object> map,
                             @RequestParam("phone") String phone) {
//        HttpSession session = request.getSession();
//        String code = request.getParameter("code");
//        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
//        if (!code.equals(userInfo.getCode())) {
//            map.put("msg", "验证码输入错误！");
//            return "phonelogin";
//        } else {

        User dbuser = userExtMapper.selectByPhone(phone);
        if (dbuser==null){
            return ResultUtil.error(ResultEnum.USER_NOTFOUND);
        }
        return ResultUtil.success(dbuser);
//        return dbuser;

//        }
    }


}


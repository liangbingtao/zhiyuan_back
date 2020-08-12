package com.sanelee.zhiyuan.Controller;

import com.sanelee.zhiyuan.Mapper.UserExtMapper;
import com.sanelee.zhiyuan.Mapper.UserMapper;
import com.sanelee.zhiyuan.Model.User;
import com.sanelee.zhiyuan.Model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserExtMapper userExtMapper;
    //定义页面
    @RequestMapping("/userBasicInformation")
    public String userBasicInformation(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("loginUser");
        User userIdentify = userExtMapper.findByUserNameAndUserPhone(user.getUsername(),user.getUserphone());
        if (userIdentify.getUserrealname()==null){
            return "userBasicInformation";
        }
        else {
            return "redirect:/reportZhiyuan";
        }

    }

    @RequestMapping(value="/adduserBasicInformation",method= RequestMethod.POST)
    public String userBasicInformation(Map<String,Object> map,
                                       HttpServletRequest request,
                                       @RequestParam(name="userRealname",required = false) String userRealname,
                                       @RequestParam(name="userGender",required = false) String userGender,
                                       @RequestParam(name="userWechat",required = false) String userWechat,
                                       @RequestParam(name="userScore",required = false) Integer userScore,
                                       @RequestParam(name="userRank",required = false) Integer userRank){
        User loginuser = (User)request.getSession().getAttribute("loginUser");
        String userPhone=loginuser.getUserphone();
        if (userRealname!=("")&&userWechat!=("")&&userScore!=null&&userRank!=null){
            User user= new User();
            user.setUserrealname(userRealname);
            user.setUsergender(userGender);
            user.setUserphone(userPhone);
            user.setUserwechat(userWechat);
            user.setUserscore(userScore);
            user.setUserrank(userRank);

            userExtMapper.saveUser(user);
            return "redirect:/reportZhiyuan";
        }
        else if(userRealname.equals("")){
            map.put("msg","姓名不能为空！");
            return "userBasicInformation";
        }
        else if(userPhone.equals("")){
            map.put("msg","电话不能为空！");
            return "userBasicInformation";
        }
        else if(userWechat.equals("")){
            map.put("msg","微信号不能为空！");
            return "userBasicInformation";
        }
        else if(userScore==null){
            map.put("msg","分数不能为空！");
            return "userBasicInformation";
        }
        else if(userRank==null){
            map.put("msg","位次不能为空！");
            return "userBasicInformation";
        }
        else {
            map.put("msg","信息填写错误，请检查无误后重新提交！");
            return "userBasicInformation";
        }
    }
    @GetMapping("/vipUser")
    public String vipUser(){
        return "vipUser";
    }
}

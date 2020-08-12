package com.sanelee.zhiyuan.Controller;


import com.sanelee.zhiyuan.Mapper.GaoKaoExtMapper;
import com.sanelee.zhiyuan.Mapper.SchoolExtMapper;
import com.sanelee.zhiyuan.Mapper.UserExtMapper;
import com.sanelee.zhiyuan.Mapper.UserMapper;
import com.sanelee.zhiyuan.Model.*;
import com.sanelee.zhiyuan.Util.ExportWordUtils;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReportController {
    @Autowired
    private UserExtMapper userExtMapper;

    @Autowired
    private GaoKaoExtMapper gaoKaoExtMapper;

    @Autowired
    private SchoolExtMapper schoolExtMapper;


    @RequestMapping("/reportZhiyuan")
    public String reportZhiyuan(Map<String,Object> map,
                                HttpServletRequest request,
                                Model model){
        User user = (User)request.getSession().getAttribute("loginUser");
        User userscore = userExtMapper.getScoreByPhone(user.getUserphone());
        SchoolExample example = new SchoolExample();
        example.setDistinct(true);
        List<School> typeList = schoolExtMapper.selectTypeByExample(example);
        model.addAttribute("score",userscore);
        model.addAttribute("type",typeList);
        return "reportZhiyuan";
    }
    @RequestMapping(value = "/exportreportZhiyuan",method= RequestMethod.POST)
    public void reportZhiyuan(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam(name = "username",required = false) String username,
                              @RequestParam(name = "province1",required = false) String province1,
                              @RequestParam(name = "province2",required = false) String province2,
                              @RequestParam(name = "province3",required = false) String province3,
                              @RequestParam(name = "type1",required = false) String type1,
                              @RequestParam(name = "type2",required = false) String type2,
                              @RequestParam(name = "type3",required = false) String type3,
                              @RequestParam(name = "type4",required = false) String type4,
                              @RequestParam(name = "type5",required = false) String type5,
                              @RequestParam(name = "type6",required = false) String type6){

        User user=userExtMapper.selectUserInfoByUsername(username);

        Integer sort = null;
        switch (user.getUsersort()){
            case "理科":
                sort=1;
                break;
            case "文科":
                sort=2;
                break;
        }
        String name=user.getUserrealname();
        String gender = user.getUsergender();
        String phone = user.getUserphone();
        String wechat = user.getUserwechat();
        String area = user.getUserarea();
        int score = user.getUserscore();
        int rank = user.getUserrank();

        List<GaoKao> schoollist=gaoKaoExtMapper.selectChongBySelect(score,area,sort,province1,province2,province3,type1,type2,type3,type4,type5,type6);
        List<GaoKao> schoollist2=gaoKaoExtMapper.selectWenBySelect(score,area,sort,province1,province2,province3,type1,type2,type3,type4,type5,type6);
        List<GaoKao> schoollist3=gaoKaoExtMapper.selectBaoBySelect(score,area,sort,province1,province2,province3,type1,type2,type3,type4,type5,type6);



        Map<String,Object> params = new HashMap<>();

        params.put("userName",name);
        params.put("userGender",gender);
        params.put("userNumber",phone);
        params.put("userWechat",wechat);
        params.put("userSort",sort);
        params.put("userArea",area);
        params.put("userScore",score);
        params.put("userRank",rank);


        String[] array1 = {"chongschool1","chongschool2","chongschool3","chongschool4","chongschool5","chongschool6"};
        if (schoollist.size()<=6){
            for (int i=0;i<schoollist.size();i++){
                array1[i]=schoollist.get(i).getName();
                params.put("chongschool"+String.valueOf(i+1),array1[i]);
            }
        }else {
            for (int i = 0; i < 6; i++) {
                array1[i] = schoollist.get(i).getName();
                params.put("chongschool" + String.valueOf(i + 1), array1[i]);
            }
        }
        String[] array2 = {"wenschool1","wenschool2","wenschool3","wenschool4","wenschool5","wenschool6"};
        if (schoollist2.size()<=6){
            for (int i=0;i<schoollist2.size();i++){
                array2[i]=schoollist2.get(i).getName();
                params.put("wenschool"+String.valueOf(i+1),array2[i]);
            }
        }else{
            for (int i=0;i<6;i++){
                array2[i]=schoollist2.get(i).getName();
                params.put("wenschool"+String.valueOf(i+1),array2[i]);
            }
        }
        String[] array3 = {"baoschool1","baoschool2","baoschool3","baoschool4","baoschool5","baoschool6"};
        if (schoollist3.size()<=6){
            for (int i=0;i<schoollist3.size();i++){
                array3[i]=schoollist3.get(i).getName();
                params.put("baoschool"+String.valueOf(i+1),array3[i]);
            }
        }else{
            for (int i=0;i<6;i++){
                array3[i]=schoollist3.get(i).getName();
                params.put("baoschool"+String.valueOf(i+1),array3[i]);
            }
        }

        List<GaoKao> schoolProfessionList1 = gaoKaoExtMapper.selectChongSchoolProfession(score,sort,array1[0],area);
        List<GaoKao> schoolProfessionList2 = gaoKaoExtMapper.selectChongSchoolProfession(score,sort,array1[1],area);

        List<GaoKao> schoolProfessionList3 = gaoKaoExtMapper.selectWenSchoolProfession(score,sort,array2[0],area);
        List<GaoKao> schoolProfessionList4 = gaoKaoExtMapper.selectWenSchoolProfession(score,sort,array2[1],area);
        List<GaoKao> schoolProfessionList5 = gaoKaoExtMapper.selectWenSchoolProfession(score,sort,array2[2],area);

        List<GaoKao> schoolProfessionList6 = gaoKaoExtMapper.selectBaoSchoolProfession(score,sort,array3[0],area);
        List<GaoKao> schoolProfessionList7 = gaoKaoExtMapper.selectBaoSchoolProfession(score,sort,array3[1],area);

        String[] profession1={"pro1_1","pro1_2","pro1_3"};
        for (int i=0;i<3;i++){
            profession1[i]=schoolProfessionList1.get(i).getSpname();
            params.put("pro1_"+String.valueOf(i+1),profession1[i]);
        }
        String[] profession2={"pro2_1","pro2_2","pro2_3","pro2_4","pro2_5","pro2_6"};
        for (int i=0;i<6;i++){
            profession2[i]=schoolProfessionList2.get(i).getSpname();
            params.put("pro2_"+String.valueOf(i+1),profession2[i]);
        }
        String[] profession3={"pro3_1","pro3_2","pro3_3","pro3_4","pro3_5","pro3_6"};
        for (int i=0;i<6;i++){
            profession3[i]=schoolProfessionList3.get(i).getSpname();
            params.put("pro3_"+String.valueOf(i+1),profession3[i]);
        }
        String[] profession4={"pro4_1","pro4_2","pro4_3","pro4_4","pro4_5","pro4_6"};
        for (int i=0;i<6;i++){
            profession4[i]=schoolProfessionList4.get(i).getSpname();
            params.put("pro4_"+String.valueOf(i+1),profession4[i]);
        }
        String[] profession5={"pro5_1","pro5_2","pro5_3","pro5_4","pro5_5","pro5_6"};
        for (int i=0;i<6;i++){
            profession5[i]=schoolProfessionList5.get(i).getSpname();
            params.put("pro5_"+String.valueOf(i+1),profession5[i]);
        }
        String[] profession6={"pro6_1","pro6_2","pro6_3","pro6_4","pro6_5","pro6_6"};
        for (int i=0;i<6;i++){
            profession6[i]=schoolProfessionList6.get(i).getSpname();
            params.put("pro6_"+String.valueOf(i+1),profession6[i]);
        }
        String[] profession7={"pro7_1","pro7_2","pro7_3","pro7_4","pro7_5","pro7_6"};
        for (int i=0;i<6;i++){
            profession7[i]=schoolProfessionList7.get(i).getSpname();
            params.put("pro7_"+String.valueOf(i+1),profession7[i]);
        }


        InputStream is = this.getClass().getResourceAsStream("/word/template.docx");
        ExportWordUtils.exportWord(is,"D:/test",name+"的高考志愿报告.docx",params,request,response);
    }
}

package com.example.demo.controller.Base;

import com.example.demo.controller.FormBean.Nodes;
import com.example.demo.dao.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping(value = {"/common/"})
public class CommonController extends BaseController {
    /**
     * 获取菜单json
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "getMenuTree.json")
    @ResponseBody
    public List<Nodes> getMenuTree(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(DR_USER);
        List<Nodes> nodesList = new ArrayList<>();
        Nodes export = new Nodes("XXXX", "/daily/save.htm");
        Nodes query = new Nodes("XXXX", "/daily/query.htm");
        Nodes manage = new Nodes("XXXX", "/manager/query.htm");
        Nodes faceRec = new Nodes("人脸识别", "/faceRec/query.htm");
        if (user.getJurisdiction() == '2') {
            nodesList.add(export);
        } else if (user.getJurisdiction() == '1') {
            nodesList.add(query);
            nodesList.add(manage);

        } else if(user.getJurisdiction() == '7') {
            nodesList.add(faceRec);
        }
        else{
            nodesList.add(export);
            nodesList.add(query);
            nodesList.add(manage);
        }
        return nodesList;
    }


    /**
     * 主页空白页
     *
     * @return
     */
    @RequestMapping(value = "main.htm")
    public String main() {
        return "main";
    }
}

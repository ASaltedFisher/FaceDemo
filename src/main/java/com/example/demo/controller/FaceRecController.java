package com.example.demo.controller;

import com.example.demo.controller.Base.BaseController;
import com.example.demo.controller.FormBean.Message;
import com.example.demo.dao.pojo.User;
import com.lzw.face.FaceHelper;
import com.seetaface2.model.RecognizeResult;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping(value = {"/faceRec/"})
public class FaceRecController extends BaseController {

    public static String filePath = "D:/project/FR/demo/src/main/resources/static/";//设置上传图片的存放地址

    /**
     * 页面跳转
     *
     * @return
     */
    @RequestMapping(value = {"/query.htm"})
    public String queryPage() {
        return "faceRec/list";
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(MultipartFile file, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(DR_USER);
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if (!".jpg".equals(suffixName)) {
            return new Message(Message.MessageType.error, "只能上传jpg文件");
        }
        //重新生成文件名
        fileName = user.getUid() + suffixName;
        //指定本地文件夹存储图片
        String filePath = "D:/project/FR/demo/src/main/resources/static/";
        try {
            //将图片保存到static文件夹里
            file.transferTo(new File(filePath + fileName));
            return new Message(Message.MessageType.notice, "上传成功！请点击【识别】");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(Message.MessageType.error, "fail to upload");
        }
    }

    /**
     * 进行识别
     * @param request
     * @return
     */
    @RequestMapping(value = "check.json", method = RequestMethod.POST)
    @ResponseBody
    public Object check(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(DR_USER);
        String fileName = user.getUid() + ".jpg";

        File pic = new File(filePath + fileName);
        if (!pic.exists()) {
            return new Message(Message.MessageType.error, "未上传图片");
        }
        try {
            long l = System.currentTimeMillis();
            RecognizeResult result = FaceHelper.search(FileUtils.readFileToByteArray(pic));
            pic.delete();//删除临时上传文件
            if (null == result) {
                return new Message(Message.MessageType.notice, "无匹配");
            }
            File resultFile = GlobalList.files.get(result.index);
            String name = resultFile.getName();
            return new Message(Message.MessageType.notice, "匹配成功！<br>索引号:" + result.index + "<br>文件名：" + StringUtils.substring(name, 0, name.length() - 4) +
                    " <br>相似度：" + result.similar * 100 + "% <br>识别耗时：" + (System.currentTimeMillis() - l) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(Message.MessageType.error, "error");
        }
    }


}

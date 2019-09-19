package com.example.demo.controller;

import com.lzw.face.FaceHelper;
import com.lzw.face.SeetafaceBuilder;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Order(value = 1)
public class InitRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SeetafaceBuilder.build();//系统启动时先调用初始化方法
        //等待初始化完成
        while (SeetafaceBuilder.getFaceDbStatus() == SeetafaceBuilder.FacedbStatus.LOADING || SeetafaceBuilder.getFaceDbStatus() == SeetafaceBuilder.FacedbStatus.READY) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            long l2 = System.currentTimeMillis();
            System.out.println("==========开始注册==========");
            int i = 0;
            for (File file : GlobalList.files) {
                //注册时返回的图片在人脸库中的下标请自己存储 搜索时返回下标及相似度
                int index = FaceHelper.register(FileUtils.readFileToByteArray(file));
                System.out.println(index);
                if (index < 0) {
                    GlobalList.ll.add(-1);
                } else {
                    GlobalList.ll.add(i);
                }
                i++;
            }
            for (int j = GlobalList.ll.size() - 1; j >= 0; j--) {
                if ((int) GlobalList.ll.get(j) < 0) {
                    GlobalList.files.remove(j);
                }
            }
            System.out.println("注册耗时：" + (System.currentTimeMillis() - l2) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

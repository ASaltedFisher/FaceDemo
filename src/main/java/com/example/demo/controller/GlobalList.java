package com.example.demo.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 存放识别失效的样本库序号
 */
public class GlobalList {
    public static List ll = new ArrayList();

    public static LinkedList<File> files = (LinkedList) FileUtils.listFiles(new File("D:\\faces\\pyGetFaces"), FileFilterUtils.suffixFileFilter("jpg"), DirectoryFileFilter.INSTANCE);

}

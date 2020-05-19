package com.example.demoUtil;

import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2018/9/3.
 */
public class TestUtil {
    // 把排序后的文件写入txt中
    public static void write(String name,List<String> text) {
        try {
            // 准备文件666.txt其中的内容是空的
            File f1 = new File("E:/"+name);
            if (f1.exists() == false) {
                f1.getParentFile().mkdirs();
            } else {
                f1.delete();
            }
            // 创建基于文件的输出流
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f1),"UTF-8");
            BufferedWriter writer=new BufferedWriter(write);
            // 把数据写入到输出流
            for(int i=0; i < text.size(); i++) {
                writer.write(text.get(i));
            }
            writer.close();
//            System.out.println(name + "输入完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

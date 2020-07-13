package com.example.demo.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 * <Description> <br>
 * 测试流
 * 
 * @author chen.haibo<br>
 * @version 1.0<br>
 * @CreateDate 2020年7月4日 <br>
 * @see com.example.demo.stream <br>
 */
public class StreamTest {

    private final static String source = "C:\\Users\\13994\\Desktop\\test\\HbzzStateCheckServiceImpl.java";

    private final static String target = "C:\\Users\\13994\\Desktop\\test\\target.java";

    public static void test1() throws IOException {

        InputStream is = new FileInputStream(new File(source));

        byte[] b = new byte[1024];
        int len = 0;
        while ((len = is.read(b)) != -1) {
            System.out.println(new String(b, 0, len));
        }
        is.close();

    }

    public static void test2() throws IOException {

        InputStream is = new FileInputStream(new File(source));

        File t = new File(target);
        if (!t.exists()) {
            t.createNewFile();
        }
        OutputStream os = new FileOutputStream(target);

        byte[] b = new byte[1024];
        int len = 0;
        while ((len = is.read(b)) != -1) {
            os.write(b, 0, len);
            System.out.println(b.length);
            System.out.println(len);
            System.out.println(new String(b));
            System.out.println("--------------------- 我是分割线 ---------------------");
            System.out.println(new String(b, 0, len));
        }
        os.close();
        is.close();

    }

    public static void test3() throws IOException {
        Reader reader = new FileReader(source);
        char[] c = new char[1024];
        int len = 0;
        while ((len = reader.read(c)) != -1) {
            System.out.println(new String(c, 0, len));
        }
        reader.close();
    }

    public static void test4() throws IOException {
        Reader reader = new FileReader(source);
        Writer writer = new FileWriter(target);
        char[] c = new char[1024];
        int len = 0;
        while ((len = reader.read(c)) != -1) {
            writer.write(c, 0, len);
        }
        writer.close();
        reader.close();
    }

    public static void test5() throws IOException {
        Reader reader = new FileReader(source);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }

    public static void test5_1() throws IOException {
        Reader reader = new FileReader(source);
        BufferedReader bufferedReader = new BufferedReader(reader);
        char[] c = new char[1024];
        int len = 0;
        while ((len = bufferedReader.read(c)) != -1) {
            System.out.println(new String(c, 0, len));
        }
        bufferedReader.close();
    }

    public static void test6() throws IOException {
        Reader reader = new FileReader(source);
        BufferedReader bufferedReader = new BufferedReader(reader);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(target));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line + "\n");
        }
        bufferedReader.close();
        bufferedWriter.close();
    }

    public static void test7() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(source));
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(target));

        char[] c = new char[1024];
        int len = 0;
        while ((len = isr.read(c)) != -1) {
            osw.write(c, 0, len);
        }
        osw.close();
        isr.close();
    }

    public static void test8() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(source));
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(target));

        char[] c = new char[1024];
        int len = 0;

        while ((len = isr.read(c, 0, 1024)) != -1) {
            osw.write(c, 0, len);
        }
        osw.close();
        isr.close();
    }

    // 字节流实现文件拷贝
    public static String copyFile(String src, String dest) throws IOException {
        File srcFile = new File(src);// 源文件数据源
        File desFile = new File(dest);// 写入到目标数据源
        InputStream is = null;
        OutputStream os = null;
        byte[] buf = new byte[1024];// 缓存区
        int len = 0;// 读取长度
        try {
            is = new BufferedInputStream(new FileInputStream(srcFile));// 读取数据源
            os = new BufferedOutputStream(new FileOutputStream(desFile));// 写入到数据源
            while ((len = is.read(buf)) != -1) { // 读取长度不为-1，继续读取
                os.write(buf); // 读取内容之后马上写入目标数据源
            }
            os.flush();// 输出
            return "文件拷贝成功！查看拷贝文件路径：" + desFile.getPath();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (is != null)
                is.close();
            if (os != null)
                os.close();
        }
        return "文件拷贝失败";
    }

    public static void testN() throws IOException {
        InputStream is = new FileInputStream(source);
        OutputStream os = new FileOutputStream(target);
        IOUtils.copy(is, os);
    }

    public static void main(String[] args) throws IOException {
        // StreamTest.test1();
        // StreamTest.test2();
        // StreamTest.test3();
        // StreamTest.test4();
        // StreamTest.test5();
        // StreamTest.test6();
        // StreamTest.test7();
        // StreamTest.test8();

        StreamTest.copyFile(source, target);
        // StreamTest.testN();

        String a = "memcached";
        System.out.println(a.getBytes().length);
        String b = "测试测试测试";
        System.out.println(b.getBytes().length);

    }

}

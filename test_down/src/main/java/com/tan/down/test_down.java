package com.tan.down;

//import com.sun.org.apache.xpath.internal.operations.String;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javafx.scene.image.PixelFormat;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Random;

import static java.awt.Font.BOLD;


public class test_down extends HttpServlet {
    //生成随机验证码
    private static String makeRandomString() {
        Random random = new Random(1);
        String codeNum = random.nextDouble() * 10000000 + "";
        return (String) codeNum.substring(0, 6);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //使用HttpServlet实现文件下载
        System.out.println("文件开始下载");
        //OutPutStreamDOwnload(resp);
        OutputPicture(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO
    }

    //下载文件的具体实现
    public void OutPutStreamDOwnload(HttpServletResponse rep) throws IOException {
        //获取下载文件的实际路径
//        String realPath = this.getServletContext().getR   ealPath("C:\\Users\\tanzi\\Documents\\算法\\PythonCrawler-master\\README.md");
        String realPath = this.getServletContext().getRealPath("/WEB-INF/README.md");
        //截取想下载的文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //设置响应头，让浏览器能够响应下载文件
        rep.setHeader("Content-Disposition", "attachment;filename" + fileName);
        System.out.println(realPath + "--" + fileName);
        //获取文件输入流
        try {
            FileInputStream in = new FileInputStream(realPath);
            int len = 0;
            //创建缓冲区
            byte[] buffer = new byte[1024];
            //获取输入流
            ServletOutputStream out = rep.getOutputStream();
            //写入缓冲区
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            //收尾工作
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("文件不存在");
            rep.getOutputStream().print("文件不存在");
        }
    }

    //验证码实现
    public void OutputPicture(HttpServletResponse resp) throws IOException {
        resp.setContentType("image/ipg");
        //去除缓存
        resp.setDateHeader("expires", -1);
        resp.setHeader("content-type", "image/jpeg");
        resp.setHeader("Cache-Control", "none");
        resp.setHeader("Pragma", "none");
        //回复图片
        ImageIO.write(drawPic(), "jpg", resp.getOutputStream());
    }

    //画随机验证码
    private BufferedImage drawPic() {
        //创建图片
        BufferedImage image = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
        //创建画笔
        Graphics2D pen = (Graphics2D) image.getGraphics();
        //画底色
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, 1920, 1080);
        //画验证码
        pen.setColor(Color.black);
        pen.setFont(new Font(null, BOLD, 500));
        pen.drawString(makeRandomString(), 0, 500);
        return image;
    }

//    public static void main(String[] args) {
//        System.out.println(makeRandomString());
//    }
}


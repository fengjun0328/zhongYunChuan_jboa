package cn.jboa.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;

import cn.jboa.util.RandomNumUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 该类主要用于生成验证码
 * 
 */
@RestController
public class RandomController {

    private static final long serialVersionUID = 1L;



    // 生成验证码方法
    @RequestMapping(value = "/random.do")
    public void random(HttpServletResponse response, HttpSession session) throws Exception {
        RandomNumUtil rdnu = RandomNumUtil.Instance();
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        BufferedImage image = ImageIO.read(rdnu.getImage());
        session.setAttribute("msg",rdnu.getString());
        ImageIO.write(image, "png", os);

    }

}

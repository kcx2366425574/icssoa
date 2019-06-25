package com.icss.oa.t;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {

	
	/*
    1.生成验证码
    2.把验证码上的文本存在session中
    3.把验证码图片发送给客户端
    */
	@RequestMapping("/getVerifiCode")
    @ResponseBody
    public void getVerifiCode(HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		//用我们的验证码类，生成验证码类对象
        ImageVerificationCode ivc = new ImageVerificationCode();   
        //获取验证码
        BufferedImage image = ivc.getImage();  
        
        HttpSession session = request.getSession();
        
        //将验证码的文本存在session中
        session.setAttribute("text", ivc.getText());        

        //将验证码图片响应给客户端
        ivc.output(image, response.getOutputStream());
    }
	
	
	@RequestMapping("/auth")
    @ResponseBody
    public String authentication(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("utf-8");
        
        //从session中获取真正的验证码
        String session_vcode=(String)session.getAttribute("text");  
        return session_vcode;
    }
}

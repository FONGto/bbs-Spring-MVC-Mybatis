package com.fong2.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fong2.dao.EmailCodeDao;
import com.fong2.entity.EmailCode;
import com.fong2.service.SendMailService;
import com.sun.mail.util.MailSSLSocketFactory;
@Service("sendMailService")
public class SendMailServiceImpl implements SendMailService {
	@Resource
	EmailCodeDao emailCodeDao;
	@Value("${mail.account}")
	private String account;		//登录用户名
	@Value("${mail.pass}")
	private String pass;        //登录密码
	@Value("${mail.from}")
	private String from;        //发件地址
	@Value("${mail.host}")
	private String host;        //服务器地址
	@Value("${mail.port}")
	private String port;        //端口
	@Value("${mail.protocol}")
	private String protocol; 	//协议
	@Value("${mail.fromName}")
	private String fromName; 	//发件人标识
	
	static class MyAuthenricator extends Authenticator{  
        String u = null;  
        String p = null;  
        public MyAuthenricator(String u,String p){  
            this.u=u;  
            this.p=p;  
        }  
        @Override  
        protected PasswordAuthentication getPasswordAuthentication() {  
            return new PasswordAuthentication(u,p);  
        }  
    }
    	
	/**
	 * @param to收件人地址，subject主题，text内容
	 */
	public void send(String to, String subject,String text) {
		try {
			subject=MimeUtility.encodeWord(subject, "UTF-8", "Q");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Properties prop = new Properties();
        //协议
        prop.setProperty("mail.transport.protocol", protocol);
        //服务器
        prop.setProperty("mail.smtp.host", host);
        //端口
        prop.setProperty("mail.smtp.port", port);
        //使用smtp身份验证
        prop.setProperty("mail.smtp.auth", "true");
        //使用SSL，企业邮箱必需！开启安全协议
        MailSSLSocketFactory sf = null;
        
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        //
        Session session = Session.getDefaultInstance(prop, new MyAuthenricator(account, pass));
        session.setDebug(false);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(from,fromName));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setSentDate(new Date());
            //mimeMessage.setText(text);
            mimeMessage.setContent(text, "text/html;charset=utf-8");
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    

	}


	public void forgetPwd(String email) {
		//Map<String, Object>	map=new HashMap<String, Object>();
		int num=(int) (Math.random()*9000+1000);
		String text="<meta charset=\"utf8\"><h1>你的验证码是: "+num+"</h1><h3>本次操作有效</h3>";
		send(email,"GOALFORUM取回密码",text);
		EmailCode ec=new EmailCode(email, num+"");
		emailCodeDao.addCode(ec);
		
	}

}

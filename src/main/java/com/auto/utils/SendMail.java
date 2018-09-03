package com.auto.utils;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件
 * 
 * @author lijialong
 */
public class SendMail {
	/**
	 * 
	 * @param userName
	 *            发送邮箱账号
	 * @param passWord
	 *            发送邮件密码
	 * @param smtpHost
	 *            stmp服务器地址
	 * @param smtpPort
	 *            smtp服务器端口
	 * @param from
	 *            发件人地址,注意和userName保持对应关系
	 * @param tos
	 *            收件人地址
	 * @param title
	 *            标题
	 * @param content
	 *            邮件正文
	 */
	public void sendmessage(String userName, String passWord, String smtpHost, String smtpPort, String from, String tos,
			String title, String content) {
		Properties smtpProper = setSmtp(smtpHost, smtpPort, userName);
		Auth authenticator = new Auth(userName, passWord);
		try {

			// 创建session实例
			Session session = Session.getInstance(smtpProper, authenticator);
			MimeMessage message = new MimeMessage(session);
			session.setDebug(true);

			// 设置from发件人邮箱地址
			message.setFrom(new InternetAddress(from));
			// 收件人to LIST
			ArrayList<Address> toList = new ArrayList<Address>();
			// 收件人字符串通过 , 号分隔收件人
			String[] toArray = tos.split(",");
			for (int i = 0; i < toArray.length; i++) {
				String str = toArray[i];
				toList.add(new InternetAddress(str));
			}
			// 将收件人列表转换为收件人数组
			Address[] addresses = new Address[toList.size()];
			addresses = toList.toArray(addresses);
			// 设置to收件人地址
			message.setRecipients(MimeMessage.RecipientType.TO, addresses);
			// 设置邮件标题
			message.setSubject(title);
			// 设置邮件内容
			message.setContent(content, "text/html;charset=UTF-8");
			// Transport.send(message);
			Transport transport = session.getTransport();
			transport.connect(smtpHost, userName, passWord);
			transport.sendMessage(message, addresses);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * 邮件基本配置
	 * 
	 * @param smtpHost
	 *            smtp服务器
	 * @param smtpPort
	 *            smtp端口号
	 * @param userName
	 *            用户名
	 * @author lijialong
	 */
	private Properties setSmtp(String smtpHost, String smtpPort, String userName) {
		// 创建邮件配置文件
		Properties maiProperties = new Properties();
		// 配置smtp发件服务器
		maiProperties.put("mail.transport.protocol", "smtp");
		maiProperties.put("mail.smtp.host", smtpHost);
		maiProperties.put("mail.smtp.port", smtpPort);
		// 配置smtp用户名
		maiProperties.put("mail.user", userName);
		// 配置smtp身份验证
		maiProperties.put("mail.smtp.auth", "true");
		return maiProperties;

	}

//	public static void main(String[] args) {
//		SendMail sendMail = new SendMail();
//		sendMail.sendmessage("etyero", "WOSHI93917", "smtp.163.com", "25", "etyero@163.com",
//				"929152416@qq.com", "你好", "你在家吗？");
//
//	}

}

/**
 * smtp 身份验证
 * 
 * @author lijialong
 */
class Auth extends Authenticator {
	Properties pwdProperties;

	public Auth(String userName, String passWord) {
		pwdProperties = new Properties();
		try {
			pwdProperties.put(userName, passWord);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 必须实现 PasswordAuthentication 方法
	 * 
	 * @return PasswordAuthentication Password验证信息
	 */
	public PasswordAuthentication passwordAuthentication() {
		String userName = getDefaultUserName();
		// 当前用户在密码表里
		if (pwdProperties.containsKey(userName)) {
			// 取出密码，封装好后返回
			return new PasswordAuthentication(userName, pwdProperties.getProperty(userName).toString());

		} else {
			// 如果当前用户不在密码表里就返回Null
			return null;

		}

	}

}

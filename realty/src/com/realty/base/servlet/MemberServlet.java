package com.realty.base.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Date;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.apache.naming.java.javaURLContextFactory;
import com.realty.base.action.MemberAction;
import com.realty.base.dao.MemberDao;
import com.realty.base.dao.impl.MemberDaoImpl;
import com.realty.base.model.Member;
import com.realty.base.model.MyAuthenticator;
import com.realty.base.tools.MD5Util;

/**
 * Servlet implementation class MemberAdd
 */
@WebServlet("/MemberAdd")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		String membername=request.getParameter("membername");
		System.out.print(membername);
		String memberpwd=request.getParameter("memberpwd");
		System.out.println(memberpwd);
		String email=request.getParameter("email");
		String question=request.getParameter("question");
		String answer = request.getParameter("answer");
		String registerId=MD5Util.encode2hex(email);
		
		MemberAction memberAction=new MemberAction();
		
		if(flag.equals("2"))//�ж��Ƿ��������һ�����
		{
			if(memberAction.find(email).get(0).getName().equals(membername))
			{
				System.out.print(membername);
				String userName = "cn147159@163.com";    
		        String password = "136063722380"; 
		        
		        String membernameq=memberAction.find(email).get(0).getName();
		        String memberpwdq=memberAction.find(email).get(0).getPassword();
		       
		            
		        Properties props = new Properties();    
		        props.setProperty("mail.smtp.host", "smtp.163.com");    
		        props.setProperty("mail.smtp.auth", "true"); 
		        
		        Authenticator authenticator = new MyAuthenticator(userName, password);    
	            
		        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props,authenticator);    
		        session.setDebug(true);
		        
		        try{    
		            Address from = new InternetAddress(userName);    
		            Address to = new InternetAddress(email);    
		                
		            MimeMessage msg = new MimeMessage(session);    
		            msg.setFrom(from);    
		            msg.setSubject("����");    
		            msg.setSentDate(new Date());    
		            msg.setContent("�𾴵�" + membernameq + ":����������" + memberpwdq + "�����¼�󾡿��޸�����лл", "text/html;charset=utf-8");    
		            msg.setRecipient(RecipientType.TO, to);    
		              
		           /* Transport transport = session.getTransport("smtp");  
		            transport.connect("smtp.163.com", userName, password);  
		            transport.sendMessage(msg,msg.getAllRecipients());  
		            transport.close();  */
		              
		            Transport.send(msg);    
		        } catch(MessagingException e){    
		            e.printStackTrace();    
		        }    
		         
				request.getRequestDispatcher("./website/login/sendMailSuccess.jsp").forward(request,response);
			}else {
				System.out.print("�û��������䲻һ��");
				request.getRequestDispatcher("./website/login/.jsp").forward(request,response);
			}
			
			
		}else{
				/*HttpSession session=request.getSession();
		session.setAttribute("membername", membername);
		session.setAttribute("memberpwd", memberpwd);*/
		
		 
		/*for(int i=0;i<memberAction.memberList().size();i++){
			 if(memberAction.memberList().get(i).getName().equals(membername)){
				 //String manrole=memberAction.memberList().get(i).getManrole();
				 //session.setAttribute("manrole", manrole);
			 }
		 }*/
			if(flag.equals("1")&&memberAction.find(email).isEmpty()){
		
		if(memberAction.findn(membername).isEmpty()){
		if(memberAction.memberAdd(membername, memberpwd,email,question,answer,registerId))
			
		 {	
	        String userName = "cn147159@163.com";    
	        String password = "136063722380"; 
	        
	        String url = "http://localhost:8088/realty/MailBackServlet?registerId=" + registerId + "&email="+email;//�����û����������е��������ӻص������վ��    
            System.out.print(url);
	        HttpSession httpSession = request.getSession();    
	       // httpSession.setAttribute(registerId, membername);    
	        httpSession.setMaxInactiveInterval(600);    
	            
	        Properties props = new Properties();    
	        props.setProperty("mail.smtp.host", "smtp.163.com");    
	        props.setProperty("mail.smtp.auth", "true"); 
	        
	        Authenticator authenticator = new MyAuthenticator(userName, password);    
            
	        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props,authenticator);    
	        session.setDebug(true);
	        
	        try{    
	            Address from = new InternetAddress(userName);    
	            Address to = new InternetAddress(email);    
	                
	            MimeMessage msg = new MimeMessage(session);    
	            msg.setFrom(from);    
	            msg.setSubject("����");    
	            msg.setSentDate(new Date());    
	            msg.setContent("<a href='" + url + "'>���" + url + "���ע��</a>", "text/html;charset=utf-8");    
	            msg.setRecipient(RecipientType.TO, to);    
	              
	           /* Transport transport = session.getTransport("smtp");  
	            transport.connect("smtp.163.com", userName, password);  
	            transport.sendMessage(msg,msg.getAllRecipients());  
	            transport.close();  */
	              
	            Transport.send(msg);    
	        } catch(MessagingException e){    
	            e.printStackTrace();    
	        }    
	         
			request.getRequestDispatcher("./website/login/sendMailSuccess.jsp").forward(request,response);
			
		 }
		
		 else
		 { 
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "ע��ʧ��", "ע����ʾ", JOptionPane.ERROR_MESSAGE);
			// response.sendRedirect("./manage/login.jsp");
			 request.getRequestDispatcher("./website/register.jsp").forward(request,response);
		 }
	}else{
		System.out.print("�û����Ѵ���");
	}
	}
	else{
		System.out.print("�����Ѿ�����");
	}
	if(flag=="3"){
		
		}
	}
	}

}

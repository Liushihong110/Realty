package com.realty.base.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.service.spi.ServiceException;

import com.realty.base.dao.MemberDao;
import com.realty.base.dao.impl.MemberDaoImpl;
import com.realty.base.model.Member;



/**
 * Servlet implementation class MailBackServlet
 */
@WebServlet("/MailBackServlet")
public class MailBackServlet extends HttpServlet {    
    private static final long serialVersionUID = 1L;    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {    
            doPost(request, response);
    }    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {  
    	String registerID = request.getParameter("registerId"); 
    	String email=request.getParameter("email");
    	System.out.print(registerID);
    	System.out.print(email);
        if(registerID == null){ 
        	       
            request.getRequestDispatcher("./website/home.jsp").forward(request,response);    
            return ;    
        }  
        
        MemberDao mm =new MemberDaoImpl();
        List<Member> list=mm.find(email);
        
 
        if(list!=null) {  
            //��֤�û�����״̬  
            if(list.get(0).getMemrole()==0) { 
                ///û����
               // Date currentTime = new Date();//��ȡ��ǰʱ��  
                //��֤�����Ƿ���� 
                //currentTime.before(list.get(0).getCreateTime());
               // if(currentTime.before(user.getLastActivateTime())) {  
                    //��֤�������Ƿ���ȷ  
                    if(registerID.equals(list.get(0).getRegisterId())) {  
                        //����ɹ��� //�������û��ļ���״̬��Ϊ�Ѽ��� 
                        System.out.println("==sq==="+list.get(0).getMemrole());
                        list.get(0).setMemrole(1);;//��״̬��Ϊ����
                        mm.update(email);
                        System.out.println("==sh==="+list.get(0).getMemrole());
                        
                        //mm.update(email,list.get(0).getMemberId());
                        request.getRequestDispatcher("./website/login/registerSuccess.jsp").forward(request,response);
                       
                        
                    } else {  
                       throw new ServiceException("�����벻��ȷ");  
                    }  
               // } else { throw new ServiceException("�������ѹ��ڣ�");  
               // }  
            } else {
               throw new ServiceException("�����Ѽ�����¼��");  
            }  
        } else {
            throw new ServiceException("������δע�ᣨ�����ַ�����ڣ���");  
        }  
        
      //  request.getRequestDispatcher("/registSuccess.jsp").forward(request,response);
           
    }    
    
}    

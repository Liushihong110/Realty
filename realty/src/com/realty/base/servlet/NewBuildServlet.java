package com.realty.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.realty.base.action.BuildingAction;
import com.realty.base.model.Building;
import com.realty.base.model.BuildingPhoto;

/**
 * Servlet implementation class NewBuildServlet
 */
@WebServlet("/NewBuildServlet")
public class NewBuildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewBuildServlet() {
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
		 response.setHeader("Cache-Control", "no-store");  
		 response.setHeader("Pragma", "no-cache");  
		 response.setDateHeader("Expires", 0); 
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		/*String bname=request.getParameter("bname");
		System.out.println(bname);*/
		String flag=request.getParameter("flag");
		String bname= request.getParameter("bname"); 
		
		if(flag.equals("3")){
			//String[] searchchar1 = request.getParameterValues("searchChar");  
			String searchcharg = request.getParameterValues("searchChar")[0].trim();
			 String[] searchchars = searchcharg.split(",");
			  int[] searchchar = { 0, 0, 0, 0};
			  //String to int
			  for (int i = 0; i < searchchars.length; i++) {
				  searchchar[i] = Integer.parseInt(searchchars[i]);
			    }
			  BuildingAction buildingaction=new BuildingAction();
			  int count=buildingaction.buildingCount(searchchar[0], searchchar[1], searchchar[2],bname);
			  int pageSize=10;
			  int index=0;
			  if(count%pageSize==0&&count!=0)
			   index=count/pageSize;
			  else {
			  	index=count/pageSize+1;
			  	}
			  String pageNos=request.getParameter("pageNo");	
			  if(pageNos==null){
			  	pageNos="1";
			  }
			  int pageNo=Integer.parseInt(pageNos);
			  if(pageNo<1){
			  	pageNo=1;
			  	}else if(pageNo>index){
			  		pageNo=index;
			  	}
			  int pageNo1=pageNo-1;
			  int pageNo2=pageNo+1;
			
				List<Building> result=buildingaction.buildingSearch(searchchar[0], searchchar[1], searchchar[2],bname,pageNo,pageSize);
		      	if(result.size()>0){
		      		out.println("<table width='799px'>");
		      		for(int i=0;i<result.size();i++){
		      		 List BuildingPhotoList=buildingaction.buildingPhoto(result.get(i).getBuildingId(),"building_photo");
      				 if(!BuildingPhotoList.isEmpty()){
		      	out.println("<tr height='150'>"
		      				+"<td width='150'><img style='width:150px;height:170px;' alt='' src='picupload/building/"+BuildingPhotoList.get(0)+"'></td>"
		      			   + "<td><a href='website/building/buildingintro.jsp?buildId="+result.get(i).getBuildingId()+"' <span>"+result.get(i).getBuildingName()+"</span> </a> "
		      			   + "<span>"+result.get(i).getRegionId()+"</span>"
		      			   + "<span>"+result.get(i).getUsageId()+"</span>"
		      			   + "<td><span>"+result.get(i).getAveragePrice()+"</span></td>"
		      			   
		      			   +"</tr>");
		      	}else{
		      		out.println("<tr height='150'>"
		      				+"<td width='150'>��ͼƬ</td>"
		      			   + "<td><a href='website/building/buildingintro.jsp?buildId="+result.get(i).getBuildingId()+"' <span>"+result.get(i).getBuildingName()+"</span> </a> "
		      			   + "<span>"+result.get(i).getRegionId()+"</span>"
		      			   + "<span>"+result.get(i).getUsageId()+"</span>"
		      			   + "<td><span>"+result.get(i).getAveragePrice()+"</span></td>"
		      			   +"</tr>");
		      	}
		      		}
		      	out.println("<tr style='border-left:0px;border-right:0px;border-down:0px;'>"
      			 		+" <td colspan='5' >"
      			 		+" <table style='border:0px;'  width='100%' class='right-font08'>");
       out.println("<tr>");
       out.println("<td style='border:0px;' width='50%'>�� <span class='right-text09'>"+index+"</span> ҳ | �� <span class='right-text09'>"+pageNo+"</span> ҳ</td>");
       out.println(" <td style='border:0px;' width='48%' align='right'>[<a href='website/building/serbuilding.jsp?pageNo=1&searchchar[0]="+searchchar[0]+"&searchchar[1]="+searchchar[1]+"&searchchar[2]="+searchchar[2]+"' class='right-font08'>��ҳ</a> | <a href='manage/data/serbuilding.jsp?pageNo="+pageNo1+"&searchchar[0]="+searchchar[0]+"&searchchar[1]="+searchchar[1]+"&searchchar[2]="+searchchar[2]+"' class='right-font08'>��һҳ</a> | <a href='website/building/serbuilding.jsp?pageNo="+pageNo2+"&searchchar[0]="+searchchar[0]+"&searchchar[1]="+searchchar[1]+"&searchchar[2]="+searchchar[2]+"' class='right-font08'>��һҳ</a> | <a href='website/building/serbuilding.jsp?pageNo="+index+"&searchchar[0]="+searchchar[0]+"&searchchar[1]="+searchchar[1]+"&searchchar[2]="+searchchar[2]+"' class='right-font08'>ĩҳ</a>] ת����</td>");
       out.println("<td style='border:0px;' width='2%'>"
      	                +"<table style='border:0px;' width='29'>"
      	                    +"<tr>"
      	                     +" <td style='border:0px;' width='1%'><input id='gopageNo' type='text' class='textfield' size='1' /></td>"
      	                      +"<td style='border:0px;' width='87%'><input name='pagego' type='button' class='right-button06' onclick='passpageNo()' />"
      	                     +" </td>"
      	                    +"</tr>"
      	               +" </table></td>"
      	             +" </tr>"
      	         +" </table></td>"
      	       +" </tr>");
		      	out.println("</table>");
		      				out.close();
		      	}
		      	}
		      	/*out.println("<table width='663' height='516'  align='center'>"
   +"<tr> <td width='289'>¥������<span>"+result.get(0).getBuildingName()+"</span></td>"
     +" <td width='358'>¥�̱�����<span><%=buildingList.get(j).getBuildingAlias()%></span></td> </tr>"
    +"<tr><td>��ҵ��������<span><%=buildingList.get(j).getRegionId()%></span></td><td>��ҵ��ַ�� </td></tr>"
    +" <tr><td>��ҵ���ƣ�</td><td>¥����;��</tr>"
    +" <tr><td>���������</td><td>�����̣�</td></tr>"
    +" <tr><td>ռ�������</td><td>���������</td> </tr>"
    +"<tr><td>�����ṹ��   </td><td>������̬��</td></tr>"
    +" <tr> <td colspan='3'>�ݻ��ʣ�</td></tr>"
    +" <tr><td colspan='2'>�̻��ʣ� ��λ��ȣ� ����װ�������</td></tr>"
    +" <tr><td>����ʱ�䣺</td><td>��ҵ����</td></tr>"
    +" <tr><td>���̼۸���ۣ���</td><td>���̼۸񣨾��ۣ���</td></tr>"
    +" <tr><td>���ݵ��ۣ�</td><td>�����ܼۣ�</td></tr>"
    +" <tr> <td valign='middle' colspan='2'>¥��λ��ͼ��<%=photolist.get(0)%> <img src='<%=photolist.get(0)%>'/>"
    		+ "<span name='locationphoto' id='locationphoto' ></span> </td></tr>"
    +" <tr><td>���ȣ�</td><td>γ�ȣ�</td></tr>"
    +" <tr><td colspan='2'>��Ϣ¼���ˣ�¼��ʱ�䣺</td></tr>"
    +" <tr><td colspan='2'>�ύ�ˣ� �ύʱ�䣺״̬��ǣ�</td></tr>"
    +" <tr> <td colspan='2'>��ע��</td></tr>"
    		+"</table>");*/
		      	else{
			      	out.println("<table width='742'>"
			      	+"<tr>"
					+" <td> <span>û����Ӧ��Ϣ </span></td>"
					+"</tr>"
					+"</table>");
					out.close();

		      	}
	
		}
	

	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("=================");
	}
}


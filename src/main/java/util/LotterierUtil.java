package main.java.util;

import javax.servlet.http.*;

import main.java.entity.Lotterier;
public class LotterierUtil {
	 public static Lotterier getUserSession(HttpServletRequest request){
		 Lotterier lotterier=getLotterier(request);
		 return lotterier;
	 }
	
	 public static boolean checkSession(HttpServletRequest request,HttpServletResponse response){
		 boolean isok=false;
		 Lotterier usv=getLotterier(request);
		 if(usv!=null)isok=true;
		 return isok;
	 }
	 //通过本系统获取usv
	 public static Lotterier  getLotterier(HttpServletRequest request){
		 HttpSession session = request.getSession();
		 Lotterier lotterier=(Lotterier)session.getAttribute("sys_lotterierSession");
		 return lotterier;
	 }
	
	 private static String getRemoteRealIp(HttpServletRequest request){
		 String realIp=request.getHeader("x-forwarded-for");
		 if("".equals(realIp)||"unknow".equals(realIp))realIp=request.getHeader("Proxy-Client-Ip");
		 if("".equals(realIp)||"unknow".equals(realIp))realIp=request.getHeader("WL-Proxy-Client-Ip");
		 if("".equals(realIp)||"unknow".equals(realIp))realIp=request.getRemoteAddr();
		 return realIp;
		 
		 
	 }

}

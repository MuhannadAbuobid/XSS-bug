// Under testing and development
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class User {
   private String userid;
   private String password;
   private boolean isAdmin;

   public String getUserid() {
    return name;
  }
  
  public void setUserid(String userid) {
    this.userid = userid;
  }
  
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getIsadmin() {
    return isadmin;
  }

  public void setIsAdmin(String isadmin) {
    this.isadmin = isadmin;
  }
}


public class InsecureHttpServlet extends HttpServlet 
{    
    private String mymsg;
    public void init() throws ServletException 
    {      
       DOMAIN = "https://www.linkedin.com";   
    }
    public void doGet(HttpServletRequest request, 
        HttpServletResponse response) throws ServletException, 
        IOException 
    {   
        String value1 = req.getParameter("value1");
        String value2 = req.getParameter("value2");
        String value3 = req.getParameter("value3");
        String referer = req.getHeader("referer");
        if(referer.startsWith(DOMAIN)){
            showPremiumContent();
        } else {
            showLoginPage();
        }
        response.setContentType("text/html");
        resp.addHeader("X-Header", value1);
        
        PrintWriter out = response.getWriter();      
        out.println("<h1>" + alert("XSS") + "</h1>");      //
        out.println("<a href=\"javascript:hello('${value3}')\">");   
    }
    
    public void doPost(HttpServletRequest req, 
        HttpServletResponse resp) throws ServletException, 
        IOException 
    { 
    
      Enumeration<String> parameterNames = req.getParameterNames();
      User user = new User();
      Class<User> clazz = User.class;
        while (parameterNames.hasMoreElements()) {
            String param = parameterNames.nextElement();
            String value = req.getParameter(param);
            Field fieldID = clazz.getField(param);
            fieldID.set(user, value);
        }
      createnewUser(user);
    }
    
    public void destroy() 
    { 
    
    }
}


https://www.linkedin.com/signup

<form action="/signup" method="POST">
     <input name="userid" type="text">
     <input name="password" type="text">
     <input type="submit">
</form>

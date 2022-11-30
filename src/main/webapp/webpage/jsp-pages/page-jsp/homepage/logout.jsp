<%@ page contentType="text/html" pageEncoding="UTF-8" language="Java" %>

<body>
<%
        session.removeAttribute("username");
        session.invalidate();
        out.println("<script language=javascript>alert('user logout');</script>");
        response.sendRedirect("../../../../index.jsp");
%>
</body>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

 ${username} ${userMessage} ${serviceUserId}
<c:forEach var="userRole" items="${userRoles}">
   User Roles <c:out value="${userRole.roleName}"/>
</c:forEach>
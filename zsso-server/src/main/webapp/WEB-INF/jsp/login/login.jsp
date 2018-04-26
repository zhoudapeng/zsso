<form action="/login" method="post">
    <input name="systemName" type="hidden" value="${systemName}"/>
    <input name="redirectUrl" type="hidden" value="${redirectUrl}"/>
    用户名：<input name="userName" ><br/>
    密码：<input name="password" type="password"/><br/>
    <input type="submit" value="提交">
</form>
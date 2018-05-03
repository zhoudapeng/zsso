<%@ page contentType="text/html;charset=utf-8"%>
<font color="red">${errorMsg}</font><br/>
<form action="/login" method="post">
    <input name="systemName" type="hidden" value="${systemName}"/>
    <input name="redirectUrl" type="hidden" value="${redirectUrl}"/>
    <table>
        <tr><td>用户名</td><td><input name="userName" ></td></tr>
        <tr><td>密码</td><td><input name="password" type="password"/></td></tr>
        <tr><td colspan=2><input type="submit" value="提交"/></td></tr>
    </table>
</form>
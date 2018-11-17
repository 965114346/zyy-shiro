package com.zyy.realm;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yangyang.zhang
 * @date 2018/11/17 0:49
 */
public class CustomRealm extends AuthorizingRealm {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = getPasswordByUsername(username);

        /*if (Objects.isNull(password)) {
            return null;
        }*/
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, "46c08c54f42af6eb68b94d0743e8242d", getName());
        info.setCredentialsSalt(ByteSource.Util.bytes("mark"));
        return info;

    }

    private String getPasswordByUsername(String username) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select password from sys_user where username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

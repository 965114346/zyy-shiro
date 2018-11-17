package com.zyy.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author yangyang.zhang
 * @date 2018/11/17 0:08
 */
public class JdbcRealmTest {

    @Test
    public void testJdbcRealm() {
        DefaultSecurityManager manager = new DefaultSecurityManager();

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://111.230.48.104:33306/website");
        dataSource.setUsername("root");
        dataSource.setPassword("232511");

        JdbcRealm realm = new JdbcRealm();
        realm.setDataSource(dataSource);
        realm.setPermissionsLookupEnabled(false);
        String sql = "select password from sys_user where username = ?";
        realm.setAuthenticationQuery(sql);

        SecurityUtils.setSecurityManager(manager);
        manager.setRealm(realm);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin", "de41b7fb99201d8334c23c014db35ecd92df81bc");
        subject.login(token);
        System.out.println(subject.isAuthenticated());

        subject.logout();
    }
}

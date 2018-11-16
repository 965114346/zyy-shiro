package com.zyy.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Create By yangyang.zhang on  16:32
 **/
public class AuthenticationTest {

    private SimpleAccountRealm realm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        realm.addAccount("zyy", "123", "admin");
    }

    @Test
    public void testAuthentication() {
        DefaultSecurityManager manager = new DefaultSecurityManager();
        manager.setRealm(realm);
        SecurityUtils.setSecurityManager(manager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zyy", "123");

        subject.login(token);

        subject.checkRole("admin");
        System.out.println(subject.isAuthenticated());

        subject.logout();

        System.out.println(subject.isAuthenticated());
    }
}

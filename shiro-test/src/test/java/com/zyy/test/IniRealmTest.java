package com.zyy.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Create By yangyang.zhang on 2018年11月16日17:06:11
 **/
public class IniRealmTest {

    @Test
    public void testIniRealm() {
        DefaultSecurityManager manager = new DefaultSecurityManager();

        IniRealm realm = new IniRealm("classpath:realm.ini");
        manager.setRealm(realm);
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zyy", "123");
        subject.login(token);
        subject.checkRole("admin");
        subject.checkPermission("user:delete");
        System.out.println(subject.isAuthenticated());
        subject.logout();
    }
}

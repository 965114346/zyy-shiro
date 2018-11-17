package com.zyy.test;

import com.zyy.shiro.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author yangyang.zhang
 * @date 2018/11/17 1:13
 */
public class CustomRealmTest {

    @Test
    public void testCustomRealm() {

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);

        CustomRealm realm = new CustomRealm();
        realm.setCredentialsMatcher(matcher);

        DefaultSecurityManager manager = new DefaultSecurityManager();
        manager.setRealm(realm);

        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin", "1");
        subject.login(token);
        System.out.println(subject.isAuthenticated());

        subject.logout();
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("1", "mark");
        System.out.println(md5Hash.toString());


        SimpleHash hash = new SimpleHash("SHA-1", "admin", "1");
        UsernamePasswordToken token = new UsernamePasswordToken("admin", hash.toString());
        System.out.println(new String((char[])token.getCredentials()));
    }
}

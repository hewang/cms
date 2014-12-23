package com.qatang.cms;

import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.service.resource.ResourceService;
import com.qatang.cms.service.role.RoleService;
import com.qatang.cms.service.user.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chirowong on 2014/9/3.
 */
public class CmsAuthorizingRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token){
        CmsAuthenticationToken cmsAuthenticationToken = (CmsAuthenticationToken)token;
        String username = cmsAuthenticationToken.getUsername();
        String password = new String(cmsAuthenticationToken.getPassword());
        String captcha = cmsAuthenticationToken.getCaptcha();

        if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
            User user = userService.getByUsername(username);
            if(user == null){
                throw new UnknownAccountException();
            }
            if(user.getValid().getValue() != EnableDisableStatus.ENABLE.getValue()){
                throw new DisabledAccountException();
            }
            if(!DigestUtils.md5Hex(password).equals(user.getPassword())){
                throw new IncorrectCredentialsException();
            }
            if(user.getLoginTime() != null){
                user.setLastLoginTime(user.getLoginTime());
            }
            user.setLoginTime(new Date());
            userService.update(user);
            return new SimpleAuthenticationInfo(user,password,user.getName());
        }
        throw new UnknownAccountException();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User)principals.getPrimaryPrincipal();
        List<Role> roles = userService.getByUserId(user.getId());
        Set<String> stringRoles = new HashSet<>();
        Set<String> stringPermissions = new HashSet<>();
        for(Role role : roles){
<<<<<<< HEAD
            stringRoles.add(role.getName());
            List<Menu> menus = roleService.getByRoleId(role.getId());
=======
            stringRoles.add(role.getRoleName());
            List<Resource> menus = roleService.getByRoleId(role.getId());
>>>>>>> 57389f169916442c332fb7f54a6e0c5c3e52b563
            /*for(Menu menu : menus){
                if(menu != null){
                    stringPermissions.add(menu.getAuthority());
                }
            }*/
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(stringRoles);
        authorizationInfo.setStringPermissions(stringPermissions);
        return authorizationInfo;
    }
}

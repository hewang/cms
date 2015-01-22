package com.qatang.cms.interceptor;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.entity.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by qatang on 14-6-27.
 */
public class ResourceSessionInterceptor extends HandlerInterceptorAdapter {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CacheManager cacheManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("开始获取session信息。");
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        Cache<Long, List<Resource>> rememberMeSessionCache = cacheManager.getCache("rememberMeSessionCache");
        List<Resource> list = rememberMeSessionCache.get(user.getId());
        request.setAttribute("resourceListForMenu", list);
        return true;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}

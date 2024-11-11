package com.revenatium.startalent_sb.config.tenant;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@Component
public class TenantInterceptor implements WebRequestInterceptor {
    @Override
    public void preHandle(WebRequest request) throws Exception {
        String account = request.getHeader("account");
        if (account == null) {
            account = "common";
        }
        TenantContext.setTenantId(account);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        // NOOP
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        // NOOP
    }
}

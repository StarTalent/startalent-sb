package com.revenatium.startalent_sb.config.tenant;

public class TenantContext {
    private static final InheritableThreadLocal<String> tenant = new InheritableThreadLocal<>();

    public static String getTenantId() {
        return tenant.get() != null ? tenant.get() : "test-tenant";
    }

    public static void setTenantId(String tenantId) {
        tenant.set(tenantId);
    }

    public static void clear() {
        tenant.remove();
    }
}

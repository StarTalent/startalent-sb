package com.revenatium.startalent_sb.config.tenant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.TenantId;

@MappedSuperclass
public abstract class TenantAbstractBaseEntity {
    @TenantId
    @Column(name = "tenant_id", nullable = false)
    private String tenantId;
}

package com.revenatium.startalent_sb.accounts;

import com.fasterxml.jackson.databind.JsonNode;
import com.revenatium.startalent_sb.users.User;
import com.revenatium.startalent_sb.utils.JsonNodeConverter;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String domain;

    @Convert(converter = JsonNodeConverter.class)
    @Column(columnDefinition = "json", name = "brandingconfig")
    private JsonNode brandingConfig;

    @Column(name = "isactive")
    private boolean isActive;

    @OneToMany(mappedBy = "account")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public JsonNode getBrandingConfig() {
        return brandingConfig;
    }

    public void setBrandingConfig(JsonNode brandingConfig) {
        this.brandingConfig = brandingConfig;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}


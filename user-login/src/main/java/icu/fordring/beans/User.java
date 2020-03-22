package icu.fordring.beans;

import java.util.*;


import javax.persistence.*;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Slf4j
@Data
@Entity
@Table
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long exp;

    @Column(length = 128,unique = true)
    private String username;

    @Column(length = 128,unique = true)
    private String password;

    @Column(length = 64)
    private String realName;

    @Column(length = 64)
    private String studentId;

    @Column(length = 64)
    private String email;

    @Column
    private Boolean enabled;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column
    private Byte[] head;

    @Column
    private String description;

    //急加载 会查询role表
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROlE",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("find authorities...");
        Set<GrantedAuthority> auths = new HashSet<>();
        Set<Role> roles = this.getRoles();
        for (Role role : roles) {
            for(Authority authority:role.getAuthorities()){
                auths.add(new SimpleGrantedAuthority(authority.getName()));
            }
        }
        return auths;
    }
    public Set<String> getRolesList(){
        Set<String> set = new HashSet<>();
        for(Role role:roles){
            set.add(role.getName());
        }
        return set;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(enabled==null)return false;
        return enabled;
    }
}
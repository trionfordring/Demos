package icu.fordring.beans;

import lombok.Data;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table
public class Authority{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自动生成
    @Column
    private Long id;

    @Column(length = 64)
    private String name;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)//懒加载   快速查询 不会查询role表
    @JoinTable(
            name = "ROLE_AUTH",
            joinColumns = {@JoinColumn(name = "AUTH_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "id")})
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return name.equals(authority.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
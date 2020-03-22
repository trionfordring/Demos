package icu.fordring.dao;

import icu.fordring.beans.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * @Description
 * @ClassName RoleDao
 * @Author fordring
 * @date 2020.03.19 14:27
 */
public interface RoleDao extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);
}

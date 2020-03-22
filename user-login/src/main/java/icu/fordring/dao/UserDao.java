package icu.fordring.dao;

import icu.fordring.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @ClassName UserDao
 * @Author fordring
 * @date 2020.03.16 23:10
 */
public interface UserDao extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);

}

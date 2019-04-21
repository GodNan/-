package com.css.bdpfnew.repository;

import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<SysUser, Long> {
    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    SysUser findByUsernameAndPassword(String username, String password);
}

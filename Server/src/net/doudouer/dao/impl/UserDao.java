package net.doudouer.dao.impl;

import org.springframework.stereotype.Repository;

import net.doudouer.domain.User;

@Repository("userDao")
public class UserDao extends BaseDaoImpl<User> {
}

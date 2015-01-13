package org.hepx.jgt.ssj.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.hepx.jgt.ssj.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);
}

package com.springboot.app.user.models.dao;

import com.springboot.app.commons.user.models.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users")
public interface UserDao extends PagingAndSortingRepository<User, Long> {
    public User findByUsername(String username);

    public User findById(Long id);
}

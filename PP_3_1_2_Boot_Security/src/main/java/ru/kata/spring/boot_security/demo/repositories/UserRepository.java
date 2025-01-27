package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u FROM User u left join fetch u.roles where u.username =:username")
    User findUsersByUsername(@Param("username") String username);

    @Query("select u FROM User u left join fetch u.roles where u.id =:id")
    Optional<User> findById(@Param("id") Long id);

    @Query("select distinct u from User u left join fetch u.roles")
    List<User> findAll();
}

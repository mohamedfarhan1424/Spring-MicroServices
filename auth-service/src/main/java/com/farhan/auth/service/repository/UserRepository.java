package com.farhan.auth.service.repository;


import com.farhan.auth.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByEmail(String email);


    @Query(value = "SELECT * FROM users WHERE email_address = :email AND password = :password",nativeQuery = true)
    public User login(@Param("email") String email,@Param("password") String password);



    @Modifying
    @Transactional
    @Query(value = "Update users set is_login = :login where email_address = :email",nativeQuery = true)
    public void setLogin(@Param("email") String email,@Param("login") boolean login);

    User findByUserId(Integer userId);

//    @Query(value = "SELECT is_login FROM users WHERE user_id = :userId",nativeQuery = true)
//    public boolean isLoggedIn(@Param("userId") Integer userId);


}

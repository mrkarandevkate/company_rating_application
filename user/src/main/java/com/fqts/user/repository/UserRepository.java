package com.fqts.user.repository;

import com.fqts.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

   @Query(value="select * from users where role='USER' ", nativeQuery = true)
   List<User>findAllUser();

   @Query(value="select * from users where role='ADMIN' ", nativeQuery = true)
   List<User>findAllAdmin();

   boolean existsByEmail(String email);

   @Query(value="SELECT * FROM users WHERE email = :email AND status = 'ALLOWED'", nativeQuery = true)
   User findByEmail(@Param("email") String email);


   @Query(value="SELECT COUNT(*) FROM users where role = 'ADMIN' ", nativeQuery = true)
   int countAllAdmin();

   @Modifying
   @Transactional
   @Query(value = "DELETE FROM users WHERE status = 'NOTALLOWED' AND created_At < :cutoffDate", nativeQuery = true)
   int deleteInactiveUsers(@Param("cutoffDate") Date cutoffDate);

}

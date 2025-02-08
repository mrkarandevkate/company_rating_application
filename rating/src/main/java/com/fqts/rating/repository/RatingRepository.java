package com.fqts.rating.repository;

import com.fqts.rating.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
    List<Rating> findByUserId(int userId);
    List<Rating> findByCompanyId(int companyId);
}

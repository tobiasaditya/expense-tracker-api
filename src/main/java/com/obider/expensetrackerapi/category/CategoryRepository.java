package com.obider.expensetrackerapi.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<List<Category>> findByUser_Id(Integer userId);

    Optional<Category> findByIdAndUser_Id(Integer categoryId,Integer userId);


}

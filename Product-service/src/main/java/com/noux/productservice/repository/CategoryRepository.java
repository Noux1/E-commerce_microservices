package com.noux.productservice.repository;

import com.noux.productservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository< Category , Long > {
    Category findCategoryByName(String name);
}
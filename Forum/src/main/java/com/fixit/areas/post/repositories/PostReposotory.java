package com.fixit.areas.post.repositories;

import com.fixit.areas.category.entities.Category;
import com.fixit.areas.post.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostReposotory extends JpaRepository<Post, Long> {
    List<Post> findAllByCategoriesContaining(Category categories);

    Page<Post> findAllByTitleContaining(String name, Pageable pageable);
}

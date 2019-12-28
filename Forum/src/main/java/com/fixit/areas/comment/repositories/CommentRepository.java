package com.fixit.areas.comment.repositories;

import com.fixit.areas.comment.entities.Comment;
import com.fixit.areas.post.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Set<Comment> findByPost(Post post);
}

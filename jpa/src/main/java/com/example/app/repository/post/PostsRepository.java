package com.example.app.repository.post;

import com.example.app.domain.help.Posts;
import com.example.app.domain.help.PostsId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, PostsId> {

    @EntityGraph(attributePaths = {"postGroupRelSet"})
    Posts findById_Id(Long id);
}

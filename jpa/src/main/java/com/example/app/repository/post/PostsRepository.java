package com.example.app.repository.post;

import com.example.app.domain.help.Posts;
import com.example.app.domain.help.PostsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, PostsId> {
}

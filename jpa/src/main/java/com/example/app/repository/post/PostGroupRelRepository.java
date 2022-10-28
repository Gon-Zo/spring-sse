package com.example.app.repository.post;

import com.example.app.domain.help.PostGroupRel;
import com.example.app.domain.help.PostGroupRelId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostGroupRelRepository extends JpaRepository<PostGroupRel, PostGroupRelId> {

    @EntityGraph(attributePaths = {"posts"})
    Optional<PostGroupRel> findById_PostsId(Long postsId);

    @EntityGraph(attributePaths = {"posts"})
    <T> Optional<T> findById_PostsId(Long postsId, Class<T> type);
}

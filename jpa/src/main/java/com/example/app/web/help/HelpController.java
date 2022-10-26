package com.example.app.web.help;

import com.example.app.domain.help.PostGroupRel;
import com.example.app.domain.help.Posts;
import com.example.app.repository.post.PostGroupRelRepository;
import com.example.app.repository.post.PostsRepository;
import com.example.app.service.dto.PostGroupRelInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/helps")
@RequiredArgsConstructor
@Transactional
public class HelpController {

    private final PostsRepository postsRepository;

    private final PostGroupRelRepository postGroupRelRepository;

    @GetMapping
    public List<Posts> getPostsList() {
        return postsRepository.findAll();
    }

    @GetMapping("/test")
    public Posts getPostsList_1() {
        return postsRepository.findById_Id(1L);
    }

    @GetMapping("rel")
    public PostGroupRel getPostGroupRelList() {
        return postGroupRelRepository.findById_PostsId(1L).orElseThrow();
    }

    @GetMapping("rel/p")
    public PostGroupRelInfo getPostGroupRelInfo() {
        return postGroupRelRepository.findById_PostsId(1L, PostGroupRelInfo.class).orElseThrow();
    }
}

package com.gdsc.springpractice.service.posts;

import com.gdsc.springpractice.domain.posts.Posts;
import com.gdsc.springpractice.domain.posts.PostsRepository;
import com.gdsc.springpractice.web.dto.PostsResponseDto;
import com.gdsc.springpractice.web.dto.PostsSaveRequestDto;
import com.gdsc.springpractice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There's no post. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There's no post. id=" + id));
        return new PostsResponseDto(entity);
    }
}

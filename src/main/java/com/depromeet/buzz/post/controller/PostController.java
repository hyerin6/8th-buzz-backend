package com.depromeet.buzz.post.controller;

import com.depromeet.buzz.post.dto.CommentResponses;
import com.depromeet.buzz.post.dto.PostDescriptionResponse;
import com.depromeet.buzz.post.dto.PostResponse;
import com.depromeet.buzz.post.dto.PostSellerResponse;
import com.depromeet.buzz.post.dto.PostsRequest;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @GetMapping
    @ApiResponse(description = "게시글의 옵션에 따른 정보를 가져온다(페이징)")
    @Parameters(value = {
        @Parameter(name = "category", description = "카테고리 정보", in = ParameterIn.QUERY),
        @Parameter(name = "page", description = "현재 페이지 정보 기본은 0", in = ParameterIn.QUERY),
        @Parameter(name = "size", description = "원하는 컨텐츠 수 기본은 10", in = ParameterIn.QUERY),
        @Parameter(name = "sortOption", description = "정렬 기준", in = ParameterIn.QUERY)
    })
    public ResponseEntity<Page<List<PostResponse>>> get(PostsRequest request, @PageableDefault Pageable pageable) {
        List<PostResponse> responses = new ArrayList<>();
        for (int i = 0; i < pageable.getPageSize(); i++) {
            responses.add(PostResponse.mock());
        }
        return ResponseEntity.ok(new PageImpl(responses, pageable, 100));
    }

    @GetMapping("{postId}/banner")
    @ApiResponse(description = "상세 게시글 이미지 URL(배너)")
    @Parameters(value = {
        @Parameter(name = "postId", description = "게시글 id", in = ParameterIn.PATH)
    })
    public ResponseEntity<String> getPostBanner(@PathVariable Long postId) {
        return ResponseEntity.ok("https://fs.jtbc.joins.com/prog/img/mig/MOBILE/PR10010297.jpg");
    }

    @GetMapping("{postId}/description")
    @ApiResponse(description = "제품명 및 할인률, 가격")
    @Parameters(value = {
        @Parameter(name = "postId", description = "게시글 id", in = ParameterIn.PATH)
    })
    public ResponseEntity<PostDescriptionResponse> getPostDescription(@PathVariable Long postId) {
        return ResponseEntity.ok(PostDescriptionResponse.mock());
    }

    @GetMapping("{postId}/seller")
    @ApiResponse(description = "주최자 정보")
    @Parameters(value = {
        @Parameter(name = "postId", description = "게시글 id", in = ParameterIn.PATH)
    })
    public ResponseEntity<PostSellerResponse> getSeller(@PathVariable Long postId) {
        return ResponseEntity.ok(PostSellerResponse.mock());
    }


    @GetMapping("{postId}/content")
    @ApiResponse(description = "상세 게시글 이미지 URL(배너)")
    @Parameters(value = {
        @Parameter(name = "postId", description = "게시글 id", in = ParameterIn.PATH)
    })
    public ResponseEntity<String> getContent(@PathVariable Long postId) {
        return ResponseEntity.ok("https://files.slack.com/files-pri/T01753WJ8H1-F01916BREKE/image.png");
    }


    @GetMapping("{postId}/comments")
    @ApiResponse(description = "제품에 대한 댓글리스트 가져옴")
    @Parameters(value = {
        @Parameter(name = "postId", description = "게시글 id", in = ParameterIn.PATH)
    })
    public ResponseEntity<CommentResponses> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(CommentResponses.mock());
    }

    @PostMapping("{postId}/like")
    @ApiResponse(description = "제품에 대한 좋아요 토글")
    @Parameters(value = {
        @Parameter(name = "postId", description = "게시글 id", in = ParameterIn.PATH)
    })
    public ResponseEntity<Boolean> like(@PathVariable Long postId) {
        return ResponseEntity.ok(true);
    }
}
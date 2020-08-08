package com.depromeet.buzz.comment.domain;

import com.depromeet.buzz.user.domain.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private User user;

    //TODO Post Entity 연관관계 추가

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> subComments;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parentComment;

    //TODO Like 추가

    private Comment() {
    }

    /**
     * 최상위 댓글 생성 시 사용 : 부모 댓글이 없다
     */
    public Comment(String comment, User user) {
        this.comment = comment;
        this.user = user;
    }

    /**
     * 대댓글 생성 시 사용 : 부모 댓글 필수
     */
    public Comment(String comment, User user, Comment parentComment) {
        this.comment = comment;
        this.user = user;
        this.parentComment = parentComment;
    }

    public Long getId() {
        return Id;
    }

    public String getComment() {
        return comment;
    }

    public User getUser() {
        return user;
    }

    public List<Comment> getSubComments() {
        return subComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(Id, comment.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "Id=" + Id +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                ", subComments=" + subComments +
                ", parentComment=" + parentComment +
                '}';
    }
}

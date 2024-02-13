package com.example.NewsManagement.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "comment_creator_id", nullable = false)
    @ToString.Exclude
    private User creator;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    @ToString.Exclude
    private News news;

}

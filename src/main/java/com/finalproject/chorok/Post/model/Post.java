package com.finalproject.chorok.Post.model;

import com.finalproject.chorok.common.model.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * [model] - 게시판 model
 *
 * @class   : Post
 * @author  : 김주호
 * @since   : 2022.04.03
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "post")
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", unique = true, nullable = false)
    private Long PostId;

    @ManyToOne
    @JoinColumn(name = "post_type_code", referencedColumnName = "post_type_code")
    private PostType postType;

    @Column(nullable = false)
    private String postTitle;

    @Column(nullable = false)
    private String postContent;

    @Column
    private String postImgUrlNo;
}

package com.techeer.suremarket.domain.image;

import com.techeer.suremarket.domain.posts.Posts;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Posts postId;

    private String uuid;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Builder
    public Images(String uuid) {
        this.uuid = uuid;
        this.isDeleted = false;
    }

    public int removeImage() {
        this.isDeleted = true;
        return 0;
    }
}

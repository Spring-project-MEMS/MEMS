package com.fixit.areas.post.models.binding;

import javax.validation.constraints.Size;
import com.fixit.constants.Constants;

public class EditPostBindingModel {

    private Long postId;

    @Size(min = 4, max = 50, message = Constants.TITLE_LENGTH)
    private String title;

    @Size(min = 8, max = 2000, message = Constants.POST_CONTENT_LENGTH)
    private String content;

    public EditPostBindingModel() {
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

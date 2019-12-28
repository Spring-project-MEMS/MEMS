package com.fixit.areas.comment.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fixit.constants.Constants;

public class CommentBindingModel {
    @Size(min = 3, max = 1000, message = Constants.COMMENT_LENGTH)
    private String commentContent;

    public CommentBindingModel() {
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}

package com.fixit.areas.post.models.binding;

import javax.validation.constraints.Size;
import com.fixit.constants.Constants;

public class CreatePostBindingModel {
    @Size(min = 4, max = 50, message = Constants.TITLE_LENGTH)
    private String title;

    @Size(min = 8, max = 2000, message = Constants.POST_CONTENT_LENGTH)
    private String content;

    private Long[] categories;

    public CreatePostBindingModel() {
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

    public Long[] getCategories() {
        return categories;
    }

    public void setCategories(Long[] categories) {
        this.categories = categories;
    }
}
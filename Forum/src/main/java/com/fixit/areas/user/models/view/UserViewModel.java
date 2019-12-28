package com.fixit.areas.user.models.view;

import com.fixit.areas.comment.models.service.CommentServiceModel;
import com.fixit.areas.post.models.view.PostViewModel;
import java.util.Set;

public class UserViewModel {
    private String username;

    private String email;

    private Set<PostViewModel> posts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<PostViewModel> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostViewModel> posts) {
        this.posts = posts;
    }
}
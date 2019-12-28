package com.fixit.areas.category.models.service;

import java.util.Set;
import com.fixit.areas.post.models.service.PostServiceModel;

public class CategoryServiceModel {
    private Long id;

    private String name;

    private String description;

    private Set<PostServiceModel> posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PostServiceModel> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostServiceModel> posts) {
        this.posts = posts;
    }
}
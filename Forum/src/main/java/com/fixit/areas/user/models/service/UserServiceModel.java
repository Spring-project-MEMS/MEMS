package com.fixit.areas.user.models.service;

import com.fixit.areas.comment.models.service.CommentServiceModel;
import com.fixit.areas.post.models.service.PostServiceModel;
import com.fixit.areas.role.models.service.RoleServiceModel;
import java.util.Set;

public class UserServiceModel {

    private Long id;

    private String password;

    private String username;

    private String email;
    
    private String company;
    
    private String phone;

    private Set<RoleServiceModel> roles;

    private Set<PostServiceModel> posts;

    private Set<CommentServiceModel> comments;

    private boolean admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
    
    public String getCompany() {
    	return company;
    }
    
    public void setCompany(String company) {
    	this.company = company;
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public void setPhone(String phone) {
    	this.phone = phone;
    }

    public Set<RoleServiceModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleServiceModel> roles) {
        this.roles = roles;
    }

    public Set<PostServiceModel> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostServiceModel> posts) {
        this.posts = posts;
    }

    public Set<CommentServiceModel> getComments() {
        return comments;
    }

    public void setComments(Set<CommentServiceModel> comments) {
        this.comments = comments;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
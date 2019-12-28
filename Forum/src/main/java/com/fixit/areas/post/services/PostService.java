package com.fixit.areas.post.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.fixit.areas.post.models.service.PostServiceModel;
import java.util.List;

public interface PostService {

    void create(PostServiceModel postServiceModel);

    List<PostServiceModel> findAll();

    Page<PostServiceModel> findAll(Pageable pageable);

    Page<PostServiceModel> findAllByName(String name, Pageable pageable);

    PostServiceModel findById(Long id);

    void edit(PostServiceModel postServiceModel);

    void deleteById(Long id);
}
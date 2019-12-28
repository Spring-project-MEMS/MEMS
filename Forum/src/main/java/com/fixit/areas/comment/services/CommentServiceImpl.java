package com.fixit.areas.comment.services;

import com.fixit.areas.comment.entities.Comment;
import com.fixit.areas.comment.models.service.CommentServiceModel;
import com.fixit.areas.comment.repositories.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(CommentServiceModel commentServiceModel) {
        Comment commentEntity = this.modelMapper.map(commentServiceModel, Comment.class);
        commentEntity.setPublishDate(LocalDateTime.now());
        this.commentRepository.save(commentEntity);
    }
}

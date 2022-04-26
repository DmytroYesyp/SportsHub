package com.sportshub.controller.comment;

import com.sportshub.annotation.Auth;
import com.sportshub.dto.comment.CommentCreateDto;
import com.sportshub.dto.comment.CommentDto;
import com.sportshub.dto.comment.CommentUpdateDto;
import com.sportshub.dto.count.CountDto;
import com.sportshub.service.comment.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @Auth(permission = "CREATE_COMMENT")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@RequestBody @Valid CommentCreateDto commentDto) {
        return commentService.create(commentDto);
    }

    @GetMapping
    @Auth(permission = "GET_COMMENTS_LIST")
    public List<CommentDto> findAll(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "1000") int limit) {

        return commentService.findAll(page, limit);
    }

    @GetMapping("{id}")
    @Auth(permission = "GET_COMMENT_BY_ID")
    public CommentDto find(@PathVariable Long id) {
        return commentService.find(id);
    }

    @GetMapping("count")
    @Auth(permission = "GET_COMMENTS_COUNT")
    public CountDto getCount() {
        return commentService.getCount();
    }

    @PutMapping("{id}")
    @Auth(permission = "UPDATE_COMMENT")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody CommentUpdateDto commentDto) {
        commentService.update(id, commentDto);
    }

    @DeleteMapping("{id}")
    @Auth(permission = "DELETE_COMMENT")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        commentService.delete(id);
    }
}

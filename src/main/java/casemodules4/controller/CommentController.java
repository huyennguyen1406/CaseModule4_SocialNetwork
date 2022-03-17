package casemodules4.controller;

import casemodules4.model.Comment;
import casemodules4.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Comment>> showAllByPost(@PathVariable("id") Long id) {
        Iterable<Comment> comments = commentService.findAllById_Post(id);

        if (!comments.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
    }

    @GetMapping("/demo/{id}")
    public ResponseEntity<Comment> showById(@PathVariable("id") Long id) {
        Optional<Comment> comments = commentService.findById(id);
        return comments.map(comment -> new ResponseEntity<>(comment, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable("id") long id, @RequestBody Comment comment) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            comment.setIdComment(commentOptional.get().getIdComment());
            comment = commentService.save(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> delete(@PathVariable("id") long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            commentService.remove(id);
            return new ResponseEntity<>(commentOptional.get(), HttpStatus.OK);
        }
    }
}

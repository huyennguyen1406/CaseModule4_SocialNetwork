package casemodules4.repository;

import casemodules4.model.Comment;
import casemodules4.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    Iterable<Comment> findAllById_Post(Long id);

    void deleteAllByPost(Post post);

    Iterable<Comment> deleteAllById_Comment();
}

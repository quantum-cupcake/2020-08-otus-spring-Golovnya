package ye.golovnya.otusbookstore.dao.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ye.golovnya.otusbookstore.entities.Comment;
import ye.golovnya.otusbookstore.factory.BookFactory;
import ye.golovnya.otusbookstore.factory.CommentFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
@Import(value = {BookJpaRepository.class, CommentJpaRepository.class})
@DataJpaTest
class CommentJpaRepositoryTest {

    @Autowired
    private CommentJpaRepository commentJpaRepository;
    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void whenPresentThenGetByIdReturnsComment() {
        var id = 1L;
        var commentOpt = commentJpaRepository.getById(id);
        if (commentOpt.isEmpty()) {
            fail("Комментарий не найден");
        }
        var comment = commentOpt.get();
        assertEquals("This is a comment", comment.getValue());
    }

    @Test
    void whenPresentThenGetByBookIdReturnsAllComments() {
        var comments = commentJpaRepository.getByBookId(1L);
        assertEquals(2, comments.size());
        var comment = comments.get(0);
        assertEquals("This is a comment", comment.getValue());
        comment = comments.get(1);
        assertEquals("And this is another comment", comment.getValue());
    }

    @Test
    void whenBookExistsThenPostingCommentPersists() {

        var persistedId = 52L;

        var bookOpt = bookJpaRepository.getById(1L);
        if (bookOpt.isEmpty()) {
            fail("Не найдена книга для комментирования");
        }
        var book = bookOpt.get();
        var comment = CommentFactory.buildComment(book);

        assertDoesNotThrow(() -> commentJpaRepository.create(comment));
        var persistedCommentOpt = commentJpaRepository.getById(persistedId);
        if (persistedCommentOpt.isEmpty()) {
            fail("Комментарий не найден после сохранения");
        }
        var persistedComment = persistedCommentOpt.get();
        assertEquals(comment.getValue(), persistedComment.getValue());
    }

    @Test
    void whenCommentExistsThenUpdateAltersValue() {
        var persistedId = 1L;
        var persistedCommentOpt = commentJpaRepository.getById(persistedId);
        if (persistedCommentOpt.isEmpty()) {
            fail("Комментарий для обновления не найден");
        }
        var persistedComment = persistedCommentOpt.get();
        var newValue = persistedComment.getValue() + " changed by update!";
        persistedComment.setValue(newValue);

        assertDoesNotThrow(() -> commentJpaRepository.update(persistedComment));
        var updatedCommentOpt = commentJpaRepository.getById(persistedId);
        if (updatedCommentOpt.isEmpty()) {
            fail("Комментарий не найден после обновления");
        }
        var updatedComment = updatedCommentOpt.get();
        assertEquals(newValue, updatedComment.getValue());
    }

    @Test
    void whenPresentThenDeleteByIdDeletesComment() {
        var persistedId = 1L;
        var commentOpt = commentJpaRepository.getById(persistedId);

        if (commentOpt.isEmpty()) {
            fail("Комментарий для удаления не найден");
        }

        commentJpaRepository.deleteById(persistedId);
        assertTrue(commentJpaRepository.getById(persistedId).isEmpty());
    }
}
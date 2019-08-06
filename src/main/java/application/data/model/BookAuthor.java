package application.data.model;

import javax.persistence.*;

    @Entity(name = "dbo_book_author")
    public class BookAuthor {
        @GeneratedValue(strategy= GenerationType.AUTO)
        @Id
        @Column(name = "book_author_id")
        private int id;

        @Column(name = "book_id")
        private int bookId;

        @Column(name = "author_id")
        private int authorId;

        public BookAuthor() {
        }

        public BookAuthor(int bookId, int auhtorId) {
            this.bookId = bookId;
            this.authorId = auhtorId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public int getAuhtorId() {
            return authorId;
        }

        public void setAuhtorId(int auhtorId) {
            this.authorId = auhtorId;
        }
    }

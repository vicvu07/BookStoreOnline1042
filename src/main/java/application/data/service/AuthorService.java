package application.data.service;

import application.data.model.Author;
import application.data.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public void addNewAuthor (Author author){
        authorRepository.save(author);
    }

    @Transactional
    public void addNewListAuthor (List<Author> authors){
        authorRepository.save(authors);
    }

    public Author findOne(int authorId) {
        return authorRepository.findOne(authorId);
    }

    public boolean updateAuthor (Author author) {
        try {
            authorRepository.save(author);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deteleAuthor (int authorId) {
        try {
            authorRepository.delete(authorId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Author> getListAllAuthor() {
        try {
            return authorRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

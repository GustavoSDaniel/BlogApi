package com.devgustavosdaniel.apiblog.Author;

import com.devgustavosdaniel.apiblog.exceptions.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors(Author author) {
        return authorRepository.findAll();
    }

    public Author getAuthorId(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new  MyException("O numero do ID " +id+ " não encontrado"));
    }

    public List<Author> getNameAuthor(String name){
        List<Author> names = authorRepository.findByNameContainingIgnoreCase(name);
        if (names.isEmpty()) {
            throw new MyException("Nome do autor não encontrado");
        }
        return names;
    }

    public void updateAuthorById(Author author){

        if (author.getIdAuthor() == null){
            throw new MyException("Não é possivel atualizar um Autor com Id inexistente");
        }
        if (!authorRepository.existsById(author.getIdAuthor())){
            throw new MyException("Id do Auto "+author.getIdAuthor()+" não encontrado");
        }
        authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        if (id == null){
            throw new MyException("Id do autor não pode ser nulo");
        }
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new  MyException("Id "+id+ " não encontrado"));

        if (!author.getPosts().isEmpty()) {
            throw new MyException("Não é possivel excluir um autor que contem posts");

        }

        authorRepository.delete(author);
    }

}

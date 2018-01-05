package com.mkyong.book.service;

import com.mkyong.book.model.Log;
import com.mkyong.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Log save(Log book) {
        return bookRepository.save(book);
    }

    public void delete(Log book) {
        bookRepository.delete(book);
    }

    public Log findOne(String id) {
        return bookRepository.findOne(id);
    }

    public Iterable<Log> findAll() {
        return bookRepository.findAll();
    }

    public Page<Log> findByAuthor(String author, PageRequest pageRequest) {
        return bookRepository.findBypath(author, pageRequest);
    }

    public List<Log> findByTitle(String title) {
        return bookRepository.findBypath(title);
    }

	@Override
	public Page<Log> findBypath(String path, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Log> findBypath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

}
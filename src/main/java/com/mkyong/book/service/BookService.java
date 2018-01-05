package com.mkyong.book.service;

import com.mkyong.book.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BookService {

	Log save(Log log);

    void delete(Log log);

    Log findOne(String id);

    Iterable<Log> findAll();

    Page<Log> findBypath(String path, PageRequest pageRequest);

    List<Log> findBypath(String path);

}
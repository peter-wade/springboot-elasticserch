package com.mkyong.book.repository;

import com.mkyong.book.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Log, String> {

    Page<Log> findBypath(String path, Pageable pageable);

    List<Log> findBypath(String path);

}
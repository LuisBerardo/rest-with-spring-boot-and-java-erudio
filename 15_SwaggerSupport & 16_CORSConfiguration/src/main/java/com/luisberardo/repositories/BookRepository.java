package com.luisberardo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisberardo.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}

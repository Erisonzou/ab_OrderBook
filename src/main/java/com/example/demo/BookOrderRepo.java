package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepo extends JpaRepository<BookOrder,String>{

}

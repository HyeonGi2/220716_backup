package com.mysite.sbb.article.dao;


import com.mysite.sbb.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

  boolean existsByTitle(String title);

  List<Article> findByTitle(String title);

  boolean existsByBody(String body);

  List<Article> findByBody(String body);

  boolean existsByTitleAndBody(String title, String body);

  List<Article> findByTitleAndBody(String title, String body);


}



package com.mysite.sbb.article.controller;

import com.mysite.sbb.article.dao.ArticleRepository;
import com.mysite.sbb.article.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {
  @Autowired
  private ArticleRepository articleRepository;

  @RequestMapping("/test")
  @ResponseBody
  public String testFunc() {
    return "test";
  }

  @RequestMapping("/list")
  @ResponseBody
  public List<Article> showArticleList(String title, String body) {
    if (title != null && body == null) {
      if (articleRepository.existsByTitle(title) == false) {
        System.out.println("제목과 일치하는 게시물이 없습니다.");
        return null;
      }
      return articleRepository.findByTitle(title);

    } else if (title == null && body != null) {
      if (articleRepository.existsByBody(body) == false) {
        System.out.println("내용과 일치하는 게시물이 없습니다.");
        return null;
      }
      return articleRepository.findByBody(body);

    } else if (title != null && body != null) {
      if (articleRepository.existsByTitleAndBody(title, body) == false) {
        System.out.println("제목, 내용과 일치하는 게시물이 없습니다.");
        return null;
      }
      return articleRepository.findByTitleAndBody(title, body);

    }

    return articleRepository.findAll();
  }

  @RequestMapping("/detail")
  @ResponseBody
  public Article showArticleDetail(@RequestParam long id, String name) {
    Optional<Article> article = articleRepository.findById(id);
    return article.orElse(null);
  }

  @RequestMapping("/doModify")
  @ResponseBody
  public Article doModify(long id, String title, String body) {
    Article article = articleRepository.findById(id).get();
    if (title != null) {
      article.setTitle(title); // 불러온 데이터 수정
    }
    if (body != null) {
      article.setBody(body); // 불러온 데이터 수정
    }

    article.setUpdateDate(LocalDateTime.now());

    articleRepository.save(article); // 수정된 데이터 db에 저장

    return article;
  }

  @RequestMapping("/doDelete")
  @ResponseBody
  public String doDelete(long id) {
    if (articleRepository.existsById(id) == false) {
      return "%d번 게시물이 삭제되었거나 존재하지 않습니다.".formatted(id);
    }

    articleRepository.deleteById(id);
    return "%d번 게시물이 삭제되었습니다.".formatted(id);

  }

  @RequestMapping("/doWrite")
  @ResponseBody
  public String doWrite(String title, String body) {
    if (title == null || title.trim().length() == 0) {
      return "제목을 입력해주세요";
    }

    if (body == null || body.trim().length() == 0) {
      return "내용을 입력해주세요";
    }
    title = title.trim();
    body = body.trim();

    Article article = new Article();
    article.setRegDate(LocalDateTime.now());
    article.setUpdateDate(LocalDateTime.now());
    article.setTitle(title);
    article.setBody(body);

    articleRepository.save(article);
    return "%d번 게시물이 생성되었습니다.".formatted(article.getId());
  }

}

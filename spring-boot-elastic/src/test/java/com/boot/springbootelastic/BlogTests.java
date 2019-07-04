package com.boot.springbootelastic;

import com.boot.springbootelastic.dao.BlogRepository;
import com.boot.springbootelastic.dao.UserRepository;
import com.boot.springbootelastic.entity.Blog;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * BlogTests.java 测试类
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/03
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogTests {
  @Autowired
  private BlogRepository repository;

  @Autowired
  private UserRepository userRepository;

  @Test
  public void findAll(){
    repository.findAll().forEach(System.out::println);
  }

  @Test
  public void save(){
    Blog blog = new Blog();
    blog.setTitle("My three blog entry");
    blog.setText("blog text");
    blog.setDate("2019/07/04");
    Blog result = userRepository.save(blog);
    System.out.println(result);
  }

  @Test
  public void findById(){
    Optional<Blog> result = userRepository.findById("1");
    System.out.println(result.orElse(null));
  }

  @Test
  public void findByTitle(){
    System.out.println(userRepository.findByTitle("three"));
  }
}
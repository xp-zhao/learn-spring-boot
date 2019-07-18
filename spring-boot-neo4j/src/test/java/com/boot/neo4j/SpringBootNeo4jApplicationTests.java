package com.boot.neo4j;

import com.boot.neo4j.entity.Movie;
import com.boot.neo4j.entity.Person;
import com.boot.neo4j.repository.MovieRepositroy;
import com.boot.neo4j.repository.PersonRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootNeo4jApplicationTests {

  @Autowired
  private MovieRepositroy movieRepositroy;
  @Autowired
  private PersonRepository personRepository;

  @Test
  public void contextLoads() {
  }

  @Test
  public void saveMovie(){
    Movie m1 = new Movie("无问西东", "2018");
    Movie m2 = new Movie("罗曼蒂克消亡史", "2016");
    System.out.println(movieRepositroy.save(m1));
    System.out.println(movieRepositroy.save(m2));
  }

  @Test
  public void savePerson(){
    Person p1 = new Person("章子怡", "1979");
    Person p2 = new Person("李芳芳", "1976");
    Person p3 = new Person("程耳", "1970");
    Optional<Movie> m1 = Optional.ofNullable(movieRepositroy.findByTitle("罗曼蒂克消亡史"));
    Optional<Movie> m2 = Optional.ofNullable(movieRepositroy.findByTitle("无问西东"));
    m1.ifPresent(movie -> {
      p1.addActor(movie);
      p3.addDirector(movie);
    });
    m2.ifPresent(movie -> {
      p1.addActor(movie);
      p2.addDirector(movie);
    });
    System.out.println(personRepository.save(p1));
    System.out.println(personRepository.save(p2));
    System.out.println(personRepository.save(p3));
  }

  @Test
  public void findByName(){
    System.out.println(personRepository.findByName("章子怡"));
  }

  @Autowired
  private Driver driver;

  @Test
  public void driverTest() {
    Session session = driver.session();
    Transaction tx = session.beginTransaction();
    StatementResult result = tx
        .run("match (n{node_id:'RJB_SX_KBZSD_3'}) - [r:CONTAIN] -> (m) return m");
    while (result.hasNext()) {
      Node node = result.next().get("m").asNode();
      node.asMap().forEach((k, v) -> System.out.println(k + ": " + v));
    }
  }

  @Test
  public void parentTest(){
    Session session = driver.session();
    Transaction tx = session.beginTransaction();
    StatementResult result = tx
        .run("match p = (n{node_id:'RJB_SX_KBZSD_530'}) <- [:CONTAIN*] - (m:ZSLY) return p");
    while (result.hasNext()) {
      Path path = result.next().get("p").asPath();
      path.relationships().forEach(relationship -> {
        relationship.asMap().forEach((k, v) -> System.out.println(k + ": " + v));
      });
      path.nodes().forEach(node -> {
        node.labels().forEach(System.out::println);
        node.asMap().forEach((k, v) -> System.out.println(k + ": " + v));
      });
    }
  }

}

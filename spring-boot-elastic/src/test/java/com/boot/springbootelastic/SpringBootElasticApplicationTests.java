package com.boot.springbootelastic;

import com.boot.springbootelastic.dao.EmployeeRepository;
import com.boot.springbootelastic.entity.Employee;
import com.boot.springbootelastic.service.EmployeeService;
import java.util.Arrays;
import java.util.List;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilters.InternalBucket;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms.Bucket;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootElasticApplicationTests {

  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private EmployeeRepository repository;
  @Autowired
  private ElasticsearchTemplate template;

  @Test
  public void contextLoads() {
  }

  @Test
  public void save() {
    Employee employee = new Employee();
    employee.setId(1);
    employee.setAge(23);
    employee.setFirstName("xp");
    employee.setLastName("hj");
    employee.setAbout("aboutTest");
    employee.setInterests(Arrays.asList("music", "food"));
    System.out.println(employeeService.save(employee).toString());
  }

  @Test
  public void findAll() {
    List<Employee> employees = employeeService.findAll();
    employees.forEach(System.out::println);
  }

  @Test
  public void findByListName() {
    employeeService.findByLastName("hj").stream().forEach(System.out::println);
  }

  @Test
  public void findByLastNameLike() {
//    employeeService.findByLastNameLike("j").stream().forEach(System.out::println);
    repository.findByLastNameEndingWith("j").stream().forEach(System.out::println);
  }

  @Test
  public void search() {
    QueryBuilder builder = new QueryStringQueryBuilder("id=1");
    System.out.println("查询语句：" + builder);
    repository.search(builder).forEach(System.out::println);
  }

  @Test
  public void tesgAgg() {
    NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
    builder.withQuery(QueryBuilders.matchQuery("lastName", "Smith"));
    builder.withIndices("megacorp").withTypes("employee");
    builder.withSearchType(SearchType.QUERY_THEN_FETCH);
    builder.addAggregation(
        AggregationBuilders.terms("test").field("interests.keyword")
            .subAggregation(AggregationBuilders.avg("ageAvg").field("age")));
    AggregatedPage<Employee> aggPage = (AggregatedPage<Employee>) repository
        .search(builder.build());
    StringTerms agg = (StringTerms) aggPage.getAggregation("test");
    List<Bucket> buckets = agg.getBuckets();
    buckets.forEach(bucket -> {
      System.out.println(bucket.getKeyAsString() + ": " + bucket.getDocCount());
      InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("ageAvg");
      System.out.println("平均年龄：" + avg.getValue());
    });
  }

  @Test
  public void testBlog(){

  }
}

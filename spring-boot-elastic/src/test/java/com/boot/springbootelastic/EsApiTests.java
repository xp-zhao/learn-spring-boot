package com.boot.springbootelastic;

import com.boot.springbootelastic.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiaoping
 * @Description: es api测试
 * @Date 2020-5-25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsApiTests {

  @Autowired
  private RestHighLevelClient client;

  @Test
  public void testCreateIndex() throws IOException {
    // 创建索引请求
    CreateIndexRequest request = new CreateIndexRequest("xp_index");
    // 客户端执行请求，然后获得响应
    CreateIndexResponse response = client.indices()
        .create(request, RequestOptions.DEFAULT);
    System.out.println(response);
  }

  @Test
  public void testExistIndex() throws IOException {
    // 判断索引是否存在
    GetIndexRequest request = new GetIndexRequest("xp_index2");
    boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
    System.out.println(exists);
  }

  @Test
  public void testDeleteIndex() throws IOException {
    DeleteIndexRequest request = new DeleteIndexRequest("xp_index");
    // 删除
    AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
    System.out.println(delete.isAcknowledged());
  }

  @Test
  public void testAddDocument() throws IOException {
    // 添加文档
    // 创建对象
    User user = User.builder().name("xp").age(23).build();
    // 创建请求
    IndexRequest request = new IndexRequest("xp_index");
    // 规则 put /xp_index/_doc/1
    request.id("1");
    request.timeout(TimeValue.timeValueSeconds(1));
    request.timeout("1s");

    ObjectMapper mapper = new ObjectMapper();
    request.source(mapper.writeValueAsString(user), XContentType.JSON);

    IndexResponse response = client.index(request, RequestOptions.DEFAULT);
    System.out.println(request.toString());
    System.out.println(response.status());
  }

  @Test
  public void testDocumentIsExits() throws IOException {
    GetRequest request = new GetRequest("xp_index", "1");
    request.fetchSourceContext(new FetchSourceContext(false));
    request.storedFields("_none_");
    boolean exists = client.exists(request, RequestOptions.DEFAULT);
    System.out.println(exists);
  }

  @Test
  public void testGetDocument() throws IOException {
    GetRequest request = new GetRequest("xp_index", "1");
    GetResponse response = client.get(request, RequestOptions.DEFAULT);
    System.out.println(response.getSourceAsString());
    System.out.println(response);
  }

  @Test
  public void testUpdateRequest() throws IOException {
    UpdateRequest request = new UpdateRequest("xp_index", "1");
    request.timeout("1s");

    User user = User.builder().name("xp").age(24).build();
    ObjectMapper mapper = new ObjectMapper();
    request.doc(mapper.writeValueAsString(user), XContentType.JSON);

    UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
    System.out.println(response.status());
  }

  @Test
  public void testDeleteRequest() throws IOException {
    DeleteRequest request = new DeleteRequest("xp_index", "1");
    request.timeout("1s");

    DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);
    System.out.println(delete.status());
  }
}

package com.boot.file;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date 2020-7-28
 **/
public class FileTest {

  @Test
  public void testNo() {
    List<String> fileNames = Arrays.asList("1.tmp", "5.tmp", "3.tmp", "2.tmp", "4.tmp");
    System.out.println(fileNames);
    fileNames.sort(Comparator.comparing(s ->
        Integer.valueOf(s.substring(0, s.lastIndexOf(".")))
    ));
    System.out.println(fileNames);
  }

  @Test
  public void testStr() {
    String str = "asdafsfasfs#13:34";
    System.out.println(str.substring(0, str.lastIndexOf("#")));
    String sizeStr = str.substring(str.lastIndexOf("#") + 1);
    System.out.println(sizeStr);
    System.out.println(sizeStr.split(":")[0]);
    System.out.println(sizeStr.split(":")[1]);
  }

  @Test
  public void testUrl() throws MalformedURLException {
    String path = "http://xp-oss-bucket.oss-cn-chengdu.aliyuncs.com/qrCode.jpg?Expires=1597637909&OSSAccessKeyId=LTAI4GFrTG7eAuWhUP4egoxx&Signature=jmB8ZV8sESq0YfeB0jEj6X2RGGQ%3D";
    URL url = new URL(path);
    System.out.println(url.getHost());
    System.out.println(url.getRef());
    path.replaceAll(url.getHost(), "oss.xp-zhao.cn");
    System.out.println(path);
  }

  @Test
  public void iterator() {
    List<Integer> list = new ArrayList<>();
    List<Integer> all = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    all.addAll(list);
    list.clear();
    System.out.println(all);

  }
}

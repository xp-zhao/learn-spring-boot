package com.boot.file;

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
  public void testStr(){
    String str = "asdafsfasfs#13:34";
    System.out.println(str.substring(0, str.lastIndexOf("#")));
    String sizeStr = str.substring(str.lastIndexOf("#") + 1);
    System.out.println(sizeStr);
    System.out.println(sizeStr.split(":")[0]);
    System.out.println(sizeStr.split(":")[1]);
  }
}

package com.example.demo.auto;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.lf5.util.StreamUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by shuqin on 17/6/23.
 */
public class ConcurrentDataHandlerFrame {
 
  public static void main(String[] args) {
    List<Integer> allData = getAllData(getKeys(), new GetTradeData());
    System.out.println(allData);
  }
 
  public static List<String> getKeys() {
    List<String> keys = new ArrayList<String>();
    for (int i=0; i< 20000; i++) {
      keys.add(String.valueOf(i));
    }
    return keys;
  }
 
  /**
   * 获取所有业务数据
   */
  public static <T> List<T> getAllData(List<String> allKeys, final IGetBizData iGetBizData) {
    List<String> parts = null; //TaskUtil.divide(allKeys.size(), 1000);
    System.out.println(parts);
    ExecutorService executor = Executors.newFixedThreadPool(parts.size());
    CompletionService<List<T>>
        completionService = new ExecutorCompletionService<List<T>>(executor);
    for (String part: parts) {
      int start = Integer.parseInt(part.split(":")[0]);
      int end = Integer.parseInt(part.split(":")[1]);
      if (end > allKeys.size()) {
        end = allKeys.size();
      }
      final List<String> tmpRowkeyList = allKeys.subList(start, end);
      completionService.submit( ()-> iGetBizData.getData(tmpRowkeyList));
    }

 
    List<T> result = new ArrayList<T>();
    for (int i=0; i< parts.size(); i++) {
      try {
        result.addAll(completionService.take().get());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    executor.shutdown();
    return result;
  }
 
}
 
/** 业务数据接口 */
interface IGetBizData<T> {
  List<T> getData(List<String> keys);
}
 
/** 获取业务数据具体实现 */
class GetTradeData implements IGetBizData<Integer> {
 
  public List<Integer> getData(List<String> keys) {
    // maybe xxxService.getData(keys);
    List<Integer> result = new ArrayList<Integer>();
    for (int i = 0; i < 100; i++) {
      result.add(i);
    }
    /*for (String key: keys) {
      result.add(Integer.valueOf(key) % 1000000000);
    }*/
    List<Object> ll =  result.stream().map(key -> Integer.valueOf(key) % 1000000000).collect(Collectors.toList());
    System.out.println(JSON.toJSONString(ll));
    return result.stream().map(key -> Integer.valueOf(key) % 1000000000).collect(Collectors.toList());
    //return result;
  }

  public static void main(String[] args) {
    /*List<Integer> result = new ArrayList<Integer>();
    for (int i = 0; i < 100; i++) {
      result.add(i);
    }
    List<Object> ll =  result.stream().filter(key -> Integer.valueOf(key) ==  55).collect(Collectors.toList());
    System.out.println(JSON.toJSONString(ll));
    */
    //new Thread(()-> System.out.println(1)).start();

  }
 
}
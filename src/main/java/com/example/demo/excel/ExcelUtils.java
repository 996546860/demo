package com.example.demo.excel;

import com.alibaba.fastjson.JSON;
import com.example.demo.exception.DemoException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Optional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


/**
 * 自动读取 excel信息 返回 List<Map>
 */
public class ExcelUtils {
    static ExecutorService executors = Executors.newFixedThreadPool(20);
    private static List<Map<String, Object>> mapList = new ArrayList<>();
    static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) throws Exception {
        /*executors.execute(() -> {
            try {
                ExcelUtils.readXLSX(new File("C:\\Users\\4607\\Desktop\\11.xlsx"));
                countDownLatch.countDown();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        countDownLatch.await();
        System.out.println("等待結束...");
        System.out.println(mapList);
        System.out.println(JSON.toJSONString(mapList));*/
        ExcelUtils.readXLSX(new File("C:\\Users\\4607\\Desktop\\11.xlsx"));
        countDownLatch.await();
        System.out.println("执行结束...");
        System.out.println(mapList);

    }

    public static void readXLSX(File file) throws InvalidFormatException, IOException {
        if (null == file) {
            throw new DemoException("不能上传空的文件");
        }
        Workbook book = new XSSFWorkbook(file);
        //需要获取有几个sheet
        int sheetNum = book.getNumberOfSheets();

        if (sheetNum == 0) {
            throw new DemoException("当前Excel没有数据");
        }

        System.out.println("当前执行的Excel中有" + sheetNum + "个sheet");

        //每一个sheet启动一条线程解析
        for (int i = 0; i < sheetNum; i++) {
            final int num = i;
            System.out.println(i);
            Future<?> submit = executors.submit(() -> {
                Sheet sheet = book.getSheetAt(num);
                try {
                    read(sheet, book);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        book.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
    }

    public static synchronized void read(Sheet sheet, Workbook book) throws IOException {

        int rowStart = sheet.getFirstRowNum();    // 首行下标
        int rowEnd = sheet.getLastRowNum() + 1;    // 尾行下标
        //System.out.println("当前的行数为:" + (rowEnd - rowStart));
        int rows = rowEnd - rowStart;
        // 如果首行与尾行相同，表明只有一行，直接返回空数组

        // 获取第一行JSON对象键
        for (int i = 0; i < rows; i++) {
            Map<String, Object> map = new HashMap<>();
            Row row = sheet.getRow(i);
            int cellStart = row.getFirstCellNum();
            int cellEnd = row.getLastCellNum();
            for (int j = cellStart; j < cellEnd; j++) {
                Integer key = j;
                //System.out.println("key :" + key + " value :" + row.getCell(j));
                map.put("A" + key.toString(), row.getCell(j) == null ? "" : row.getCell(j).toString());
            }
            mapList.add(map);
        }
    }

}

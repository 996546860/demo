package com.example.demo.elSearch.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: fzh
 * @Date: 2020/6/11 15:34
 * @Content:
 */
@Data
public class esAdd {
    private String title;
    private String author;
    private int wordCount;
    private Date pulishDate;
}

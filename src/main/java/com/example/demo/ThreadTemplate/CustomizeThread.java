package com.example.demo.ThreadTemplate;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.Future;

/**
 * @Author: fzh
 * @Date: 2020/9/25 18:32
 * @Content:
 */
@Accessors(chain = true)
@Data
public class CustomizeThread {

    private Runnable runnable;
    private String threadName;
    private Future future;

}

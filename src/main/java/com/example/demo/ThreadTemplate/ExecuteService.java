package com.example.demo.ThreadTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: fzh
 * @Date: 2020/9/25 16:18
 * @Content:
 */
@Slf4j
public class ExecuteService {

    public static void execute(Runnable runnable) {
        log.info("增加任务");
        Boolean aBoolean = ThreadPoolSingle.addEvent(runnable);
        log.info("任务增加结果[{}]",aBoolean);
    }
}

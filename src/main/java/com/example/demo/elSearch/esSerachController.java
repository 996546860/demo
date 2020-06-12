package com.example.demo.elSearch;

import com.example.demo.elSearch.vo.esAdd;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: fzh
 * @Date: 2020/6/11 15:29
 * @Content:
 */
@RequestMapping("/es")
@RestController
public class esSerachController {

    @Autowired
    private TransportClient client;

    @PostMapping("/add")
    public IndexResponse add(@RequestBody esAdd adds) {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title", adds.getTitle())
                    .endObject();
            IndexResponse indexResponse = this.client.prepareIndex("book", "fzh")
                    .setSource(builder)
                    .get();
            return indexResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

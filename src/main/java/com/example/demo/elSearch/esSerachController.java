package com.example.demo.elSearch;

import com.example.demo.elSearch.vo.esAdd;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get")
    public ResponseEntity get(@RequestParam(value = "id") String id){
        GetRequestBuilder getRequestBuilder = this.client.prepareGet("book", "fzh", id);
        return new ResponseEntity(getRequestBuilder.get().getSource(), HttpStatus.OK);
    }

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

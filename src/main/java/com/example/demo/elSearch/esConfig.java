package com.example.demo.elSearch;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @Author: fzh
 * @Date: 2020/6/10 23:08
 * @Content:
 */
@Slf4j
@Configuration
public class esConfig {

    @Bean
    public TransportClient clent() throws UnknownHostException {
        try {
          return initClient();
        } catch (Exception e) {
           log.error("Es链接失败");
        }
        return null;
    }

    private TransportClient initClient() throws UnknownHostException {
        InetSocketTransportAddress node = new InetSocketTransportAddress(InetAddress.getByName("106.13.185.153"),9300);

        Settings settings = Settings.builder()
                .put("cluster.name", "fzh")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings);

        client.addTransportAddress(node);
        return client;
    }
}

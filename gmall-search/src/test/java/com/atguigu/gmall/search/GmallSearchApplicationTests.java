package com.atguigu.gmall.search;

import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class GmallSearchApplicationTests {

    @Autowired
    private JestClient jestClient;

    @Test
    void contextLoads() throws IOException {
        Index index = new Index.Builder(new User("liuyan", "123456", 18)).index("user").type("info").id("1").build();
        jestClient.execute(index);
//        Index update =new Index.Builder(new User("gaole","123456",null)).index("user").type("info").id("1").build();
//        DocumentResult result = jestClient.execute(update);
//        System.out.println(result.toString());
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private String name;
    private String password;
    private Integer age;
}


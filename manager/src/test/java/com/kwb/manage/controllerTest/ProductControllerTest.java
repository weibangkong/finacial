package com.kwb.manage.controllerTest;

import com.kwb.entity.Product;
import com.kwb.enums.ProductStatus;
import com.kwb.util.common.HttpUtil;
import com.kwb.util.common.RestUtil;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//随机端口
public class ProductControllerTest {
    //功能化测试
    private static RestTemplate rest = new RestTemplate();

//    @Value("http://localhost:${local.server.port}/manage")
    @Value("http://localhost:${local.server.port}/manager")
    private String baseUrl;

    //初始化数据
    private static List<Product> normals = new ArrayList<Product>();

    private static List<String> ids = new ArrayList<>();

    @BeforeClass
    public static  void init() {
        Product p1 = new Product("t01", "哈喽1号", ProductStatus.AUDITING.name(),
                BigDecimal.valueOf(5),8, BigDecimal.valueOf(10));
        Product p2 = new Product("t02", "哈喽8fghjkhgfghjkdfghjk号", ProductStatus.IN_SELL.name(),
                BigDecimal.valueOf(20),4, BigDecimal.valueOf(18));
        Product p3 = new Product("t03", "", ProductStatus.AUDITING.name(),
                BigDecimal.valueOf(100),8, BigDecimal.valueOf(28));

        normals.add(p1);
        normals.add(p2);
        normals.add(p3);

        ids.add("001");
        ids.add("002");
        ids.add("003");

        ResponseErrorHandler errorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {

            }
        };
    }

    @Test
    public void create() {
        normals.forEach(product -> {
            HttpUtil.post(baseUrl+"/products",product.toString());
        });
    }

    @Test
    public void getOne(){
       ids.forEach(id -> System.err.println("id："+id+"结果如下："+RestUtil.sendGet(baseUrl+"/products/"+id,"")));
    }
}

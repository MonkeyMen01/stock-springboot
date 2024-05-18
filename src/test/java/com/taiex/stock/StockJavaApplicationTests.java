package com.taiex.stock;

import com.taiex.stock.services.TaiexRequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class StockJavaApplicationTests {
    @Autowired
    private TaiexRequestService taiexRequestService;

    @Test
    void contextLoads() {
        taiexRequestService.requestStockDay();
    }

}

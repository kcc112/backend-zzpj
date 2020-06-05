package com.zzpj.backend.warehouse;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.entities.Warehouse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WarehouseTest {

    @Test
    void warehouseClassTest(){
        Warehouse warehouse = new Warehouse();
        Alcohol alcohol = new Alcohol();
        alcohol.setName("Piwo");
        warehouse.setAlcohol(alcohol);
        warehouse.setAmount(10);
        warehouse.setId(20l);
        Assert.assertEquals(alcohol, warehouse.getAlcohol());
        Assert.assertEquals(alcohol.getName(), warehouse.getAlcohol().getName());
        Assert.assertEquals(10, warehouse.getAmount());
        Assert.assertEquals(java.util.Optional.of(20L).get(), warehouse.getId());
        warehouse.addAmount(5);
        Assert.assertEquals(15, warehouse.getAmount());
    }
}

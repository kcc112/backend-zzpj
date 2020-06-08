package com.zzpj.backend.warehouse;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.entities.Warehouse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class WarehouseTest {

    @Test
    void warehouseClassTest(){
        UUID uuid = UUID.randomUUID();
        Warehouse warehouse = new Warehouse();
        Alcohol alcohol = new Alcohol();
        List<Alcohol> alcohols = new ArrayList<>();
        alcohols.add(alcohol);
        alcohol.setName("Piwo");
        warehouse.setAlcohols(alcohols);
        warehouse.setAmount(10);
        warehouse.setUuid(uuid);
        Assert.assertEquals(alcohol, warehouse.getAlcohols().get(0));
        Assert.assertEquals(alcohol.getName(), warehouse.getAlcohols().get(0).getName());
        Assert.assertEquals(10, warehouse.getAmount());
        Assert.assertEquals(java.util.Optional.of(uuid).get(), warehouse.getUuid());
        warehouse.addAmount(5);
        Assert.assertEquals(15, warehouse.getAmount());
    }
}

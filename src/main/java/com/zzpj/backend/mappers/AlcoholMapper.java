package com.zzpj.backend.mappers;

import com.zzpj.backend.dto.AlcoholDTO;
import com.zzpj.backend.entities.Alcohol;

public class AlcoholMapper {
    public Alcohol mapAlcoholDTOToAlcohol(AlcoholDTO alcoholDTO) {
        Alcohol alcohol = new Alcohol();
        alcohol.setUuid(alcoholDTO.getUuid());
        alcohol.setName(alcoholDTO.getName());
        alcohol.setCost(alcoholDTO.getCost());
        return alcohol;
    }
}

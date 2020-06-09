package com.zzpj.backend.mappers;

import com.zzpj.backend.dto.AddAlcoholDTO;
import com.zzpj.backend.dto.AlcoholDTO;
import com.zzpj.backend.entities.Alcohol;

public class AlcoholMapper {

    public static Alcohol mapAlcoholDTOToAlcohol(AlcoholDTO alcoholDTO) {
        Alcohol alcohol = new Alcohol();
        alcohol.setUuid(alcoholDTO.getUuid());
        alcohol.setName(alcoholDTO.getName());
        alcohol.setCost(alcoholDTO.getCost());
        return alcohol;
    }

    public static Alcohol mapAddAlcoholDTOToAlcohol(AddAlcoholDTO addAlcoholDTO) {
        Alcohol alcohol = new Alcohol();
        alcohol.setUuid(addAlcoholDTO.getUuid());
        alcohol.setName(addAlcoholDTO.getName());
        alcohol.setCost(addAlcoholDTO.getCost());
        return alcohol;
    }
}

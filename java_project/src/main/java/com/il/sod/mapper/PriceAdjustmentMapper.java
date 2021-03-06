package com.il.sod.mapper;

import com.il.sod.db.model.entities.PriceAdjustment;
import com.il.sod.db.model.entities.PriceAdjustmentType;
import com.il.sod.rest.dto.db.PriceAdjustmentDTO;
import com.il.sod.rest.dto.db.PriceAdjustmentTypeDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.ConverterFactory;

public enum PriceAdjustmentMapper {

  INSTANCE;
  private final MapperFacade mapperFacade;

  private PriceAdjustmentMapper() {

    ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
    converterFactory.registerConverter("priceAdjustmentSetConverter", new PriceAdjustmentSetConverter());

    BaseMapper.MAPPER_FACTORY.classMap(PriceAdjustmentDTO.class, PriceAdjustment.class)
            .field("idPriceAdjustmentType", "priceAdjustmentType.idPriceAdjustmentType")
            .exclude("orderPriceAdjustments")
            .byDefault()
            .register();

    BaseMapper.MAPPER_FACTORY.classMap(PriceAdjustmentTypeDTO.class, PriceAdjustmentType.class)
            .fieldMap("priceAdjustments", "priceAdjustments").converter("priceAdjustmentSetConverter").mapNulls(false).mapNullsInReverse(true).add()
            .byDefault()
            .register();


    mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
  }

  public PriceAdjustment map(PriceAdjustmentDTO input) {
    return this.mapperFacade.map(input, PriceAdjustment.class);
  }

  public PriceAdjustmentDTO map(PriceAdjustment input) {
    return this.mapperFacade.map(input, PriceAdjustmentDTO.class);
  }

  public PriceAdjustmentType map(PriceAdjustmentTypeDTO dto) {
    return this.mapperFacade.map(dto, PriceAdjustmentType.class);
  }

  public PriceAdjustmentTypeDTO map(PriceAdjustmentType dto) {
    return this.mapperFacade.map(dto, PriceAdjustmentTypeDTO.class);
  }

}




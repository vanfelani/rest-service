package com.enigma.restservice.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TypeEnumConverter implements AttributeConverter<TypeEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TypeEnum type) {
        if (type == null) {
            return null;
        }

        switch (type) {
            case IN:
                return 0;
            case OUT:
                return 1;
            default:
                throw new IllegalArgumentException(type + " not supported.");
        }
    }

    @Override
    public TypeEnum convertToEntityAttribute(Integer enumData) {
        switch (enumData) {
            case 0:
                return TypeEnum.IN;
            case 1:
                return TypeEnum.OUT;
            default:
                throw new IllegalArgumentException(enumData + " not supported.");
        }
    }

}

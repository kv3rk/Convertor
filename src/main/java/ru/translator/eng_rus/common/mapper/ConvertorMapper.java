package ru.translator.eng_rus.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.translator.eng_rus.Convertor.DTO.ConvertorDTO;
import ru.translator.eng_rus.Convertor.Entity.ConvertorEntity;

@Mapper(componentModel = "spring")
public interface ConvertorMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "time", source = "time")
    @Mapping(target = "wrongString", source = "wrongString")
    @Mapping(target = "rightString", source = "rightString")
    ConvertorDTO convertorEntityToDTO(ConvertorEntity convertorEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "time", source = "time")
    @Mapping(target = "wrongString", source = "wrongString")
    @Mapping(target = "rightString", source = "rightString")
    ConvertorEntity convertorDTOToEntity(ConvertorDTO convertorDTO);
}

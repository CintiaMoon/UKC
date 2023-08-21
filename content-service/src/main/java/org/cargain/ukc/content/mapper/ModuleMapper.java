package org.cargain.ukc.content.mapper;

import org.cargain.ukc.content.domain.CreateModuleRequest;
import org.cargain.ukc.content.domain.ModulesResponse;
import org.cargain.ukc.content.model.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ModuleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    Module toModule(CreateModuleRequest createModuleRequest);

    ModulesResponse toModuleResponse(Module module);

}

package com.guicr3.project_java_springboot.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    //mapear entity to DTO e vice versa
    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    //mapeamento de classe origem para destino
    public static <O, D> D parseObject(O origin, Class<D> destination){

        //retorno do metodo ocorrendo
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        List<D> destinationObjects = new ArrayList<>();
        for(Object o : origin){
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }
}

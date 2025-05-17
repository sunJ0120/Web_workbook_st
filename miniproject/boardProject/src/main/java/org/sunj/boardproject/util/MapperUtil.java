package org.sunj.boardproject.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {
    INSTANCE;

    private ModelMapper modelMapper;

    MapperUtil(){
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true) //field 값으로 매칭
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT); //엄격하게 매칭
    }

    public ModelMapper get(){
        return modelMapper;
    }

    //Null Checking을 위한 메서드 추가
    public <S,D> D mapOrNull(S source, Class<D> destinationType){
        if(source == null) return null;
        return modelMapper.map(source, destinationType);
    }
}

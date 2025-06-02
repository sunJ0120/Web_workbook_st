package org.zerock.springex.mapper;

import org.apache.ibatis.annotations.Select;

/*
이 timeMapper interface에서 쿼리를 작성할 수 있다.
작성된 인터페이스를 Mapper 인터페이스라고 하는데, 마지막에 어떤 인터페이스를 설정했는지 root-context.xml에 등록해야 한다.
 */
public interface TimeMapper {
    @Select("select now()")
    String getTime();
}

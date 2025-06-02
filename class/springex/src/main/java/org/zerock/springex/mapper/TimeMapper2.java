package org.zerock.springex.mapper;

/*
애노테이션으로 정의하는 것이 아니라, xml 파일로 따로 빼서 정의해보자.
xml 파일 같은 경우는 <select>같은 태그로 감싸서 구성한다.

여기에는 애노테이션이 없는 getNow()<- 메서드만을 정의한다.
 */
public interface TimeMapper2 {
    String getNow();
}

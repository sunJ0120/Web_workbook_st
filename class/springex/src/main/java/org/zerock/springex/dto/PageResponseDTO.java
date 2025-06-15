package org.zerock.springex.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int total;

    //시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;

    //이전 페이지 존재 여부
    private boolean prev;
    //다음 페이지 존재 여부
    private boolean next;
    //페이지 번호 목록 (dto 리스트)
    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.end = (int) (Math.ceil(page / (double) size)) * 10; // 현재 페이지를 기준으로 끝 페이지 계산
        this.start = end - 9; // 현재 페이지 당 개수는 10개이므로, 9를 빼면 start 페이지를 구할 수 있다.
        int last = (int) (Math.ceil(total / (double) size)) * 10; //전체 페이지에서 나눠서 끝값을 정한다.

        this.end = end > last ? last : end; //end가 더 크다면, last가 진짜 끝 값이므로 여기에 맞춘다.
        this.prev = this.start > 1; //1보다 크면 무조건 이전 페이지가 있는 것이다.
        this.next = total > this.end * this.size; //next는 전체 개수가 아직 더 크면 있는 것이다.
    }
}

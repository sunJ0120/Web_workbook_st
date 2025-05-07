package org.sunj.boardproject.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private Long boardno;
    private String title;
    private String content;
    private LocalDate regDate;
    private LocalDate updateDate;
    private boolean isPublic;
}

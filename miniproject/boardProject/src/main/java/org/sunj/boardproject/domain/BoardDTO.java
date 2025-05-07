package org.sunj.boardproject.domain;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDTO {
    private Long boardno;
    private String title;
    private String content;
    private LocalDate regDate;
    private LocalDate updateDate;
    private boolean isPublic;
}

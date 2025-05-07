package org.sunj.boardproject.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDTO {
    private Long boardno;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private boolean isPublic;
}

package org.sunj.boardproject.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private Long boardno;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private boolean isPublic;
}

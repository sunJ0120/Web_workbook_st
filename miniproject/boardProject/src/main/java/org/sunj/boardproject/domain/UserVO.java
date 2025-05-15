package org.sunj.boardproject.domain;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private String userId; //매핑 주의!
    private String userPw;
    private String userName;
    private String uuid;
}

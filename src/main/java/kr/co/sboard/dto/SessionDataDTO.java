package kr.co.sboard.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Component
@SessionScope // 꼭 필요!!! 각 세션(사용자)마다 생성
public class SessionDataDTO
{
    private String code;

}

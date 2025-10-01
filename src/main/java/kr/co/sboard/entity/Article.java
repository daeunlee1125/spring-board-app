package kr.co.sboard.entity;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SB_ARTICLE")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ano;

    private String cate;
    private String title;
    private String writer;
    private String content;
    private int comment_cnt;
    private int file_cnt;
    private int hit_cnt;
    private String reg_ip;
    @CreationTimestamp
    private LocalDateTime wdate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ano")
    private List<File> fileList;


    // 추가 필드
    @Transient
    private String nick;

    public void setNick(String nick) {
        this.nick = nick;
    }

}

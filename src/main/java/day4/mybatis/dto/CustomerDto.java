package day4.mybatis.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor // Jackson 라이브러리의 매핑에 필요합니다. json => java 객체로 변환할때
@EqualsAndHashCode
@ToString
public class CustomerDto {
	private String custom_id;
    private String name;
    private String email;
    private int age;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private Date reg_date;
}
package day4.mybatis.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

// 웹에서는 vo 단어 대신 dto를 많이 씁니다.
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BuyDto {
	private int buy_idx;
    private String customid;
    private String pcode;
    private String pname;
    private int price;
    private int quantity;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private Date buy_date;

    public BuyDto(String customid, String pcode, int quantity) {
        this.customid = customid;
        this.pcode = pcode;
        this.quantity = quantity;
    }

    public BuyDto(String customid, String pcode, int quantity, Date buy_date) {
        this.customid = customid;
        this.pcode = pcode;
        this.quantity = quantity;
        this.buy_date = buy_date;
    }
}
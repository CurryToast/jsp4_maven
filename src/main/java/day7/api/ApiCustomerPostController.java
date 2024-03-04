package day7.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import day4.mybatis.dao.MybatisCustomerDao;
import day4.mybatis.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiCustomerPostController implements Controller {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		InputStream inputStream = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
		StringBuffer sb = new StringBuffer();
		String line = null;
		
		while((line = br.readLine()) != null) {
			sb.append(line);
			log.info("line : {}", line);
		}
		
		ObjectMapper objMapper = new ObjectMapper();
		CustomerDto data = objMapper.readValue(sb.toString(), CustomerDto.class);
		
		log.info("변환된 dto : {}", data);
		
		MybatisCustomerDao dao = new MybatisCustomerDao();

		int result = 0;
		try {
			result = dao.join(data);
		} catch (Exception e) {
			log.info("dao insert 예외 : {}", e.getMessage());                           
		}

		// 순수한 문자열로 보내기
//		String msg = result == 1 ? "회원등록이 완료되었습니다." : "회원등록 오류입니다. id 중복을 확인하세요.";
//		response.setContentType("text/plain; charset=UTF-8");
//		response.getWriter().print(msg);
		
		// Json 문자열로 보내기
		String jsonData = "{ \"result\": " + result + " }";
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonData);
	}
}

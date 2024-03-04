package day7.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import day4.mybatis.dao.MybatisCustomerDao;
import day4.mybatis.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiCustomerGetController implements Controller {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MybatisCustomerDao dao = new MybatisCustomerDao();
		CustomerDto dto = dao.selectById(request.getParameter("id"));
		log.info("customer id : {}", dto);
		
		ObjectMapper objMapper = new ObjectMapper();
		String data = objMapper.writeValueAsString(dto);
		log.info("전송할 json 문자열 : {}", data);
		
//		response.setContentType("application/json; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		out.print(jsonData);
		
		int result = data != null ? 1 : 0;
		String jsonData = "{ \"result\": " + result + " }";
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonData);
	}
}

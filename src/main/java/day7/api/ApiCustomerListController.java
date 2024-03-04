package day7.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import day4.mybatis.dao.MybatisCustomerDao;
import day4.mybatis.dto.CustomerDto;

public class ApiCustomerListController implements Controller {
	public static final Logger logger = LoggerFactory.getLogger(ApiCustomerListController.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MybatisCustomerDao dao = new MybatisCustomerDao();
		List<CustomerDto> list = dao.selectAll();
		
		ObjectMapper objMapper = new ObjectMapper();
		String data = objMapper.writeValueAsString(list);
		logger.info("전송할 json 문자열 : {}", data);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(data);
	}
}

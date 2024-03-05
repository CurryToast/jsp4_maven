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

import day4.mybatis.dao.MybatisBuyDao;
import day4.mybatis.dto.BuyDto;

public class ApiBuyListController implements Controller {
	public static final Logger logger = LoggerFactory.getLogger(ApiBuyListController.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MybatisBuyDao dao = new MybatisBuyDao();
		List<BuyDto> list = dao.selectAll();
		
		ObjectMapper objMapper = new ObjectMapper();
		String jsonData = objMapper.writeValueAsString(list);
		logger.info("전송할 json 문자열 : {}", jsonData);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonData);
	}

}

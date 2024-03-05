package day7.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import day4.mybatis.dao.MybatisProductDao;
import day4.mybatis.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiProductSearchController implements Controller {
	public static final Logger logger = LoggerFactory.getLogger(ApiProductSearchController.class);

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
		@SuppressWarnings("unchecked")
		Map<String, Object> map = objMapper.readValue(sb.toString(), Map.class);
		log.info("변환된 map: {}", map);
		 
		MybatisProductDao dao = new MybatisProductDao();
		List<ProductDto> list = dao.search(map);

		ObjectMapper obM = new ObjectMapper();
		String jsonData = obM.writeValueAsString(list);
		logger.info("전송할 json 문자열 : {}", jsonData);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter(); out.print(jsonData);
	}
}

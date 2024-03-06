package day7.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import day4.mybatis.dao.MybatisProductDao;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiProdudctDeleteController implements Controller {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pcode = request.getParameter("pcode");
		
		MybatisProductDao dao = new MybatisProductDao();
		int result = 0;
		
		try {
			result = dao.delete(pcode);
		} catch (Exception e) {
			log.info("dao insert 예외 : {}", e.getMessage());
		}
		
		String jsonData = "{ \"result\": " + result + " }";
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonData);
	}

}

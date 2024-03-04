package mybatis;

import java.util.List;

import day4.mybatis.dao.MybatisBuyDao;
import day4.mybatis.dao.MybatisCustomerDao;
import day4.mybatis.dao.MybatisProductDao;
import day4.mybatis.dto.BuyDto;
import day4.mybatis.dto.CustomerDto;
import day4.mybatis.dto.ProductDto;

public class MybatisSessionTest {
	public static void main(String[] args) {
		playCustomerDao();
//		playProductDao();
//		playBuyDao();
	}
	
	private static void playCustomerDao() {
		MybatisCustomerDao dao = new MybatisCustomerDao();
		
		System.out.println("===== insert 테스트 =====");
		int result = dao.join(new CustomerDto("stupidkim", "김바보", "stupidkim@babo.io" , 60, null));
		System.out.println("반영된 행 개수 : " + result);
		
		System.out.println("===== selectAll 테스트 =====");
		List<CustomerDto> list = dao.selectAll();
		System.out.println("고객 전체 조회 : " + list);
		
		System.out.println("===== selectByNameAndAge 테스트 =====");
		List<CustomerDto> list2 = dao.selectByNameAndAge(new CustomerDto(null, "김바보", null, 60, null));
		System.out.println("김바보 고객 조회 : " + list2);
		
		System.out.println("===== modify 테스트 =====");
		int result2 = dao.modify(new CustomerDto("stupidkim", null, "BABOKIM@babo.io" , 0, null));
		System.out.println("반영된 행 개수 : " + result2);
		
		System.out.println("===== selectAll 테스트 =====");
		List<CustomerDto> list3 = dao.selectAll();
		System.out.println("고객 전체 조회 : " + list3);
		
		System.out.println("===== delete 테스트 =====");
		int result3 = dao.delete("stupidkim");
		System.out.println("반영된 행 개수 : " + result3);
	}
	
	private static void playProductDao() {
		MybatisProductDao dao = new MybatisProductDao();

		System.out.println("===== insert 테스트 =====");
		int result = dao.insert(new ProductDto("BBQch001", "B3", "BBQ 치킨", 32000));
		System.out.println("반영된 행 개수 : " + result);

		System.out.println("===== selectAll 테스트 =====");
		List<ProductDto> list = dao.selectAll();
		System.out.println("상품 전체 조회 : " + list);
		
		System.out.println("===== selectByCategory 테스트 =====");
		List<ProductDto> list2 = dao.selectByCategory("A2");
		System.out.println("상품 전체 조회 : " + list2);
		
		System.out.println("===== selectByPname 테스트 =====");
		List<ProductDto> list3 = dao.selectByPname("사과");
		System.out.println("상품 전체 조회 : " + list3);
	}
	
	private static void playBuyDao() {
		MybatisBuyDao dao = new MybatisBuyDao();
		
		System.out.println("===== insert 테스트 =====");
		int result = dao.insert(new BuyDto("mina012", "DOWON123a", 20));
		System.out.println("반영된 행 개수 : " + result);
		
		System.out.println("===== selectAll 테스트 =====");
		List<BuyDto> list = dao.selectAll();
		System.out.println(list);
		
		System.out.println("===== selectById 테스트 =====");
		List<BuyDto> list2 = dao.selectById("mina012");
		System.out.println(list2);
	}
}

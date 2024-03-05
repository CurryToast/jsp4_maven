package day7.api;

import java.util.HashMap;
import java.util.Map;

public class RequestControllerMapping {
	private static final Map<RequestMap, Controller> mapping = new HashMap<>();

	// rest api 컨트롤러는 /api로 시작하도록 합시다.
	public static void init() {
		// 동기 통신 컨트롤러
		mapping.put(new RequestMap("/products", "GET"), new ProductsController());
		mapping.put(new RequestMap("/buys", "GET"), new BuysController());

		// 비동기 통신 api 제공 컨트롤러
		mapping.put(new RequestMap("/api/customer/list", "GET"), new ApiCustomerListController());
		mapping.put(new RequestMap("/api/customer", "POST"), new ApiCustomerPostController());
		mapping.put(new RequestMap("/api/customer", "GET"), new ApiCustomerGetController());
		mapping.put(new RequestMap("/api/product/list", "GET"), new ApiProductListController());
		mapping.put(new RequestMap("/api/product", "POST"), new ApiProdudctPostController());
		mapping.put(new RequestMap("/api/product/search", "PUT"), new ApiProductSearchController());
		mapping.put(new RequestMap("/api/buy/list", "GET"), new ApiBuyListController());
	}
	
	public static Controller getController(RequestMap key) {
		return mapping.get(key);
	}
}

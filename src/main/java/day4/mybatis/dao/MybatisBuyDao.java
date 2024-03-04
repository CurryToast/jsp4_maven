package day4.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import day4.mybatis.dto.BuyDto;
import mybatis.SqlSessionBean;

public class MybatisBuyDao {
	private SqlSessionFactory sessionFactory = SqlSessionBean.getSessionFactory();
	
	public MybatisBuyDao() {
		// 테스트 db 연결 : 나중에는 지웁니다.
		// SqlSession sqlSession = sessionFactory.openSession();
		// System.out.print("db 연결 및 sql 실행 객체 : " + sqlSession);
	}
	
	// SqlSession 객체 insert, update, delete, select 메소드는 SQL을 실행합니다.
	// 						ㄴ 첫번째 인자는 sql 매퍼 파일의 "namespace이름.id속성"
	//						ㄴ 두번째 인자는 sql 실행에 파라미터가 있으면 작성합니다.
	
	public List<BuyDto> selectAll() {
		SqlSession sqlSession = sessionFactory.openSession();
		List<BuyDto> list = sqlSession.selectList("tblbuy.selectAll");
		sqlSession.close();
		return list;
	}
	
	public List<BuyDto> selectById(String id) {
		SqlSession sqlSession = sessionFactory.openSession();
		List<BuyDto> list = sqlSession.selectList("tblbuy.selectById", id);
		sqlSession.close();
		return list;
	}
	
	// 마이바티스에서는 auto commit이 아닙니다.
	public int insert(BuyDto dto) {
		SqlSession sqlSession = sessionFactory.openSession();
		int result = sqlSession.insert("tblbuy.insert", dto);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	public int update(Map<String, Integer> map) {
		SqlSession sqlSession = sessionFactory.openSession();
		int result = sqlSession.update("tblbuy.update", map);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	public int delete(int buyidx) {
		SqlSession sqlSession = sessionFactory.openSession();
		int result = sqlSession.delete("tblbuy.delete", buyidx);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
}

package part02_mybatis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



/*
 * Template 클래스
 * 1. 개발자가 중복된 코들르 입력해야 하는 작업을 줄일 수 있도록 돕고 있다
 * 2. JDBC 뿐만 아니라 myBatis, JMS와 같은 다양한 기술에 대해 템플릿을 제공한다
 * 3. 예로 JDBC인 경우 JdbcTemplate 클래스를 제공하고 있으며, JdcbTemplate 클래스를 
 * 		사용함으로서 try~catch ~finally 및 커넥션 관리를 위한 중복된 코드를 줄이거나 없앨 수 있다
 * 
 * 
 */

@Repository
public class MemDAOImp implements MemDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public MemDAOImp() {
		
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<MemDTO> list() {
		
		
		return sqlSession.selectList("mem.all");
	}

	@Override
	public void insertMethod(MemDTO dto) {
		sqlSession.insert("mem.ins", dto);

	}

	@Override
	public MemDTO updateMethod(int num) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void updateMethod(MemDTO dto) {
		sqlSession.update("mem.upt", dto);
	}

	@Override
	public void deleteMethod(int num) {
		sqlSession.delete("mem.del", num);

	}

	@Override
	public MemDTO one(int num) {
		
		return sqlSession.selectOne("mem.one", num);
	}

	@Override
	public int countMethod() {
		// TODO Auto-generated method stub
		return 0;
	}
}

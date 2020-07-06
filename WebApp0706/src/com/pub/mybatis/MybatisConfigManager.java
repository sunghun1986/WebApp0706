package com.pub.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//XML 파일로부터 설정파일을 읽어, SqlSession 까지 반환하는
//공통 코드를 담당할 클래스 정의!

public class MybatisConfigManager {
	
	private static MybatisConfigManager instance;
	
	String path="com/pub/mybatis/config.xml";
	InputStream is;
	SqlSessionFactory factory;
	
	private MybatisConfigManager() {
		
		try {
			is = Resources.getResourceAsStream(path);
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//쿼리 수행 객체인 SqlSession 반환
	public SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		sqlSession = factory.openSession();
		return sqlSession;
	}
	
	//SqlSession 닫기
	public void closeSession(SqlSession sqlSession) {
		sqlSession.close();
	}
	
	public static MybatisConfigManager getInstance() {
		if(instance==null) {
			instance = new MybatisConfigManager();
		}
		return instance;
	}
	
}

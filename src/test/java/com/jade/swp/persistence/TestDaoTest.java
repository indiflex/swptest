package com.jade.swp.persistence;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class TestDaoTest {
	
	@Inject
	private TestDaoTest.TestDAO dao;

	@Test
	public void sdfasf() throws Exception {
		System.out.println("TTTTTTTTTTTTTTT");
		this.dao.list();
	}
	
	public interface TestDAO {
		void list() throws Exception;
	}
	
	@Repository
	public class TestDAOImpl implements TestDAO {

		@Override
		public void list() throws Exception {
			System.out.println("aaaaaaaaaaaaaa");
		}
		
	}

}

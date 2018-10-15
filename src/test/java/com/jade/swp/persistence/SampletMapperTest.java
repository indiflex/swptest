package com.jade.swp.persistence;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jade.swp.mapper.SampleMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class SampletMapperTest {
	
	@Inject
	private SampleMapper sampleMapper;

	@Test
	public void testGetTime() {
		String className = sampleMapper.getClass().getName();
		System.out.println("SampleMapper.class=" + className);
		assertTrue(StringUtils.startsWith(className, "com.sun.proxy."));
		System.out.println("SampleMapper.getTime=" + sampleMapper.getTime());
	}

}

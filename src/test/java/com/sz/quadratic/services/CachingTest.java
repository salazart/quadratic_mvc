package com.sz.quadratic.services;

import com.sz.quadratic.dao.interfaces.IDAO;
import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-for-caching-test.xml"})
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class CachingTest {
    private List<Quadratic> firstExpectedQuadratics;
    private List<Quadratic> secondExpectedQuadratics;

    @Autowired
    private ApplicationContext context;
    @Autowired
    private IDAO dao;
    @Autowired
    CacheManager cacheManager;
    @Autowired
    private IQuadraticService quadraticService;

    @Before
    public void setUp() throws QuadraticException {
        Quadratic q = new Quadratic(1d, 2d, 1d);
        firstExpectedQuadratics = Collections.singletonList(q);
        secondExpectedQuadratics = Arrays.asList(q, q);
        when(dao.getAll()).thenReturn(firstExpectedQuadratics, secondExpectedQuadratics);
    }

    @Test
    public void testWithOutCaching(){
        List<Quadratic> firstQuadratics = quadraticService.getAllQuadratics();
        List<Quadratic> secondQuadratics = quadraticService.getAllQuadratics();
        assertThat(firstQuadratics.hashCode(), is(equalTo(secondQuadratics.hashCode())));
    }

    @Test
    public void testCaching(){
        List<Quadratic> firstQuadratics = quadraticService.getAllQuadratics();
        quadraticService.clearCache();
        List<Quadratic> secondQuadratics = quadraticService.getAllQuadratics();
        assertThat(firstQuadratics.hashCode(), is(not(equalTo(secondQuadratics.hashCode()))));
    }

    @Test
    public void testInvocationCachingMethod() throws QuadraticException {
        List<Quadratic> firstResultQuadratics = quadraticService.getAllQuadratics();
        assertThat(firstResultQuadratics, is(firstExpectedQuadratics));

        // Second invocation should return cached value, *not* second (as set up above)
        firstResultQuadratics = quadraticService.getAllQuadratics();
        assertThat(firstResultQuadratics, is(firstExpectedQuadratics));

        // Verify repository method was invoked once
        verify(dao, times(1)).getAll();

        // Third invocation with different key is triggers the second invocation of the repo method
        quadraticService.clearCache();
        List<Quadratic> secondResultQuadratics = quadraticService.getAllQuadratics();
        assertThat(secondResultQuadratics, is(secondExpectedQuadratics));
    }

    @After
    public void after(){
        ((ConfigurableApplicationContext)context).close();
    }

}

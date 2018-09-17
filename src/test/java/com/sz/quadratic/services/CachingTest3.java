package com.sz.quadratic.services;

import com.sz.quadratic.dao.impl.GenericHibernateDAO;
import com.sz.quadratic.dao.interfaces.IDAO;
import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-caching-context3.xml"})
public class CachingTest3 {
    private List<Quadratic> firstExpectedQuadratics = Arrays.asList(new Quadratic(1, 2, 1));
    private List<Quadratic> secondExpectedQuadratics = Arrays.asList(new Quadratic(1, 2, 1));

    @Autowired
    private IDAO dao;
    @Autowired
    CacheManager manager;
    @Autowired
    @Qualifier(value = "quadraticService")
    private IQuadraticService quadraticService;

    @Test
    public void methodInvocationShouldBeCached() throws QuadraticException {
        when(dao.getAll()).thenReturn(firstExpectedQuadratics, secondExpectedQuadratics);

        List<Quadratic> firstResultQuadratics = quadraticService.getAllQuadratics();
        assertThat(firstExpectedQuadratics, is(firstResultQuadratics));

        // Second invocation should return cached value, *not* second (as set up above)
        firstResultQuadratics = quadraticService.getAllQuadratics();
        assertThat(firstExpectedQuadratics, is(firstResultQuadratics));
//
//        // Verify repository method was invoked once
        verify(quadraticService, times(2)).getAllQuadratics();
        verify(dao, times(1)).getAll();
//        assertThat(manager.getCache("quadratic").get(List<Quadratic>.getClass()), is(notNullValue()));
//
//        // Third invocation with different key is triggers the second invocation of the repo method
//        result = repo.findByEmail("bar");
//        assertThat(result, is(second));
    }

}

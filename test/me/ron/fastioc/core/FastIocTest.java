package me.ron.fastioc.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FastIocTest {

    @Before
    public void setup() {
        FastIoc.start();
    }
    /**
     * base inject
     */
    @Test
    public void testBaseInject() {
        me.ron.fastioc.core.fastioctest.base.Dao dao = FastIoc.fetch(me.ron.fastioc.core.fastioctest.base.Dao.class);
        me.ron.fastioc.core.fastioctest.base.Dao injectDao = FastIoc.fetch(me.ron.fastioc.core.fastioctest.base.Service.class).getDao();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(injectDao);
        Assert.assertEquals(dao, injectDao);
    }

    /**
     * base inject with name
     */
    @Test
    public void testBaseInjectWithName() {
        me.ron.fastioc.core.fastioctest.base.name.Dao dao = FastIoc.fetch(me.ron.fastioc.core.fastioctest.base.name.Dao.class);
        me.ron.fastioc.core.fastioctest.base.name.Dao injectDao = FastIoc.fetch(me.ron.fastioc.core.fastioctest.base.name.Service.class).getDao();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(injectDao);
        Assert.assertEquals(dao, injectDao);
    }

    /**
     * base inject with no name
     */
    @Test
    public void testBaseInjectWithNoName() {
        me.ron.fastioc.core.fastioctest.base.name.injectwithnoname.Dao dao = FastIoc.fetch(me.ron.fastioc.core.fastioctest.base.name.injectwithnoname.Dao.class);
        me.ron.fastioc.core.fastioctest.base.name.injectwithnoname.Dao injectDao = FastIoc.fetch(me.ron.fastioc.core.fastioctest.base.name.injectwithnoname.Service.class).getDao();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(injectDao);
        Assert.assertEquals(dao, injectDao);
    }

    /**
     * setter inject
     */
    @Test
    public void testSetterInject() {
        me.ron.fastioc.core.fastioctest.setter.Dao dao = FastIoc.fetch(me.ron.fastioc.core.fastioctest.setter.Dao.class);
        me.ron.fastioc.core.fastioctest.setter.Dao injectDao = FastIoc.fetch(me.ron.fastioc.core.fastioctest.setter.Service.class).getDao();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(injectDao);
        Assert.assertEquals(dao, injectDao);
    }

    /**
     * constructor inject
     */
    @Test
    public void testConstructorInject() {
        me.ron.fastioc.core.fastioctest.constructor.Dao dao = FastIoc.fetch(me.ron.fastioc.core.fastioctest.constructor.Dao.class);
        me.ron.fastioc.core.fastioctest.constructor.Dao injectDao = FastIoc.fetch(me.ron.fastioc.core.fastioctest.constructor.Service.class).getDao();
        Assert.assertNotNull(dao);
        Assert.assertNotNull(injectDao);
        Assert.assertEquals(dao, injectDao);
    }


}

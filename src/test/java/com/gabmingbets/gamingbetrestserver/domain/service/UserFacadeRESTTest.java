/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gabmingbets.gamingbetrestserver.domain.service;

import com.gabmingbets.gamingbetrestserver.domain.User;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andre
 */
public class UserFacadeRESTTest {
    
    public UserFacadeRESTTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of edit method, of class UserFacadeREST.
     
    @org.junit.Test
    public void testEdit_GenericType() throws Exception {
        System.out.println("edit");
        User entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserFacadeREST instance = (UserFacadeREST)container.getContext().lookup("java:global/classes/UserFacadeREST");
        instance.edit(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of remove method, of class UserFacadeREST.
     
    @org.junit.Test
    public void testRemove_GenericType() throws Exception {
        System.out.println("remove");
        User entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserFacadeREST instance = (UserFacadeREST)container.getContext().lookup("java:global/classes/UserFacadeREST");
        instance.remove(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    * */

    /**
     * Test of find method, of class UserFacadeREST.
     
    @org.junit.Test
    public void testFind_Object() throws Exception {
        System.out.println("find");
        Object id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserFacadeREST instance = (UserFacadeREST)container.getContext().lookup("java:global/classes/UserFacadeREST");
        User expResult = null;
        User result = instance.find(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    * */

    /**
     * Test of findRange method, of class UserFacadeREST.
     
    @org.junit.Test
    public void testFindRange_intArr() throws Exception {
        System.out.println("findRange");
        int[] range = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserFacadeREST instance = (UserFacadeREST)container.getContext().lookup("java:global/classes/UserFacadeREST");
        List<User> expResult = null;
        List<User> result = instance.findRange(range);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    * */

    /**
     * Test of count method, of class UserFacadeREST.
     
    @org.junit.Test
    public void testCount() throws Exception {
        System.out.println("count");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserFacadeREST instance = (UserFacadeREST)container.getContext().lookup("java:global/classes/UserFacadeREST");
        int expResult = 0;
        int result = instance.count();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    * */

    /**
     * Test of create method, of class UserFacadeREST.
     
    @org.junit.Test
    public void testCreate() throws Exception {
        System.out.println("create");
        User entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserFacadeREST instance = (UserFacadeREST)container.getContext().lookup("java:global/classes/UserFacadeREST");
        instance.create(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of edit method, of class UserFacadeREST.
     
    @org.junit.Test
    public void testEdit_Integer_User() throws Exception {
        System.out.println("edit");
        Integer id = null;
        User entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserFacadeREST instance = (UserFacadeREST)container.getContext().lookup("java:global/classes/UserFacadeREST");
        instance.edit(id, entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of remove method, of class UserFacadeREST.
     
    @org.junit.Test
    public void testRemove_Integer() throws Exception {
        System.out.println("remove");
        Integer id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserFacadeREST instance = (UserFacadeREST)container.getContext().lookup("java:global/classes/UserFacadeREST");
        instance.remove(id);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of find method, of class UserFacadeREST.
     
    @org.junit.Test
    public void testFind_Integer() throws Exception {
        System.out.println("find");
        Integer id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserFacadeREST instance = (UserFacadeREST)container.getContext().lookup("java:global/classes/UserFacadeREST");
        User expResult = null;
        User result = instance.find(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    * */

    /**
     * Test of findByName method, of class UserFacadeREST.
     
    @org.junit.Test
    public void testFindByName() throws Exception {
        System.out.println("findByName");
        String name = "a";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserFacadeREST instance = (UserFacadeREST)container.getContext().lookup("java:global/classes/UserFacadeREST");
        User expResult = null;
        User result = instance.findByName(name);
        assertEquals(result.getUserName(), name);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of findAll method, of class UserFacadeREST.
     
    @org.junit.Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserFacadeREST instance = (UserFacadeREST)container.getContext().lookup("java:global/classes/UserFacadeREST");
        List<User> expResult = null;
        List<User> result = instance.findAll();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of findRange method, of class UserFacadeREST.
     
    @org.junit.Test
    public void testFindRange_Integer_Integer() throws Exception {
        System.out.println("findRange");
        Integer from = null;
        Integer to = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserFacadeREST instance = (UserFacadeREST)container.getContext().lookup("java:global/classes/UserFacadeREST");
        List<User> expResult = null;
        List<User> result = instance.findRange(from, to);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of countREST method, of class UserFacadeREST.
     
    @org.junit.Test
    public void testCountREST() throws Exception {
        System.out.println("countREST");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserFacadeREST instance = (UserFacadeREST)container.getContext().lookup("java:global/classes/UserFacadeREST");
        String expResult = "";
        String result = instance.countREST();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}

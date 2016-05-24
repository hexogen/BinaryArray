package info.hexogen;

import java.util.Iterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryArrayTest {
    
    private BinaryArray<Integer> instance;

    @Before
    public void setUp() {
        instance = new BinaryArray<>();
        for (int i = 0; i < 10; i++) {
            instance.push(i);
        }
    }

    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of get method, of class BinaryArray.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int index = 0;
        int expResult = 0;
        int result = (int) instance.get(index);
        assertEquals(expResult, result);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetWithLowerBoundException() {
        System.out.println("get value with lowerbound exception");
        instance.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetWithUpperBoundException() {
        System.out.println("get value with upper exception");
        instance.get(10);
    }
    
    /**
     * Test of set method, of class BinaryArray.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        int index = 12;
        instance.set(index, 12);
        int expResult = 12;
        int result = (int) instance.get(index);
        assertEquals(expResult, result);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetWithLowerBoundException() {
        System.out.println("set value with lowerbound exception");
        instance.get(-1);
    }

    /**
     * Test of push method, of class BinaryArray.
     */
    @Test
    public void testPush() {
        System.out.println("push");
        Integer value = 1;
        instance.push(value);
    }

    /**
     * Test of delete method, of class BinaryArray.
     */
    @Test
    public void testDelete() {
        System.out.println("delete singele value");
        instance.delete(3);
        instance.delete(2);
        instance.delete(0);
        instance.delete(2);
        
        int index = 0;
        int expResult = 1;
        int result = (int) instance.get(index);
        assertEquals(expResult, result);
        
        index = 1;
        expResult = 4;
        result = (int) instance.get(index);
        assertEquals(expResult, result);
        assertEquals(expResult, result);
        
        index = 2;
        expResult = 6;
        result = (int) instance.get(index);
        assertEquals(expResult, result);
        
        index = 3;
        expResult = 7;
        result = (int) instance.get(index);
        assertEquals(expResult, result);
        assertEquals(expResult, result);
        
        index = 4;
        expResult = 8;
        result = (int) instance.get(index);
        assertEquals(expResult, result);
        assertEquals(expResult, result);
        
        index = 5;
        expResult = 9;
        result = (int) instance.get(index);
        assertEquals(expResult, result);
        assertEquals(expResult, result);
        
        instance.delete(4);
        
        index = 4;
        expResult = 9;
        result = (int) instance.get(index);
        assertEquals(expResult, result);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of delete method, of class BinaryArray.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteAll() {
        System.out.println("delete all values");
        try {
            //remove all values
            instance.delete(3);
            instance.delete(2);
            instance.delete(0);
            instance.delete(2);
            instance.delete(0);
            instance.delete(2);
            instance.delete(2);
            instance.delete(1);
            instance.delete(1);
            instance.delete(0);
        } catch (Error e) {
            fail();
        }
        
        instance.get(0);

    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteWithLowerBoundException() {
        System.out.println("delete singele value with lowerbound exception");
        instance.delete(-1);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteWithUpperBoundException() {
        System.out.println("delete singele value with upper exception");
        instance.delete(10);
    }

    /**
     * Test of iterator method, of class BinaryArray.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        
        Iterator<Integer> iterator = instance.iterator();
        for (int i = 0; i < 10; i++) {
            assertTrue(iterator.hasNext());
            assertEquals((int)iterator.next(), i);
        }
        assertFalse(iterator.hasNext());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIteratorWithException() {
        System.out.println("iterator out of bound exception");
        
        Iterator<Integer> iterator = instance.iterator(9);
        iterator.next();
        iterator.next(); // exception should be thrown
    }

    /**
     * Test of iterator with upper bound exception
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIteratorInitUpperBoundException() {
        System.out.println("iterator init out of bound exception");
        
        Iterator<Integer> iterator = instance.iterator(10);
    }

    /**
     * Test of iterator with negative start
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIteratorInitLowerBoundException() {
        System.out.println("iterator init out of bound exception");
        
        Iterator<Integer> iterator = instance.iterator(-1);
    }
    
    /**
     * Test of iterator with zero length array 
     */
    public void testIteratorInitZeroLength() {
        System.out.println("iterator init out of bound exception");
        
        BinaryArray<Integer> binaryArray = new BinaryArray<>();
        Iterator<Integer> iterator = binaryArray.iterator();
        
        assertFalse(iterator.hasNext());
    }

    /**
     * Test of iterator method, of class BinaryArray.
     */
    @Test
    public void testIterator_int() {
        System.out.println("iterator");
        Iterator<Integer> iterator = instance.iterator(5);
        for (int i = 5; i < 10; i++) {
            assertTrue(iterator.hasNext());
            assertEquals((int) iterator.next(), i);
        }
        assertFalse(iterator.hasNext());
    }
}
/*
* The MIT License (MIT)
* Copyright (c) 2016 Volodymyr Basarab
* 
* Permission is hereby granted, free of charge, to any person obtaining
* a copy of this software and associated documentation files (the "Software"),
* to deal in the Software without restriction, including without limitation
* the rights to use, copy, modify, merge, publish, distribute, sublicense,
* and/or sell copies of the Software, and to permit persons to whom
* the Software is furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included
* in all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
* IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
* DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
* TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
* OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

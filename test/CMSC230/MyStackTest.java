package CMSC230;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
public class MyStackTest {

    @Test
    void testPushAndPopWork(){
        MyStack<Integer> stack = new MyStack<Integer>(5);

        stack.push(new Integer(5));
        assertTrue(stack.pop().compareTo(5) == 0);
        assertTrue(stack.pop() == null);       
    }

    @Test
    void pushThrowsForIllegalArgument(){ 
        MyStack<Integer> stack = new MyStack<Integer>(5);
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {stack.push(null);}
        );
        assertEquals("typeObject cant be null", e.getMessage());
    }

    @Test
    void constructerThrowsForIllegalArgument(){
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {new MyStack<Integer>(-5);}
            );
            assertEquals("stackSize must be positive", e.getMessage());
    }

    @Test
    void testEmptyMethod(){
        MyStack<Integer> stack = new MyStack<Integer>(5);
        stack.push(new Integer(5));
        stack.push(new Integer(4));
        stack.push(new Integer(1));
        stack.push(new Integer(2));
        stack.push(new Integer(3));
        stack.empty();
        assertTrue(stack.pop() == null);
    }

    
}

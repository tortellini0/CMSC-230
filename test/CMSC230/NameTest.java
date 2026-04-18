package CMSC230;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
public class NameTest {
    @Test
    public void constructorThrowsForInvalidFirstName(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new Name(null, "last name");}
        );
        assertEquals("firstname cant be null",exception.getMessage());
    }

    @Test
    void constructorThrowsForInvalidLastName(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new Name("first name", null);}
        );
        assertEquals("lastname cant be null",exception.getMessage());
    }

    @Test
    void matchThrowsForInvalidOtherArgument(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
             () -> {new Name("firstName", "lastName").match(null);}
        );
        assertEquals("other cant be null",exception.getMessage());
    }

    @Test
    void MatchWorksCorrectly(){
        Name name1 = new Name("bbb", "bbb");
        Name name2 = new Name("bbb", "bbb");
        Name name3 = new Name("aaa", "aaa");
        assertTrue(name1.match(name2));
        assertFalse(name1.match(name3));
    }

    @Test
    void isLessThanThrowsForInvalidArgument(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {new Name("firstName", "lastName").isLessThan(null);}
        );
        assertEquals("other cant be null", exception.getMessage());
    }

    @Test
    void logicForIsLessThanWorks(){
        Name name1 = new Name("bbb", "bbb");
        Name name2 = new Name("bbb", "bbb");
        Name name3 = new Name("aaa", "aaa");
        Name name4 = new Name("ccc", "ccc");
        assertTrue(name3.isLessThan(name1));
        assertTrue(name2.isLessThan(name4));
        assertFalse(name1.isLessThan(name3));
        assertFalse(name1.isLessThan(name2));
    }
}

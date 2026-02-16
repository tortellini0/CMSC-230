package CMSC230;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class NameTest {
    public Name name1;
    public Name name2;
    public Name name3;
    public Name name4;
    @BeforeEach
    void beforeEach(){
        name1 = new Name("bbb", "bbb");
        name2 = new Name("bbb", "bbb");
        name3 = new Name("aaa", "aaa");
        name4 = new Name("ccc", "ccc");
    }
    @Test
    void constructorThrowsForInvalidFirstName(){
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
             () -> {name1.match(null);}
        );
        assertEquals("other cant be null",exception.getMessage());
    }

    @Test
    void MatchWorksCorrectly(){
        assertTrue(name1.match(name2));
        assertFalse(name1.match(name3));
    }

    @Test
    void isLessThanThrowsForInvalidArgument(){
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {name1.isLessThan(null);}
        );
        assertEquals("other cant be null", exception.getMessage());
    }

    @Test
    void logicForIsLessThanWorks(){
        boolean lessThanTrue = name3.isLessThan(name1);
        assertTrue(name3.isLessThan(name1));
        assertTrue(name2.isLessThan(name4));
        assertFalse(name1.isLessThan(name3));
        assertFalse(name1.isLessThan(name2));
    }
}

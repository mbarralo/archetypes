#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.util.Arrays;
import static org.hamcrest.CoreMatchers.hasItems;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelloTest {

    @Test
    public void testStuff() {
        assertThat(Arrays.asList("one", "two"), hasItems("one"));
    }    
}

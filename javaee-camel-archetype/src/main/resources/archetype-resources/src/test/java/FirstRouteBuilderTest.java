#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import javax.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.ServiceStatus;
import org.apache.camel.cdi.Uri;
import org.apache.camel.test.cdi.Beans;
import org.apache.camel.test.cdi.CamelCdiRunner;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

@RunWith(CamelCdiRunner.class)
@Beans(classes = {TestRoute.class})
public class FirstRouteBuilderTest {

    @Inject
    CamelContext context;

    @Test
    public void test() {
        assertThat("Camel context status is incorrect!",
                context.getStatus(),
                is(equalTo(ServiceStatus.Started)));
    }

    @Test
    public void test(@Uri("direct:foo") ProducerTemplate producer) {
        producer.sendBody("bar");
    }

}

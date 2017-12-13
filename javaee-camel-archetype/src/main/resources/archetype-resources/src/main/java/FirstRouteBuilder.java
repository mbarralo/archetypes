#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

@ContextName("first-context")
public class FirstRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://timer1?period=1000")
                .log(">> ping route");
    }

}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.apache.camel.builder.RouteBuilder;

class TestRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:foo").log("received: ${symbol_dollar}{body}").to("mock:bar");
    }
}

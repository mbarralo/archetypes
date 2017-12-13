#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import javax.enterprise.context.ApplicationScoped;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.impl.DefaultCamelContext;

@ApplicationScoped
@ContextName("first-context")
public class FirstCamelContext extends DefaultCamelContext {
    
}

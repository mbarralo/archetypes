#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.st;

import java.io.StringReader;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import ${package}.rest.hello.HelloResource;
import ${package}.rest.RestConfiguration;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class HelloServiceST {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addClasses(RestConfiguration.class, HelloResource.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @ArquillianResource
    URL base;

    @Test
    public void invokeRestEndpoint() throws Exception {

        final URL url = new URL(base, "rest/hello");
        final WebTarget target = ClientBuilder.newClient().target(url.toExternalForm());
        final Response response = target.path("{name}").resolveTemplate("name", "john").request().get();

        assertThat(response.getStatus(), is(200));
        assertThat(readJsonField(response, "msg"), is("hello john"));

    }

    private String readJsonField(Response response, String field) {
        JsonReader reader = Json.createReader(new StringReader(response.readEntity(String.class)));
        JsonObject jsonObject = reader.readObject();
        return jsonObject.getString(field);
    }
}

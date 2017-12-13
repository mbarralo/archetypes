#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.hello;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

    @GET
    @Path("{name}")
    public Response sayHello(@PathParam("name") String name) {
        
        JsonObject jsonObject = Json.
                createObjectBuilder().
                add("msg", "hello " + name).
                build();
        
        return Response.ok(jsonObject).build();
    }
}

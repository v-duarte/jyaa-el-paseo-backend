package rest;
import org.glassfish.jersey.server.ResourceConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(servers = {
		@Server(url = "http://localhost:8080/Trabajo/api")
})
public class JerseyApplication extends ResourceConfig {
	public JerseyApplication() {
		register(new Binder());
		packages("rest.recursos");
		packages("rest");
	}
}

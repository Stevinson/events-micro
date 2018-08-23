package application;

import application.rest.v1.DatabaseResource;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * JAX-RS application that specifies the REST end points.
 */
public class ApplicationConfig extends Application {
	@Override
	public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();

        classes.add(DatabaseResource.class);

        return classes;
	}	
}

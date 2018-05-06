package aiss.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import aiss.api.resources.BibliotecaResource;
import aiss.api.resources.LibroResource;

public class BibliotecasApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	// Loads all resources that are implemented in the application
	// so that they can be found by RESTEasy.
	public BibliotecasApplication() {

		singletons.add(BibliotecaResource.getInstance());
		singletons.add(LibroResource.getInstance());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}

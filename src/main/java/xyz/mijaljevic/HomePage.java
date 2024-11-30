package xyz.mijaljevic;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Home page of the simple SSO.
 * 
 * @author karlo
 * 
 * @since 11.2024
 * 
 * @version 1.0
 */
@Path("/")
@PermitAll
public final class HomePage
{
	@Inject
	private Template homePage;

	private static final String TITLE_KEY = "title";

	private static final String TITLE_VALUE = "Simple SSO";

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response auth()
	{
		TemplateInstance instance = homePage.data(TITLE_KEY, TITLE_VALUE);

		return Response.ok().entity(instance).build();
	}
}

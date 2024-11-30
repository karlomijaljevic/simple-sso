package xyz.mijaljevic;

import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
public final class Auth
{
	@POST
	@Path("sign-in")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signIn(JsonObject request)
	{
		JsonObject response = new JsonObject();

		String email = request.getString("email");
		String password = request.getString("password");

		Log.info("User \"" + email + "\" is trying to sign in.");

		if (email.isBlank() || password.isBlank())
		{
			response.put("success", false);
			response.put("message", "Email and password shoud not be empty!");
			return Response.status(Status.BAD_REQUEST).entity(response).build();
		}
		
		// TODO: Handle user lookup and validation

		Log.info("User \"" + email + "\" signed in successfully.");

		response.put("success", true);
		response.put("message", "User signed in successfully.");

		return Response.accepted().entity(response).build();
	}
}

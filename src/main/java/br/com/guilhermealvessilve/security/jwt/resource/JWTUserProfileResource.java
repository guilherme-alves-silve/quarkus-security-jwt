package br.com.guilhermealvessilve.security.jwt.resource;

import br.com.guilhermealvessilve.security.jwt.data.UserInfo;
import br.com.guilhermealvessilve.security.jwt.service.JWTService;
import org.jboss.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/jwt")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class JWTUserProfileResource {

    private static final Logger LOGGER = Logger.getLogger(JWTUserProfileResource.class);

    private final JWTService jwtService;

    @Inject
    public JWTUserProfileResource(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @GET
    @Path("/user")
    @RolesAllowed({"user", "admin"})
    public UserInfo getUserInfo() {
        return new UserInfo(
                "Name",
                "User name",
                "12/08/2000"
        );
    }

    @GET
    @Path("/admin")
    @RolesAllowed("admin")
    public UserInfo getAdminInfo() {
        return new UserInfo(
                "Admin",
                "Admin name",
                "07/02/1988"
        );
    }

    @POST
    @PermitAll
    @Path("/profile")
    public Response register(
            @QueryParam("email") final String email,
            @QueryParam("username") final String username,
            @QueryParam("nickname") final String nickname,
            @QueryParam("role") final String role
    ) {
        try {
            final var token = jwtService.generateToken(email, username, nickname, role);
            return Response.ok(token).build();
        } catch (final Exception ex) {
            LOGGER.error("An error has occurred: " + ex.getMessage(), ex);
            return Response.serverError().build();
        }
    }
}

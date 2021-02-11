package com.smarthomelightingsystem.controller.filters;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

/**
 * This class filters all requests, and makes sure that the client is logged in.
 *
 * Requests should contain their token in their header under the "Bearer" authentication scheme. If altering the header
 * in this way is not possible, the token can be passed as a query parameter instead.
 *
 * @author Jasper van Amerongen
 * @author Adamo Mariani
 *
 */
@Provider
@PreMatching
public class AuthFilter implements ContainerRequestFilter {

    private static final String CLIENT_ID = "127840733535-1pnq9jfs5v0f2b1tbbpjbk0dt864cso3.apps.googleusercontent.com";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Override
    public void filter(ContainerRequestContext ctx) {

		String authorizationHeader = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);
		String idTokenString = "";

		if (authorizationHeader == null) idTokenString = ctx.getUriInfo().getQueryParameters().getFirst("id_token");
		else idTokenString = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

		if (!valid(idTokenString)) {

			ctx.abortWith(Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("No valid token was provided!")
					.build());
			return;

		}

    }

    private boolean valid(String idTokenString) {

        if (idTokenString == null || idTokenString.equals("")) return false;

        GoogleIdToken idToken = null;
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        try { idToken = verifier.verify(idTokenString); }
        catch (Exception e) { e.printStackTrace(); }

        return idToken != null;

    }

}
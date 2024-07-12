package com.example.ms.users.exmap;

import com.example.ms.users.def.ex.MSUsersException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MSUsersExceptionMapper implements ExceptionMapper<MSUsersException> {

	@Override
	public Response toResponse(MSUsersException exception) {
		return Response.status(400).entity(exception.getMessage()).type("text/plain")
				.header("Exception-Type", exception.getClass().getSimpleName()).build();
	}

}

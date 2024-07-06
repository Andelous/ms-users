package com.example.ms.users.exmap;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.ms.users.def.ex.MSUsersException;

@Provider
public class MSUsersExceptionMapper implements ExceptionMapper<MSUsersException> {

	@Override
	public Response toResponse(MSUsersException exception) {
		return Response.status(400).entity(exception.getMessage()).type("text/plain")
				.header("Exception-Type", exception.getClass().getSimpleName()).build();
	}

}

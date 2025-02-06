package com.example.taskmanager.exception;

import com.example.taskmanager.model.ErrorResponse;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


/**
 * Whenever exception occurs in your API, Jakarta EE will automatically call this class,
 * this formats the error response into JSON structure.
 * The API will retur a proper HTTP status code with meaningful error message.
 */
@Provider // registers this class as a global exception handler
public class CustomExceptionMapper implements ExceptionMapper<Exception>{

	@Override
	public Response toResponse(Exception exception) {
		// TODO Auto-generated method stub
		ErrorResponse errorResponse;
		
		//If it is a WebApplicationException , we use its response and status code.
		if(exception instanceof WebApplicationException) {
			WebApplicationException webApplicationException = (WebApplicationException) exception;
			errorResponse = new ErrorResponse(webApplicationException.getResponse().getStatus(), webApplicationException.getMessage(), "A known error occured");
			return Response.status(webApplicationException.getResponse().getStatus())
					.entity(errorResponse)
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
		errorResponse =  new ErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), exception.getMessage(), "an unexpected error occured");
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(errorResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}

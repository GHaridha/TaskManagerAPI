package com.example.taskmanager.api;

import java.util.List;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskManagerRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/tasks") // base url path for this resource class
public class TaskResource {

	@Inject
	private TaskManagerRepository taskManagerRepository;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> getAllTasks() {
	    return taskManagerRepository.findAllTasks();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTask(Task task) {
		taskManagerRepository.save(task);
		return Response.status(Status.CREATED).entity(task).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Long id) {
		Task task = taskManagerRepository.findById(id);
		if(task == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(task).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		taskManagerRepository.remove(id);
		return Response.status(Status.NO_CONTENT).build();
	}
}

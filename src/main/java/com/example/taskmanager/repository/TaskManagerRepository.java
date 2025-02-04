package com.example.taskmanager.repository;

import java.util.List;

import com.example.taskmanager.model.Task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


public class TaskManagerRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	/*	If entity already exist it will update
	 *  else it will save new entity 
	 * */
	public void save(Task task) {
		entityManager. merge(task);
	}
	
	public Task findById(Long id){
		return entityManager.find(Task.class, id);
	}
	
	public void remove(Long id) {
		Task task = findById(id);
		if(task != null) {
			entityManager.remove(task);
		}
	}
	
	public List<Task> findAllTasks(){
		return entityManager.createQuery("Select t from Task t", Task.class).getResultList();
	}
}

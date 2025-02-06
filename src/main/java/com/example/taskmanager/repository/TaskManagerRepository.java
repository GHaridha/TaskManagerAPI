package com.example.taskmanager.repository;

import java.util.List;

import com.example.taskmanager.model.Task;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class TaskManagerRepository {

	@PersistenceContext(unitName = "task-manager-pu")  // ✅ This should match persistence.xml
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
	
	public List<Task> findAllTasks(int pageNum, int  pageSize){
		return entityManager.createQuery("Select t from Task t", Task.class)
				.setFirstResult(pageNum * pageSize)   // Set the first result based on page number and size
                .setMaxResults(pageSize)  
				.getResultList();
	}
}

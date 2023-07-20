package com.akentech.crudreldemo.dao;

import com.akentech.crudreldemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class AppDAOImpl implements AppDAO{


    // define field for entity manager

    private EntityManager entityManager;

    // inject entity manager using constructor injection

    @Autowired
    public AppDAOImpl (EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    // Finding instructor by id
    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    // Deleting instructor by id
    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        // Retrieve the instructor

        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // Delete the instructor

        entityManager.remove(tempInstructor);
    }
}

package com.akentech.crudreldemo.dao;

import com.akentech.crudreldemo.entity.Instructor;
import com.akentech.crudreldemo.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}

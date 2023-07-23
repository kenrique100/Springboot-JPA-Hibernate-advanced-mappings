package com.akentech.crudreldemo;

import com.akentech.crudreldemo.dao.AppDAO;
import com.akentech.crudreldemo.entity.Instructor;
import com.akentech.crudreldemo.entity.InstructorDetail;
import org.hibernate.dialect.SybaseSqmToSqlAstConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudreldemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudreldemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

			//createInstructor(appDAO);

			//findInstructor(appDAO);

            //deleteInstructor(appDAO);

			//findInstructorDetail(appDAO);

			deleteInstructorDetail(appDAO);
		};
	}

	private void deleteInstructorDetail(AppDAO appDAO) {

		int theId = 4;
		System.out.println("Deleting instructor detail id: " + theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Done!!!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		// get  the instructor detail object
		int theId = 3;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		// print tha associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("Done!!!");
	}

	private void deleteInstructor(AppDAO appDAO) {

        int theId = 2;
        System.out.println("Deleting instructor id: " + theId);

        appDAO.deleteInstructorById(theId);

        System.out.println("Done!!!");
    }

    // Finding the instructor by id
	private void findInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// create the instructor

		Instructor tempInstructor =
				new  Instructor("Awah", "Kenrique", "ngwakenri2016@gmail.com");
		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"https://www.youtube.com/",
						"Love to code!!!");
/*
        // create the instructor
        Instructor tempInstructor =
                new  Instructor("Nyam", "Juzette", "juzette681@gmail.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail =
                new InstructorDetail(
                        "https://www.youtube.com/",
                        "Love to code!!!");*/

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		// NOTE: this will ALSO save the details object
		// because of CascadeType.ALL
		//
		System.out.println(" Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!!!");
	}

}

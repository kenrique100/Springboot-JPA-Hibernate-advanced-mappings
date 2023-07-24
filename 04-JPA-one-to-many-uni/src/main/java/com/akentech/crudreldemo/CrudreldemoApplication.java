package com.akentech.crudreldemo;

import com.akentech.crudreldemo.dao.AppDAO;
import com.akentech.crudreldemo.entity.Course;
import com.akentech.crudreldemo.entity.Instructor;
import com.akentech.crudreldemo.entity.InstructorDetail;
import com.akentech.crudreldemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudreldemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudreldemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

			// createCourseAndReviews(appDAO);

			// retrieveCourseAndReviews(appDAO);

			deleteCourseAndReviews(appDAO);

		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int theId = 11;

		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!!!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the course and reviews
		int theId = 11;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println(tempCourse);

		// print the reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Done!!!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		// create  a course
		Course tempCourse = new Course("COD - How To Score One Million Points");

		// add some reviews
		tempCourse.addReview(new Review("Great Performance ... Loved It!"));
		tempCourse.addReview(new Review("You Did Great, Job well done."));
		tempCourse.addReview(new Review("You Are Now A Professional, You are the best"));

		// save the course ... and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!!!");

	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!!!");
	}

	private void updateCourse(AppDAO appDAO) {

		int theId = 10;

		// find the course

		System.out.println("Finding the course id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		// update the course
		System.out.println("Updating course id: " + theId);
		tempCourse.setTitle("Car mechanic");

		appDAO.update(tempCourse);

		System.out.println("Done!!!");
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 4;
				// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// update the instructor
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("Bruno");
		tempInstructor.setEmail("brunongwa211@gmail.com");

		appDAO.update(tempInstructor);

		System.out.println("Done!!!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId = 4;

		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("Done!!!!");

	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 4;
		// find the instructor
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// associate the objects
		tempInstructor.setCourses(courses);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("Done!!!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId = 4;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("Done!!!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {


		// create the instructor
		Instructor tempInstructor =
				new  Instructor("Ngwa", "Fru", "frungwa@gmail.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"https://www.youtube.com/",
						"Love to fight!!!");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create so,e courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");
        Course tempCourse3 = new Course("The Java Coding Masterclass");

		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
        tempInstructor.add(tempCourse3);

		// saving the instructor
		//
		// NOTE: this will ALSO save the courses
		// because of CascadeType.PERSIST
		//
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The Courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done!!!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting instructor detail id: " + theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Done!!!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		// get  the instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		// print tha associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("Done!!!");
	}

	private void deleteInstructor(AppDAO appDAO) {

        int theId = 4;
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
						"https://www.youtube.com/watch?v=XGf2GcyHPhc&list=WL&index=3&t=11533s",
						"Love to code!!!");
    /*  // create the instructor
        Instructor tempInstructor =
                new  Instructor("Nyam", "Juzette", "juzette681@gmail.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail =
                new InstructorDetail(
                        "https://www.youtube.com/",
                        "Make-up artist!!!");

	// create the instructor
		Instructor tempInstructor =
				new  Instructor("Fru", "Ngwa", "ngwafru67@gmail.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"https://www.youtube.com/watch?v=28hYUZMufDg&list=RD28hYUZMufDg&index=1",
						"Software beginner!!!");

*/
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

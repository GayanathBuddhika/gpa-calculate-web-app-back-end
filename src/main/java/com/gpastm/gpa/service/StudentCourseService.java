package com.gpastm.gpa.service;

import com.gpastm.gpa.model.StudentCourse;

public interface StudentCourseService {

   StudentCourse findStudentCourseById(String studentCourseId );

StudentCourse findStudentCourseByepNumberCourseId(String epnumber, String courseId);
}

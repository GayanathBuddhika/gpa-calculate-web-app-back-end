package com.gpastm.gpa.service;

import java.util.List;

import com.gpastm.gpa.model.StudentCourse;

public interface StudentCourseService {

   StudentCourse findStudentCourseById(String studentCourseId );

   StudentCourse findStudentCourseByepNumberCourseId(String epnumber, String courseId);

void saveStudentCourse(StudentCourse studentCourse);

List<StudentCourse> findCourseByStudentId(String studentId);
}

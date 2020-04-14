package com.gpastm.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gpastm.gpa.model.Result;

public interface ResultRepository extends JpaRepository<Result, String>{

	@Query("select distinct r.examName from Result r")	
	List<String> findAllExamName();

	@Query("FROM Result r WHERE r.studentCourse.degreeCourse.user.id=:lecid AND r.examName=:exam Group By r.studentCourse.degreeCourse.course.id")
	List<Result> findresultForLecture(@Param("lecid") String lecid,@Param("exam") String exam);

//	List<Result> findByStudentCourse_student_epNumber(String epNumber);
	@Query("FROM Result r WHERE r.studentCourse.student.epNumber=:epNumber2 AND r.lecApproval=1 AND r.depHedApproval=1 AND r.deenApproval=1")
	List<Result> findByresultNumber(@Param("epNumber2") String epNumber);
	//List<Result> findAllByStudent_EpNumber(String epNumber);
	
//	@Query("select new com.pulsebeatv2rest.modelConverter.DptNameSurveyUuid(s.id,s.name, p.uuid, s.selectionType,s.surveyType.name) "
//    		+ "FROM PreferredUrl p inner join p.survey s "
//    		+ "WHERE p.survey = s.id AND s.enabled=:enabled AND s.surveyFor=:surveyFor "
//    		+ "AND s.assignDeptToBranch.id=:assignDeptToBranchId "
//    		+ "AND convert(s.endDateTime,datetime)>:now  "
//    		+ "AND s.id IN (select sbd.survey from SbdRelation sbd INNER JOIN sbd.assignDeptToBranch abd "
//    		+ "WHERE sbd.assignDeptToBranch=abd.id) Group By s.id")
//	List<DptNameSurveyUuid> findOngoingSurvey(@Param("enabled") boolean enabled, @Param("now") LocalDateTime now,@Param("assignDeptToBranchId") String assignDeptToBranchId,@Param("surveyFor") String surveyFor);

    
	

}

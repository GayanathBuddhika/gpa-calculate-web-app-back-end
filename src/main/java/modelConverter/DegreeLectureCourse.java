package modelConverter;

import javax.persistence.Entity;

import com.gpastm.gpa.model.Course;
import com.gpastm.gpa.model.DegreeProgram;
import com.gpastm.gpa.model.Lecture;

import lombok.Data;

@Data
@Entity
public class DegreeLectureCourse {
	
	private DegreeProgram degree;
	private Lecture lectuer;
	private Course course;
	

}

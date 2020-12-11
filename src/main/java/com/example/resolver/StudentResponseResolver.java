package com.example.resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.enums.SubjectNameFilter;
import com.example.response.StudentResponse;
import com.example.response.SubjectResponse;

@Service
public class StudentResponseResolver implements GraphQLResolver<StudentResponse>{
	
	public List<SubjectResponse> getLearningSubjects(StudentResponse studentResponse, SubjectNameFilter subjectNameFilter){
		
		List<SubjectResponse> learningSubjects = new ArrayList<SubjectResponse>();
		
		if (studentResponse.getStudent().getLearningSubjects() != null) {
			
			learningSubjects.addAll(studentResponse.getStudent().getLearningSubjects().stream()
					.filter(s -> subjectNameFilter.equals(SubjectNameFilter.All)
							|| subjectNameFilter.name().equals(s.getSubjectName()))
					.map(s -> new SubjectResponse(s)).collect(Collectors.toList()));

		}
		
		return learningSubjects;
	}
	
	public String getFullName(StudentResponse studentResponse) {
		return studentResponse.getFirstName() + " " + studentResponse.getLastName();
	}

}

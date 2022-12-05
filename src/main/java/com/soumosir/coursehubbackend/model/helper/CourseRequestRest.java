package com.soumosir.coursehubbackend.model.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumosir.coursehubbackend.model.Content;
import com.soumosir.coursehubbackend.model.Course;
import com.soumosir.coursehubbackend.model.Exam;
import com.soumosir.coursehubbackend.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestRest {

    private Long id;
    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String code;
    @NotEmpty
    @NotNull
    private String description;
    @NotEmpty
    @NotNull
    private String instructor;

    @NotEmpty
    @NotNull
    private Long startTime;
    @NotEmpty
    @NotNull
    private Long endTime;
    @NotEmpty
    @NotNull
    private Long totalSeats;

    private List<Content> contents;
    private String exams;


    public Course buildCourse(CourseService courseService){
        Course course = new Course();
        course.setName(name);
        course.setCode(code);
        course.setDescription(description);
        course.setTotalSeats(totalSeats);
        course.setInstructor(instructor);
        course.setStartTime(new Timestamp(startTime));
        course.setEndTime(new Timestamp(endTime));
//        course.setContents(parseContent(courseService));
        return course;
    }

//    private Collection<Content> parseContent(CourseService courseService) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            ContentList contentList = objectMapper.readValue(contents, ContentList.class);
//             contentList.getContents().forEach(System.out::println);
//             Collection<Content> result = new ArrayList<>();
//             contentList.getContents().forEach(c -> {
//                 Content content = new Content();
//                 content.setName(c.name);
//                 content.setType(c.type);
//                 content.setUrl(c.url);
//                 result.add(courseService.saveContent(content));
//             });
//             return result;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void validate() throws ValidationException {
        if(name==null || (name!=null && (name.length()<3|| name.length()>20))){
            log.error("name is less than 3 letters or more than 20 : "+ name );
            throw new ValidationException("name is less than 3 letters or more than 20 : "+ name );
        }


        if(!code.matches("^[a-zA-Z0-9]+$")){
            log.error("code is should be alphanumeric A-Z , a-z or 0-9 : "+ code );
            throw new ValidationException("Username is should be alphanumeric A-Z , a-z or 0-9 : "+code);
        }

        if(code==null ||(code!=null && (code.length()<3 || code.length()>30))){
            log.error("code is not valid should be more than 3 and les than 30 : "+ code );
            throw new ValidationException("code is not valid should be more than 3 and les than 30 : "+code);
        }



    }
}


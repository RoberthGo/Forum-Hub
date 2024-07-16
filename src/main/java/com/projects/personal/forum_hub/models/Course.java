package com.projects.personal.forum_hub.models;

import com.projects.personal.forum_hub.dto.course.DTOCourse;
import com.projects.personal.forum_hub.dto.course.DTOCourseAnswer;
import com.projects.personal.forum_hub.dto.course.DTOUpdateCourse;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "courses")
@Entity(name = "Course")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    @Setter
    private boolean publicCourse;
    private Long author_id;

    public Course(DTOCourse course, Long idAuthor) {
        this.name = course.name();
        this.category = course.category();
        this.publicCourse = false;
        this.author_id = idAuthor;
    }

    public void updateData(DTOUpdateCourse course){
        if(course.name() != null){
            this.name = course.name();
        }
        if(course.category() != null){
            this.category = course.category();
        }
        if(course.publicCourse() != this.publicCourse){
            this.publicCourse = course.publicCourse();
        }
    }

    public boolean getPublicCourse(){
        return  this.publicCourse;
    }

}

package com.soumosir.coursehubbackend.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumosir.coursehubbackend.model.Content;
import com.soumosir.coursehubbackend.model.Course;
import com.soumosir.coursehubbackend.model.Exam;
import com.soumosir.coursehubbackend.repo.AppUserRepo;
import com.soumosir.coursehubbackend.repo.ContentRepo;
import com.soumosir.coursehubbackend.repo.CourseRepo;
import com.soumosir.coursehubbackend.repo.ExamRepo;
import com.soumosir.coursehubbackend.repo.ResultRepo;
import com.soumosir.coursehubbackend.service.CourseService;
import com.soumosir.coursehubbackend.service.CourseServiceImplementation;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CourseController.class})
@ExtendWith(SpringExtension.class)
@Disabled
class CourseControllerTest {
    @Autowired
    private CourseController courseController;

    @MockBean
    private CourseService courseService;

    /**
     * Method under test: {@link CourseController#saveExam(Exam, org.springframework.security.core.Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveExam() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalStateException: No current ServletRequestAttributes
        //       at org.springframework.util.Assert.state(Assert.java:76)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.getCurrentRequest(ServletUriComponentsBuilder.java:179)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath(ServletUriComponentsBuilder.java:147)
        //       at com.soumosir.coursehubbackend.api.CourseController.saveExam(CourseController.java:36)
        //   In order to prevent saveExam(Exam, Authentication)
        //   from throwing IllegalStateException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveExam(Exam, Authentication).
        //   See https://diff.blue/R013 to resolve this issue.

        CourseController courseController = new CourseController(new CourseServiceImplementation(mock(CourseRepo.class),
                mock(ExamRepo.class), mock(ContentRepo.class), mock(AppUserRepo.class), mock(ResultRepo.class)));

        Exam exam = new Exam();
        exam.setAnswers("Answers");
        exam.setDuration(1L);
        exam.setId(123L);
        exam.setName("Name");
        exam.setQuestions("Questions");
        exam.setType("Type");
        exam.setUser("User");
        courseController.saveExam(exam, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CourseController#saveExam(Exam, org.springframework.security.core.Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveExam2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalStateException: No current ServletRequestAttributes
        //       at org.springframework.util.Assert.state(Assert.java:76)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.getCurrentRequest(ServletUriComponentsBuilder.java:179)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath(ServletUriComponentsBuilder.java:147)
        //       at com.soumosir.coursehubbackend.api.CourseController.saveExam(CourseController.java:36)
        //   In order to prevent saveExam(Exam, Authentication)
        //   from throwing IllegalStateException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveExam(Exam, Authentication).
        //   See https://diff.blue/R013 to resolve this issue.

        Course course = new Course();
        course.setCode("Code");
        course.setContents(new ArrayList<>());
        course.setDescription("The characteristics of someone or something");
        course.setEndTime(mock(Timestamp.class));
        course.setEnrolledUsers(new ArrayList<>());
        course.setExams(new ArrayList<>());
        course.setId(123L);
        course.setInstructor("Instructor");
        course.setName("Name");
        course.setRemainingSeats(1L);
        course.setStartTime(mock(Timestamp.class));
        course.setTotalSeats(1L);
        course.setWishlistUsers(new ArrayList<>());
        CourseRepo courseRepo = mock(CourseRepo.class);
        when(courseRepo.findById((Long) any())).thenReturn(Optional.of(course));

        Content content = new Content();
        content.setDescription("The characteristics of someone or something");
        content.setId(123L);
        content.setName("Name");
        content.setType("Type");
        content.setUrl("https://example.org/example");
        content.setUsername("janedoe");
        ContentRepo contentRepo = mock(ContentRepo.class);
        when(contentRepo.findById((Long) any())).thenReturn(Optional.of(content));

        CourseServiceImplementation courseServiceImplementation = new CourseServiceImplementation(courseRepo,
                mock(ExamRepo.class), contentRepo, mock(AppUserRepo.class), mock(ResultRepo.class));
        courseServiceImplementation.addContent(123L, 123L);
        CourseController courseController = new CourseController(courseServiceImplementation);

        Exam exam = new Exam();
        exam.setAnswers("Answers");
        exam.setDuration(1L);
        exam.setId(123L);
        exam.setName("Name");
        exam.setQuestions("Questions");
        exam.setType("Type");
        exam.setUser("User");
        courseController.saveExam(exam, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CourseController#postContent(Content, org.springframework.security.core.Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPostContent() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalStateException: No current ServletRequestAttributes
        //       at org.springframework.util.Assert.state(Assert.java:76)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.getCurrentRequest(ServletUriComponentsBuilder.java:179)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath(ServletUriComponentsBuilder.java:147)
        //       at com.soumosir.coursehubbackend.api.CourseController.postContent(CourseController.java:83)
        //   In order to prevent postContent(Content, Authentication)
        //   from throwing IllegalStateException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   postContent(Content, Authentication).
        //   See https://diff.blue/R013 to resolve this issue.

        CourseController courseController = new CourseController(new CourseServiceImplementation(mock(CourseRepo.class),
                mock(ExamRepo.class), mock(ContentRepo.class), mock(AppUserRepo.class), mock(ResultRepo.class)));

        Content content = new Content();
        content.setDescription("The characteristics of someone or something");
        content.setId(123L);
        content.setName("Name");
        content.setType("Type");
        content.setUrl("https://example.org/example");
        content.setUsername("janedoe");
        courseController.postContent(content, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CourseController#postContent(Content, org.springframework.security.core.Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPostContent2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalStateException: No current ServletRequestAttributes
        //       at org.springframework.util.Assert.state(Assert.java:76)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.getCurrentRequest(ServletUriComponentsBuilder.java:179)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath(ServletUriComponentsBuilder.java:147)
        //       at com.soumosir.coursehubbackend.api.CourseController.postContent(CourseController.java:83)
        //   In order to prevent postContent(Content, Authentication)
        //   from throwing IllegalStateException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   postContent(Content, Authentication).
        //   See https://diff.blue/R013 to resolve this issue.

        Course course = new Course();
        course.setCode("Code");
        course.setContents(new ArrayList<>());
        course.setDescription("The characteristics of someone or something");
        course.setEndTime(mock(Timestamp.class));
        course.setEnrolledUsers(new ArrayList<>());
        course.setExams(new ArrayList<>());
        course.setId(123L);
        course.setInstructor("Instructor");
        course.setName("Name");
        course.setRemainingSeats(1L);
        course.setStartTime(mock(Timestamp.class));
        course.setTotalSeats(1L);
        course.setWishlistUsers(new ArrayList<>());
        CourseRepo courseRepo = mock(CourseRepo.class);
        when(courseRepo.findById((Long) any())).thenReturn(Optional.of(course));

        Content content = new Content();
        content.setDescription("The characteristics of someone or something");
        content.setId(123L);
        content.setName("Name");
        content.setType("Type");
        content.setUrl("https://example.org/example");
        content.setUsername("janedoe");
        ContentRepo contentRepo = mock(ContentRepo.class);
        when(contentRepo.findById((Long) any())).thenReturn(Optional.of(content));

        CourseServiceImplementation courseServiceImplementation = new CourseServiceImplementation(courseRepo,
                mock(ExamRepo.class), contentRepo, mock(AppUserRepo.class), mock(ResultRepo.class));
        courseServiceImplementation.addContent(123L, 123L);
        CourseController courseController = new CourseController(courseServiceImplementation);

        Content content1 = new Content();
        content1.setDescription("The characteristics of someone or something");
        content1.setId(123L);
        content1.setName("Name");
        content1.setType("Type");
        content1.setUrl("https://example.org/example");
        content1.setUsername("janedoe");
        courseController.postContent(content1, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CourseController#postCourse(Course, org.springframework.security.core.Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPostCourse() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalStateException: No current ServletRequestAttributes
        //       at org.springframework.util.Assert.state(Assert.java:76)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.getCurrentRequest(ServletUriComponentsBuilder.java:179)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath(ServletUriComponentsBuilder.java:147)
        //       at com.soumosir.coursehubbackend.api.CourseController.postCourse(CourseController.java:90)
        //   In order to prevent postCourse(Course, Authentication)
        //   from throwing IllegalStateException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   postCourse(Course, Authentication).
        //   See https://diff.blue/R013 to resolve this issue.

        CourseController courseController = new CourseController(new CourseServiceImplementation(mock(CourseRepo.class),
                mock(ExamRepo.class), mock(ContentRepo.class), mock(AppUserRepo.class), mock(ResultRepo.class)));

        Course course = new Course();
        course.setCode("Code");
        course.setContents(new ArrayList<>());
        course.setDescription("The characteristics of someone or something");
        course.setEndTime(mock(Timestamp.class));
        course.setEnrolledUsers(new ArrayList<>());
        course.setExams(new ArrayList<>());
        course.setId(123L);
        course.setInstructor("Instructor");
        course.setName("Name");
        course.setRemainingSeats(1L);
        course.setStartTime(mock(Timestamp.class));
        course.setTotalSeats(1L);
        course.setWishlistUsers(new ArrayList<>());
//        courseController.postCourse(course, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CourseController#postCourse(Course, org.springframework.security.core.Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPostCourse2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalStateException: No current ServletRequestAttributes
        //       at org.springframework.util.Assert.state(Assert.java:76)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.getCurrentRequest(ServletUriComponentsBuilder.java:179)
        //       at org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath(ServletUriComponentsBuilder.java:147)
        //       at com.soumosir.coursehubbackend.api.CourseController.postCourse(CourseController.java:90)
        //   In order to prevent postCourse(Course, Authentication)
        //   from throwing IllegalStateException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   postCourse(Course, Authentication).
        //   See https://diff.blue/R013 to resolve this issue.

        Course course = new Course();
        course.setCode("Code");
        course.setContents(new ArrayList<>());
        course.setDescription("The characteristics of someone or something");
        course.setEndTime(mock(Timestamp.class));
        course.setEnrolledUsers(new ArrayList<>());
        course.setExams(new ArrayList<>());
        course.setId(123L);
        course.setInstructor("Instructor");
        course.setName("Name");
        course.setRemainingSeats(1L);
        course.setStartTime(mock(Timestamp.class));
        course.setTotalSeats(1L);
        course.setWishlistUsers(new ArrayList<>());
        CourseRepo courseRepo = mock(CourseRepo.class);
        when(courseRepo.findById((Long) any())).thenReturn(Optional.of(course));

        Content content = new Content();
        content.setDescription("The characteristics of someone or something");
        content.setId(123L);
        content.setName("Name");
        content.setType("Type");
        content.setUrl("https://example.org/example");
        content.setUsername("janedoe");
        ContentRepo contentRepo = mock(ContentRepo.class);
        when(contentRepo.findById((Long) any())).thenReturn(Optional.of(content));

        CourseServiceImplementation courseServiceImplementation = new CourseServiceImplementation(courseRepo,
                mock(ExamRepo.class), contentRepo, mock(AppUserRepo.class), mock(ResultRepo.class));
        courseServiceImplementation.addContent(123L, 123L);
        CourseController courseController = new CourseController(courseServiceImplementation);

        Course course1 = new Course();
        course1.setCode("Code");
        course1.setContents(new ArrayList<>());
        course1.setDescription("The characteristics of someone or something");
        course1.setEndTime(mock(Timestamp.class));
        course1.setEnrolledUsers(new ArrayList<>());
        course1.setExams(new ArrayList<>());
        course1.setId(123L);
        course1.setInstructor("Instructor");
        course1.setName("Name");
        course1.setRemainingSeats(1L);
        course1.setStartTime(mock(Timestamp.class));
        course1.setTotalSeats(1L);
        course1.setWishlistUsers(new ArrayList<>());
//        courseController.postCourse(course1, new TestingAuthenticationToken("Principal", "Credentials"),javax.servlet.http.HttpServletResponse);
    }

    /**
     * Method under test: {@link CourseController#addWishlist(AddWishlistForm, org.springframework.security.core.Authentication, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testAddWishlist() throws Exception {
        AddWishlistForm addWishlistForm = new AddWishlistForm();
        addWishlistForm.setWishlistId(123L);
        String content = (new ObjectMapper()).writeValueAsString(addWishlistForm);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/course/addwishlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":null}"));
    }

    /**
     * Method under test: {@link CourseController#getCourses()}
     */
    @Test
    void testGetCourses() throws Exception {
        when(this.courseService.getCourses()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course");
        MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CourseController#getCourses()}
     */
    @Test
    void testGetCourses2() throws Exception {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);
        Timestamp timestamp1 = mock(Timestamp.class);
        when(timestamp1.getTime()).thenReturn(10L);

        Course course = new Course();
        course.setCode("?");
        course.setContents(new ArrayList<>());
        course.setDescription("The characteristics of someone or something");
        course.setEndTime(timestamp);
        course.setEnrolledUsers(new ArrayList<>());
        course.setExams(new ArrayList<>());
        course.setId(123L);
        course.setInstructor("?");
        course.setName("?");
        course.setRemainingSeats(1L);
        course.setStartTime(timestamp1);
        course.setTotalSeats(1L);
        course.setWishlistUsers(new ArrayList<>());

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(course);
        when(this.courseService.getCourses()).thenReturn(courseList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course");
        MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"name\":\"?\",\"code\":\"?\",\"description\":\"The characteristics of someone or something\","
                                        + "\"instructor\":\"?\",\"startTime\":10,\"endTime\":10,\"totalSeats\":1,\"remainingSeats\":1,\"contents\":null,"
                                        + "\"exams\":null}]"));
    }

    /**
     * Method under test: {@link CourseController#getCourses()}
     */
    @Test
    void testGetCourses3() throws Exception {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);
        Timestamp timestamp1 = mock(Timestamp.class);
        when(timestamp1.getTime()).thenReturn(10L);

        Course course = new Course();
        course.setCode("?");
        course.setContents(new ArrayList<>());
        course.setDescription("The characteristics of someone or something");
        course.setEndTime(timestamp);
        course.setEnrolledUsers(new ArrayList<>());
        course.setExams(new ArrayList<>());
        course.setId(123L);
        course.setInstructor("?");
        course.setName("?");
        course.setRemainingSeats(1L);
        course.setStartTime(timestamp1);
        course.setTotalSeats(1L);
        course.setWishlistUsers(new ArrayList<>());
        Timestamp timestamp2 = mock(Timestamp.class);
        when(timestamp2.getTime()).thenReturn(10L);
        Timestamp timestamp3 = mock(Timestamp.class);
        when(timestamp3.getTime()).thenReturn(10L);

        Course course1 = new Course();
        course1.setCode("?");
        course1.setContents(new ArrayList<>());
        course1.setDescription("The characteristics of someone or something");
        course1.setEndTime(timestamp2);
        course1.setEnrolledUsers(new ArrayList<>());
        course1.setExams(new ArrayList<>());
        course1.setId(123L);
        course1.setInstructor("?");
        course1.setName("?");
        course1.setRemainingSeats(1L);
        course1.setStartTime(timestamp3);
        course1.setTotalSeats(1L);
        course1.setWishlistUsers(new ArrayList<>());

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course);
        when(this.courseService.getCourses()).thenReturn(courseList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course");
        MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"name\":\"?\",\"code\":\"?\",\"description\":\"The characteristics of someone or something\","
                                        + "\"instructor\":\"?\",\"startTime\":10,\"endTime\":10,\"totalSeats\":1,\"remainingSeats\":1,\"contents\":null,\"exams"
                                        + "\":null},{\"id\":123,\"name\":\"?\",\"code\":\"?\",\"description\":\"The characteristics of someone or something"
                                        + "\",\"instructor\":\"?\",\"startTime\":10,\"endTime\":10,\"totalSeats\":1,\"remainingSeats\":1,\"contents\":null,"
                                        + "\"exams\":null}]"));
    }

    /**
     * Method under test: {@link CourseController#getCourses()}
     */
    @Test
    void testGetCourses4() throws Exception {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);
        Timestamp timestamp1 = mock(Timestamp.class);
        when(timestamp1.getTime()).thenReturn(10L);

        Course course = new Course();
        course.setCode("?");
        course.setContents(new ArrayList<>());
        course.setDescription("The characteristics of someone or something");
        course.setEndTime(timestamp);
        course.setEnrolledUsers(new ArrayList<>());
        course.setExams(new ArrayList<>());
        course.setId(123L);
        course.setInstructor("?");
        course.setName("?");
        course.setRemainingSeats(1L);
        course.setStartTime(timestamp1);
        course.setTotalSeats(1L);
        course.setWishlistUsers(new ArrayList<>());
        Timestamp timestamp2 = mock(Timestamp.class);
        when(timestamp2.getTime()).thenReturn(10L);
        Timestamp timestamp3 = mock(Timestamp.class);
        when(timestamp3.getTime()).thenReturn(10L);

        Course course1 = new Course();
        course1.setCode("?");
        course1.setContents(new ArrayList<>());
        course1.setDescription("The characteristics of someone or something");
        course1.setEndTime(timestamp2);
        course1.setEnrolledUsers(new ArrayList<>());
        course1.setExams(new ArrayList<>());
        course1.setId(123L);
        course1.setInstructor("?");
        course1.setName("?");
        course1.setRemainingSeats(1L);
        course1.setStartTime(timestamp3);
        course1.setTotalSeats(1L);
        course1.setWishlistUsers(new ArrayList<>());

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course);
        when(this.courseService.getCourses()).thenReturn(courseList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/course");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"name\":\"?\",\"code\":\"?\",\"description\":\"The characteristics of someone or something\","
                                        + "\"instructor\":\"?\",\"startTime\":10,\"endTime\":10,\"totalSeats\":1,\"remainingSeats\":1,\"contents\":null,\"exams"
                                        + "\":null},{\"id\":123,\"name\":\"?\",\"code\":\"?\",\"description\":\"The characteristics of someone or something"
                                        + "\",\"instructor\":\"?\",\"startTime\":10,\"endTime\":10,\"totalSeats\":1,\"remainingSeats\":1,\"contents\":null,"
                                        + "\"exams\":null}]"));
    }

    /**
     * Method under test: {@link CourseController#addWishlist(AddWishlistForm, org.springframework.security.core.Authentication, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testAddWishlist2() throws Exception {
        AddWishlistForm addWishlistForm = new AddWishlistForm();
        addWishlistForm.setWishlistId(123L);
        String content = (new ObjectMapper()).writeValueAsString(addWishlistForm);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/course/addwishlist", "Uri Variables")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":null}"));
    }

    /**
     * Method under test: {@link CourseController#enrollCourse(EnrollCourseForm, org.springframework.security.core.Authentication, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testEnrollCourse() throws Exception {
        EnrollCourseForm enrollCourseForm = new EnrollCourseForm();
        enrollCourseForm.setCourseId(123L);
        String content = (new ObjectMapper()).writeValueAsString(enrollCourseForm);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/course/enrolluser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":null}"));
    }

    /**
     * Method under test: {@link CourseController#getContent(Long, org.springframework.security.core.Authentication, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testGetContent() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/content/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":null}"));
    }

    /**
     * Method under test: {@link CourseController#getContent(Long, org.springframework.security.core.Authentication, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testGetContent2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/content/{id}", 123L);
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":null}"));
    }

    /**
     * Method under test: {@link CourseController#getCourses(Long)}
     */
    @Test
    void testGetCourses5() throws Exception {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);
        Timestamp timestamp1 = mock(Timestamp.class);
        when(timestamp1.getTime()).thenReturn(10L);

        Course course = new Course();
        course.setCode("Code");
        course.setContents(new ArrayList<>());
        course.setDescription("The characteristics of someone or something");
        course.setEndTime(timestamp);
        course.setEnrolledUsers(new ArrayList<>());
        course.setExams(new ArrayList<>());
        course.setId(123L);
        course.setInstructor("Instructor");
        course.setName("Name");
        course.setRemainingSeats(1L);
        course.setStartTime(timestamp1);
        course.setTotalSeats(1L);
        course.setWishlistUsers(new ArrayList<>());
        when(this.courseService.getCourse((Long) any())).thenReturn(course);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"code\":\"Code\",\"description\":\"The characteristics of someone or something\","
                                        + "\"instructor\":\"Instructor\",\"startTime\":10,\"endTime\":10,\"totalSeats\":1,\"remainingSeats\":1,\"contents\":["
                                        + "],\"exams\":[]}"));
    }

    /**
     * Method under test: {@link CourseController#getCourses(Long)}
     */
    @Test
    void testGetCourses6() throws Exception {
        Content content = new Content();
        content.setDescription("The characteristics of someone or something");
        content.setId(123L);
        content.setName("?");
        content.setType("?");
        content.setUrl("https://example.org/example");
        content.setUsername("janedoe");

        ArrayList<Content> contentList = new ArrayList<>();
        contentList.add(content);
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);
        Timestamp timestamp1 = mock(Timestamp.class);
        when(timestamp1.getTime()).thenReturn(10L);

        Course course = new Course();
        course.setCode("Code");
        course.setContents(contentList);
        course.setDescription("The characteristics of someone or something");
        course.setEndTime(timestamp);
        course.setEnrolledUsers(new ArrayList<>());
        course.setExams(new ArrayList<>());
        course.setId(123L);
        course.setInstructor("Instructor");
        course.setName("Name");
        course.setRemainingSeats(1L);
        course.setStartTime(timestamp1);
        course.setTotalSeats(1L);
        course.setWishlistUsers(new ArrayList<>());
        when(this.courseService.getCourse((Long) any())).thenReturn(course);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"code\":\"Code\",\"description\":\"The characteristics of someone or something\","
                                        + "\"instructor\":\"Instructor\",\"startTime\":10,\"endTime\":10,\"totalSeats\":1,\"remainingSeats\":1,\"contents\":["
                                        + "{\"id\":123,\"name\":\"?\",\"type\":\"?\",\"url\":null,\"username\":\"janedoe\",\"description\":null}],\"exams\":[]}"));
    }

    /**
     * Method under test: {@link CourseController#getCourses(Long)}
     */
    @Test
    void testGetCourses7() throws Exception {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

        Exam exam = new Exam();
        exam.setAnswers("?");
        exam.setDuration(1L);
        exam.setId(123L);
        exam.setName("?");
        exam.setQuestions("?");
        exam.setType("?");
        exam.setUser("?");

        ArrayList<Exam> examList = new ArrayList<>();
        examList.add(exam);
        Timestamp timestamp1 = mock(Timestamp.class);
        when(timestamp1.getTime()).thenReturn(10L);

        Course course = new Course();
        course.setCode("Code");
        course.setContents(new ArrayList<>());
        course.setDescription("The characteristics of someone or something");
        course.setEndTime(timestamp);
        course.setEnrolledUsers(new ArrayList<>());
        course.setExams(examList);
        course.setId(123L);
        course.setInstructor("Instructor");
        course.setName("Name");
        course.setRemainingSeats(1L);
        course.setStartTime(timestamp1);
        course.setTotalSeats(1L);
        course.setWishlistUsers(new ArrayList<>());
        when(this.courseService.getCourse((Long) any())).thenReturn(course);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"code\":\"Code\",\"description\":\"The characteristics of someone or something\","
                                        + "\"instructor\":\"Instructor\",\"startTime\":10,\"endTime\":10,\"totalSeats\":1,\"remainingSeats\":1,\"contents\":["
                                        + "],\"exams\":[{\"id\":123,\"name\":\"?\",\"type\":\"?\",\"duration\":1,\"questions\":null,\"answers\":null,\"user\":\"?\"}]"
                                        + "}"));
    }

    /**
     * Method under test: {@link CourseController#getCourses(Long)}
     */
    @Test
    void testGetCourses8() throws Exception {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);
        Timestamp timestamp1 = mock(Timestamp.class);
        when(timestamp1.getTime()).thenReturn(10L);

        Course course = new Course();
        course.setCode("Code");
        course.setContents(new ArrayList<>());
        course.setDescription("The characteristics of someone or something");
        course.setEndTime(timestamp);
        course.setEnrolledUsers(new ArrayList<>());
        course.setExams(new ArrayList<>());
        course.setId(123L);
        course.setInstructor("Instructor");
        course.setName("Name");
        course.setRemainingSeats(1L);
        course.setStartTime(timestamp1);
        course.setTotalSeats(1L);
        course.setWishlistUsers(new ArrayList<>());
        when(this.courseService.getCourse((Long) any())).thenReturn(course);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/course/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"code\":\"Code\",\"description\":\"The characteristics of someone or something\","
                                        + "\"instructor\":\"Instructor\",\"startTime\":10,\"endTime\":10,\"totalSeats\":1,\"remainingSeats\":1,\"contents\":["
                                        + "],\"exams\":[]}"));
    }

    /**
     * Method under test: {@link CourseController#getCourses(Long)}
     */
    @Test
    void testGetCourses9() throws Exception {
        Content content = new Content();
        content.setDescription("The characteristics of someone or something");
        content.setId(123L);
        content.setName("?");
        content.setType("?");
        content.setUrl("https://example.org/example");
        content.setUsername("janedoe");

        Content content1 = new Content();
        content1.setDescription("The characteristics of someone or something");
        content1.setId(123L);
        content1.setName("?");
        content1.setType("?");
        content1.setUrl("https://example.org/example");
        content1.setUsername("janedoe");

        ArrayList<Content> contentList = new ArrayList<>();
        contentList.add(content1);
        contentList.add(content);
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);
        Timestamp timestamp1 = mock(Timestamp.class);
        when(timestamp1.getTime()).thenReturn(10L);

        Course course = new Course();
        course.setCode("Code");
        course.setContents(contentList);
        course.setDescription("The characteristics of someone or something");
        course.setEndTime(timestamp);
        course.setEnrolledUsers(new ArrayList<>());
        course.setExams(new ArrayList<>());
        course.setId(123L);
        course.setInstructor("Instructor");
        course.setName("Name");
        course.setRemainingSeats(1L);
        course.setStartTime(timestamp1);
        course.setTotalSeats(1L);
        course.setWishlistUsers(new ArrayList<>());
        when(this.courseService.getCourse((Long) any())).thenReturn(course);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"code\":\"Code\",\"description\":\"The characteristics of someone or something\","
                                        + "\"instructor\":\"Instructor\",\"startTime\":10,\"endTime\":10,\"totalSeats\":1,\"remainingSeats\":1,\"contents\":["
                                        + "{\"id\":123,\"name\":\"?\",\"type\":\"?\",\"url\":null,\"username\":\"janedoe\",\"description\":null},{\"id\":123,\"name\""
                                        + ":\"?\",\"type\":\"?\",\"url\":null,\"username\":\"janedoe\",\"description\":null}],\"exams\":[]}"));
    }

    /**
     * Method under test: {@link CourseController#getCourses(Long)}
     */
    @Test
    void testGetCourses10() throws Exception {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

        Exam exam = new Exam();
        exam.setAnswers("?");
        exam.setDuration(1L);
        exam.setId(123L);
        exam.setName("?");
        exam.setQuestions("?");
        exam.setType("?");
        exam.setUser("?");

        Exam exam1 = new Exam();
        exam1.setAnswers("?");
        exam1.setDuration(1L);
        exam1.setId(123L);
        exam1.setName("?");
        exam1.setQuestions("?");
        exam1.setType("?");
        exam1.setUser("?");

        ArrayList<Exam> examList = new ArrayList<>();
        examList.add(exam1);
        examList.add(exam);
        Timestamp timestamp1 = mock(Timestamp.class);
        when(timestamp1.getTime()).thenReturn(10L);

        Course course = new Course();
        course.setCode("Code");
        course.setContents(new ArrayList<>());
        course.setDescription("The characteristics of someone or something");
        course.setEndTime(timestamp);
        course.setEnrolledUsers(new ArrayList<>());
        course.setExams(examList);
        course.setId(123L);
        course.setInstructor("Instructor");
        course.setName("Name");
        course.setRemainingSeats(1L);
        course.setStartTime(timestamp1);
        course.setTotalSeats(1L);
        course.setWishlistUsers(new ArrayList<>());
        when(this.courseService.getCourse((Long) any())).thenReturn(course);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"code\":\"Code\",\"description\":\"The characteristics of someone or something\","
                                        + "\"instructor\":\"Instructor\",\"startTime\":10,\"endTime\":10,\"totalSeats\":1,\"remainingSeats\":1,\"contents\":["
                                        + "],\"exams\":[{\"id\":123,\"name\":\"?\",\"type\":\"?\",\"duration\":1,\"questions\":null,\"answers\":null,\"user\":\"?\"},"
                                        + "{\"id\":123,\"name\":\"?\",\"type\":\"?\",\"duration\":1,\"questions\":null,\"answers\":null,\"user\":\"?\"}]}"));
    }

    /**
     * Method under test: {@link CourseController#getEnrolledCourses(org.springframework.security.core.Authentication)}
     */
    @Test
    void testGetEnrolledCourses() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CourseController#getExam(Long, org.springframework.security.core.Authentication, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testGetExam() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/exam/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":null}"));
    }

    /**
     * Method under test: {@link CourseController#getExam(Long, org.springframework.security.core.Authentication, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testGetExam2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/exam/{id}", 123L);
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":null}"));
    }

    /**
     * Method under test: {@link CourseController#getExams(org.springframework.security.core.Authentication)}
     */
    @Test
    void testGetExams() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CourseController#getResultByCourseId(Long, org.springframework.security.core.Authentication)}
     */
    @Test
    void testGetResultByCourseId() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/courseresult/{id}", "Uri Variables",
                "Uri Variables");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CourseController#getWishlistCourses(org.springframework.security.core.Authentication)}
     */
    @Test
    void testGetWishlistCourses() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CourseController#removeWishlist(AddWishlistForm, org.springframework.security.core.Authentication, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testRemoveWishlist() throws Exception {
        AddWishlistForm addWishlistForm = new AddWishlistForm();
        addWishlistForm.setWishlistId(123L);
        String content = (new ObjectMapper()).writeValueAsString(addWishlistForm);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/course/removewishlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":null}"));
    }

    /**
     * Method under test: {@link CourseController#submitExam(ExamForm, org.springframework.security.core.Authentication, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testSubmitExam() throws Exception {
        ExamForm examForm = new ExamForm();
        examForm.setAnswers("Answers");
        examForm.setExamId(123L);
        String content = (new ObjectMapper()).writeValueAsString(examForm);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/exam/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":null}"));
    }

    /**
     * Method under test: {@link CourseController#unenrollCourse(EnrollCourseForm, org.springframework.security.core.Authentication, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testUnenrollCourse() throws Exception {
        EnrollCourseForm enrollCourseForm = new EnrollCourseForm();
        enrollCourseForm.setCourseId(123L);
        String content = (new ObjectMapper()).writeValueAsString(enrollCourseForm);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/course/unenrolluser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":null}"));
    }
}


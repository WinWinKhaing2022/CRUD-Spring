package Student.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Student.Dao.CourseDao;
import Student.Dao.StudentDao;
import Student.Dto.CourseResponseDto;
import Student.Dto.StudentRequestDto;
import Student.Dto.StudentResponseDto;
import Student.Model.StudentBean;

@Controller
public class StudentController {
 @Autowired
 private StudentDao sdao;
 
 
 	@RequestMapping(value="/setupaddstudent",method=RequestMethod.GET)
 	public ModelAndView setupaddstudent(ModelMap model) {
 		 CourseDao cdao =new CourseDao();
 		ArrayList<CourseResponseDto> list=cdao.selectAllCourse();
		model.addAttribute("courseList", list);
		return new ModelAndView("STU001","sbean",new StudentBean());
 	}
 	
 	@RequestMapping(value="/addstudent",method=RequestMethod.POST)
 	public String addStudent(@ModelAttribute("sbean")StudentBean sbean,ModelMap model) {
 		String name=sbean.getName();
 		String dob=sbean.getDob();
 		String gender=sbean.getGender();
 		String phone=sbean.getPhone();
 		String education=sbean.getEducation();
 		List<String>attendCourses=sbean.getAttendCourses();
 		if(attendCourses==null) {
 			model.addAttribute("error","Courses field can not be blank!!");
 			model.addAttribute("data",sbean);
 			return "STU001";
 		}
 		else 	if (name.isBlank() || dob.isBlank() || gender.isBlank() || phone.isBlank()|| education.isBlank() || attendCourses.isEmpty()) {
 				model.addAttribute("data",sbean);
 				model.addAttribute("error","Field can not be blank!!");
 				return "STU001";
 				}
 		else {
 					ArrayList<StudentResponseDto> studentList = sdao.selectAllStudents();
 					
 					if (studentList.size() == 0) {
 						sbean.setId("STU001");

 					} 
 					else {
 						int tempId = Integer.parseInt(studentList.get(studentList.size() - 1).getId().substring(3)) + 1;
 						String userId = String.format("STU%03d", tempId);
 						sbean.setId(userId);
 					}
 					StudentRequestDto reqDto = new StudentRequestDto(name, dob, gender, phone, education);
 					reqDto.setId(sbean.getId());
 					System.out.println(reqDto);
 					sdao.insertStudentData(reqDto);
 					
 					for (String course:attendCourses) {
 						sdao.addCourseForStrudent(course, sbean.getId());
 					}
 			
 					
 					return "redirect:/showstudent";
 		}
 	}
 	
 	 @RequestMapping (value="/showstudent",method=RequestMethod.GET)
 	public  String setupSearch(ModelMap model) {
 		
 	CourseDao cdao=new CourseDao();
 	ArrayList<ArrayList<CourseResponseDto>> coursesLists = new ArrayList<>();
 	
	ArrayList<StudentResponseDto> studentList = sdao.selectAllStudents();
	for (StudentResponseDto student : studentList) {
		ArrayList<CourseResponseDto> courseList = cdao.selectCoursesByStudentid(student.getId());
		coursesLists.add(courseList);
	}
		model.addAttribute("studentList", studentList);
		model.addAttribute("coursesLists", coursesLists);

		return "STU003";
 	}

 	@RequestMapping(value="/searchstudent",method=RequestMethod.POST)
 	public	String searchStudent(ModelMap model,@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("attendCourses") String attendCourses) {
 			String sid= id.isBlank()?"@#$%": id;
 			String sname=name.isBlank()?"@#$%" :name;
 			String scourse=attendCourses.isBlank()?"@#$%" : attendCourses;
 			CourseDao cdao=new CourseDao();
 			ArrayList<ArrayList<CourseResponseDto>> coursesLists = new ArrayList<>();
 			ArrayList<StudentResponseDto> studentList = sdao.selectStudentListByIdOrNameOrCourse(sid, sname, scourse);
 			for (StudentResponseDto student : studentList) {
 				ArrayList<CourseResponseDto> courseList = cdao.selectCoursesByStudentid(student.getId());
 				coursesLists.add(courseList);
 			}
 		
 			if (studentList.size() == 0) {
 				studentList = sdao.selectAllStudents();
 				ArrayList<ArrayList<CourseResponseDto>> coursesList = new ArrayList<>();
 				for (StudentResponseDto student : studentList) {
 					ArrayList<CourseResponseDto> courseList = cdao.selectCoursesByStudentid(student.getId());
 					coursesList.add(courseList);
 				}
 				model.addAttribute("studentList", studentList);
 				model.addAttribute("coursesLists", coursesList);
 				return "STU003";
 			} 
 			else {
 				model.addAttribute("studentList", studentList);
 				model.addAttribute("coursesLists", coursesLists);
 				return"STU003";
 			}
 		}
 		
 	@RequestMapping (value="/seemore/{id}", method=RequestMethod.GET)
 		public	String seeMore(ModelMap model,@PathVariable("id") String id) throws SQLException {
 			//	StudentRequestDto reqdto=new StudentRequestDto();
 			//	reqdto.setId(id);
 				CourseDao cdao=new CourseDao();
 				StudentResponseDto student = sdao.selectStudentById(id);
 				List<CourseResponseDto> courses = cdao.selectAllCourse();
 				StudentBean sbean=new StudentBean();
 				sbean.setId(student.getId());
 				sbean.setName(student.getName());
 				sbean.setDob(student.getDob());
 				sbean.setGender(student.getGender());
 				sbean.setPhone(student.getPhone());
 				sbean.setEducation(student.getEducation());
 				List<CourseResponseDto> attendCourses = cdao.selectCoursesByStudentid(id);
 				for(CourseResponseDto course:attendCourses) {
 					sbean.addAttendCourse(course);
 				}
 				model.addAttribute("courses",courses);
 				model.addAttribute("data", sbean);
 				return "STU002";
 			}
 	/**	@RequestMapping (value="/seemore/{id}", method=RequestMethod.GET)
		public	ModelAndView seeMore(ModelMap model,@PathVariable("id") String id,HttpSession session) {
 			StudentDao studentDao=new StudentDao();
 			CourseDao courseDao=new CourseDao();
 			
 			StudentResponseDto student = studentDao.selectStudentById(id);
 			List<CourseResponseDto> attendCourses = courseDao.selectCoursesByStudentid(id);
 			List<CourseResponseDto> courses = courseDao.selectAllCourse();
 			session.setAttribute("attendCourses", attendCourses);
 			for (CourseResponseDto at:attendCourses) {
 				System.out.println(at.getName());
 			}
 			System.out.println(attendCourses);
 			session.setAttribute("courses", courses);
 			
 			return new ModelAndView("STU002","sbean",student);
 		
 		}**/
 	
 	
 				
 		@RequestMapping(value="/updatestudent",method=RequestMethod.POST)
 			public String updateStudent	(ModelMap model,@ModelAttribute ("") StudentBean sbean) {
 			String id=sbean.getId();
 			String name=sbean.getName();
 	 		String dob=sbean.getDob();
 	 		String gender=sbean.getGender();
 	 		String phone=sbean.getPhone();
 	 		String education=sbean.getEducation();
 	 		List<String>attendCourses=sbean.getAttendCourses();
 		System.out.println("AttendCourses  "+attendCourses);
 			if (attendCourses == null) {
 				sbean = new StudentBean(id, name, dob, gender, phone, education);
 				model.addAttribute("error", "Fill the blank !!");
 				model.addAttribute("data", sbean);
 				return "STU002";
 			}
 			else {
 				 if (name.isBlank() || dob.isBlank() || gender.isBlank() || phone.isBlank() || education.isBlank()) {
 					
 					sbean = new StudentBean(id, name, dob, gender, phone, education, attendCourses);
 					model.addAttribute("error", "Fill the blank !!");
 					model.addAttribute("data", sbean);
 					return "STU002";
 					
 				 }
 				 else {
 					StudentRequestDto reqDto = new StudentRequestDto(id, name, dob, gender, phone, education);
 					sdao.updateStudent(reqDto);
 					sdao.deleteAttendCoursesByStudentId(id);
 					for (String course:attendCourses) {
 						sdao.addCourseForStrudent(course, reqDto.getId());
 						System.out.println("Table Add "+course+" "+reqDto.getId()+"   "+sdao);
 					}
 					return "redirect:/showstudent";
 				 }
 			
 		}
 			
 	}
 		@RequestMapping (value="/Deletestudent/{id}", method=RequestMethod.GET)
 		public	String deleteStudent(ModelMap model,@PathVariable("id") String id) {
 			sdao.deleteAttendCoursesByStudentId(id);
 			sdao.deleteStudentById(id);
 			return "redirect:/showstudent";
 			
 		}
 		
}

 		


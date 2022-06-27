package Student.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Student.Dao.CourseDao;
import Student.Dto.CourseRequestDto;
import Student.Dto.CourseResponseDto;
import Student.Model.CourseBean;

@Controller
public class CourseController {
	 @Autowired
	private  CourseDao cdao;
	 
	 @RequestMapping(value="/setupaddcourse", method=RequestMethod.GET)
	 public ModelAndView setupcourse() {
		 return new ModelAndView("BUD003","cbean",new CourseBean());
	 }
	 @RequestMapping(value="/addcourse",method=RequestMethod.POST)
	 public String addcourse(@ModelAttribute("cbean")CourseBean cbean,ModelMap model) {
		 
		 String name=cbean.getName();
		 if(name.trim().equals("") || name.isEmpty() || name == null) {
				model.addAttribute("error", "Filed can not blank!!");
				return"BUD003";
		 }	
		 else if(cdao.checkName(cbean.getName())){
			 model.addAttribute("data", cbean);
			 model.addAttribute("error", "Name already exists");
			 return "BUD003";
		 }
		 else {
				ArrayList<CourseResponseDto> courseList=cdao.selectAllCourse();
				
				if(courseList.size()<=0||courseList==null) {
					cbean.setId("COU001");
				}
				else{
					CourseResponseDto lastDTO = courseList.get(courseList.size()-1);
				int lastId = Integer.parseInt(lastDTO.getId().substring(3));
				String userId = String.format("COU"+"%03d", lastId+1);
					cbean.setId(userId);
				}
				CourseRequestDto reqdto=new CourseRequestDto();
				reqdto.setId(cbean.getId());
				reqdto.setName(cbean.getName());
				int result=cdao.insertCourse(reqdto);
				if(result>0) {
					model.addAttribute("msg", "Register Successfull!!!");
				}
				else {
					model.addAttribute("msg", "Insert Fail!!!");
				}
				return "BUD003";	
					
				}
	 }
}

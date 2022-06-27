package Student.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
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

import Student.Dao.UserDao;
import Student.Dto.UserRequestDto;
import Student.Dto.UserResponseDto;
import Student.Model.StudentBean;
import Student.Model.UserBean;

@Controller
public class UserController {
	@Autowired
	private UserDao udao;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView Login() {
		return new ModelAndView("LGN001","ubean",new UserBean());
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String setLogin(@RequestParam("email")String email,@RequestParam("password")String password,HttpSession session,ModelMap model) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String currentdate = formatter.format(date);
		UserDao userdao = new UserDao();
		UserBean bean = new UserBean();
		bean.setEmail(email);
		bean.setPassword(password);
		if (userdao.checkLogin(email, password)) {
			UserResponseDto dto = userdao.selectLoginOneEmail(email);
			session.setAttribute("userdata", dto);
			session.setAttribute("date", currentdate);
			return "MNU001";
		} else {
			model.addAttribute("ubean", bean);
			model.addAttribute("error", "Email and Password do not match!!!");
			return "LGN001";
		}

	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String Logout(ModelMap model,HttpSession session) {
		session.removeAttribute("userdata");
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping(value="/menu",method=RequestMethod.GET)
	public String  Menu() {
		return "MNU001";
	}
	
	@RequestMapping(value="/showuser",method=RequestMethod.GET)
	public String showUser(ModelMap model) {
		ArrayList<UserResponseDto> list=udao.selectAllUsers();
			model.addAttribute("list", list);
		
		return "USR003";
	}
	
	@RequestMapping(value="/searchUser",method=RequestMethod.POST)
	public String searchUser(@RequestParam("id")String id,@RequestParam("name")String name,ModelMap model) {
		ArrayList<UserResponseDto> searchlist=null;
		if(id.isEmpty()) {
			searchlist=udao.selectNameSearch(name);
		}
		else if(name.isEmpty()) {
			searchlist=udao.selectIdSearch(id);
		} 
		else if(name.isEmpty()&&id.isEmpty()) {
			searchlist=udao.selectAllUsers();
			System.out.println("name id empty "+searchlist);
		}
		else {
			searchlist =udao.selectNameAndIdSearch(id,name);
			System.out.println("select name and id "+searchlist);
			if(searchlist.size()==0) {
				searchlist=udao.selectAllUsers();
			}
		}
		model.addAttribute("list", searchlist);
		return "USR003";
	}
	@RequestMapping(value="/setupaddUser",method=RequestMethod.GET)
	public ModelAndView setupaddUser() {
		return new ModelAndView("USR001","ubean",new UserBean());
		
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public String addUser(@ModelAttribute("ubean")UserBean ubean,ModelMap model) {
		String name=ubean.getName();
		String email=ubean.getEmail();
		String password=ubean.getPassword();
		String cpassword=ubean.getCpassword();
		String role=ubean.getRole();
		if(name.isBlank()||email.isBlank()||password.isBlank()||cpassword.isBlank()||role.isBlank()) {
			model.addAttribute("error","Filed cannot be blank!!!");
			model.addAttribute("ubean",ubean);
			model.addAttribute("cpassword", cpassword);
			return "USR001";
		}
		else if(!password.equals(cpassword)){
			model.addAttribute("error","Password do not match");
			model.addAttribute("ubean", ubean);
			return "USR001";
		}
		else {
			ArrayList<UserResponseDto> list=udao.selectAllUsers();
			if(udao.checkEmailExist(email)) {
				model.addAttribute("error", "Email already exist!!");
				model.addAttribute("ubean",ubean);
				return "USR001";
			}
			else {
				if (list == null) {
					list = new ArrayList<>();
				}
				else if(list.size()==0) {
					ubean.setId("USR001");
				}
				else {
					int id=Integer.parseInt(list.get(list.size()-1).getId().substring(3))+1;
					String userid=String.format("USR%03d", id);
					ubean.setId(userid);
				}
				UserRequestDto reqdto=new UserRequestDto();
				reqdto.setId(ubean.getId());
				reqdto.setName(ubean.getName());
				reqdto.setEmail(ubean.getEmail());
				reqdto.setPassword(ubean.getPassword());
				reqdto.setRole(ubean.getRole());
				udao.insertUserData(reqdto);
				model.addAttribute("msg","You register successful !!");
				return "USR001";
				}
			}
		}
	@RequestMapping (value="/setupUpdate/{id}", method=RequestMethod.GET)
		public	 ModelAndView Update(ModelMap model,@PathVariable("id") String id) {
		UserResponseDto resdto = udao.selectOneId(id);
		UserBean user=new UserBean();
		user.setId(resdto.getId());
		user.setName(resdto.getName());
		user.setEmail(resdto.getEmail());
		user.setPassword(resdto.getPassword());
		user.setCpassword(resdto.getPassword());
		user.setRole(resdto.getRole());
		return new ModelAndView("USR002","ubean",user);
	}
	
	@RequestMapping(value = "/UpdateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("ubean") UserBean ubean, ModelMap model,HttpSession session,HttpServletRequest req)  {
			String id=ubean.getId();
			String name=ubean.getName();
			String email=ubean.getEmail();
			String password=ubean.getPassword();
			String cpassword=ubean.getCpassword();
			String role=ubean.getRole();
			UserResponseDto sessiondata = (UserResponseDto) session.getAttribute("userdata");
			if(name.isBlank()||email.isBlank()||password.isBlank()||cpassword.isBlank()||role.isBlank()) {
				model.addAttribute("error","Filed cannot be blank!!!");
				return"USR002";
			} 
			else if (!password.equals(cpassword)) {
				model.addAttribute("error", "Do not match password and cpassword!!");
				return "USR002";
			}
			else {
				
				
				UserResponseDto res=udao.selectOneId(id);
				UserRequestDto reqdto=new UserRequestDto(id,name,email,password,role);
				if(!res.getEmail().equals(email)) {
					if(udao.checkEmailExist(email)) {
						model.addAttribute("error","Email already Exist");
						return "USR002";
					} 
					else {
							udao.UpdateUserData( reqdto);
							if(reqdto.getEmail().equals(sessiondata.getEmail())) {
								session.setAttribute("userdata", reqdto);
							}
							return "redirect:/showuser";
					}
				}
				else {
					udao.UpdateUserData(reqdto);
					if(reqdto.getEmail().equals(sessiondata.getEmail())) {
						UserResponseDto resdto=new UserResponseDto(id,name,email,password,role);
						session.setAttribute("userdata",resdto);
					}
					return "redirect:/showuser";
					
				}
}
	}
	

	@RequestMapping (value="/deleteUser/{id}", method=RequestMethod.GET)
	public	String Delete(ModelMap model,@PathVariable("id") String id) {
	
		UserResponseDto dto=udao.selectOneId(id);
		UserRequestDto reqdto=new UserRequestDto();
		reqdto.setId(dto.getId());
		reqdto.setName(dto.getName());
		reqdto.setEmail(dto.getEmail());
		reqdto.setPassword(dto.getPassword());
		reqdto.setRole(dto.getRole());
		udao.deleteUser(reqdto);
		return "redirect:/showuser";
		
		
	}
}


package Student.Dto;

import java.util.List;

public class StudentResponseDto {

	


	private String id;
	private String name;
	private String dob;
	private String gender;
	private String phone;
	private String education;
	private List<String>attendCourse;
	
	public StudentResponseDto() {
		
	}

	public StudentResponseDto(String name, String dob, String gender, String phone, String education) {
		super();
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.phone = phone;
		this.education = education;
		
	}
	public StudentResponseDto(String name, String dob, String gender, String phone, String education,List<String>attendCourse) {
		super();
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.phone = phone;
		this.education = education;
		this.attendCourse=attendCourse;
		
	}

	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	

	public List<String> getAttendCourse() {
		return attendCourse;
	}

	public void setAttendCourse(List<String> attendCourse) {
		this.attendCourse = attendCourse;
	}

	@Override
	public String toString() {
		return "StudentResponseDto [id=" + id + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", phone="
				+ phone + ", education=" + education + "]";
	}
	
}

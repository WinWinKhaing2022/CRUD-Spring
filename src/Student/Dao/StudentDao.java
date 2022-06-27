package Student.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import Student.Dto.StudentRequestDto;
import Student.Dto.StudentResponseDto;

@Service("studentdao")
public class StudentDao {
/**	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
		System.out.println("Got Connection");
	}**/

	public ArrayList<StudentResponseDto> selectAllStudents(){
		ArrayList<StudentResponseDto>list=new ArrayList<StudentResponseDto>();
		Connection con =MyConnection.getConnection();
		String sql="select * from students";		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				StudentResponseDto res=new StudentResponseDto();
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setDob(rs.getString("dob"));
				res.setGender(rs.getString("gender"));
				res.setPhone(rs.getString("phone"));
				res.setEducation(rs.getString("education"));
				System.out.println("Search List "+res);
				list.add(res);
				
			}
		} catch (SQLException e) {
			System.out.println("Database error in selectAll"+e.getMessage());
		}
		return list;
	}
	
	public int insertStudentData(StudentRequestDto dto) {
		int i=0;
		Connection con =MyConnection.getConnection();
		String sql="insert into students(id,name,dob,gender,phone,education) values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2,dto.getName());
			ps.setString(3, dto.getDob());
			ps.setString(4,dto.getGender());
			ps.setString(5, dto.getPhone());
			ps.setString(6, dto.getEducation());
			i=ps.executeUpdate();
			System.out.println(i);
		} catch (SQLException e) {
			System.out.println("Database error in inserting "+e.getMessage());
		}
		return i;
	}
	public int addCourseForStrudent(String course_id, String student_id) {
		System.out.println(course_id+" "+student_id);
		Connection con =MyConnection.getConnection();
		String sql = "insert into students_courses (student_id, course_id) values(?, ?)";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			ps.setString(2, course_id);
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return i;
	}
	
	public ArrayList<StudentResponseDto> selectStudentListByIdOrNameOrCourse(String id, String name, String course) {
		Connection con =MyConnection.getConnection();
		ArrayList<StudentResponseDto> list = new ArrayList<>();
		String sql = "select distinct students.id, students.name from students_courses join students on students_courses.student_id = students.id join courses on students_courses.course_id = courses.id where students.id like ? or students.name like ? or courses.name like ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + id + "%");
			ps.setString(2, "%" + name + "%");
			ps.setString(3, "%" + course + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentResponseDto res = new StudentResponseDto();
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				list.add(res);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public StudentResponseDto selectStudentById(String student_id) {
		Connection con =MyConnection.getConnection();
		String sql = "select * from students where id=?";
		StudentResponseDto res = new StudentResponseDto();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setDob(rs.getString("dob"));
				res.setGender(rs.getString("gender"));
				res.setPhone(rs.getString("phone"));
				res.setEducation(rs.getString("education"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;
	}
	public int updateStudent(StudentRequestDto dto) {
		Connection con =MyConnection.getConnection();
		String sql = "update students set id=?, name=?, dob=?, gender=?, phone=?, education=? where id=?";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getDob());
			ps.setString(4, dto.getGender());
			ps.setString(5, dto.getPhone());
			ps.setString(6, dto.getEducation());
			ps.setString(7, dto.getId());
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}
	public int deleteAttendCoursesByStudentId(String student_id) {
		Connection con =MyConnection.getConnection();		
		String sql = "delete from students_courses where student_id=?";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}
	public int deleteStudentById(String student_id) {
		Connection con =MyConnection.getConnection();	
		String sql = "delete from students where id=?";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}


}

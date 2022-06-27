package Student.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;


import Student.Dto.CourseRequestDto;
import Student.Dto.CourseResponseDto;

@Service("coursedao")

public class CourseDao {
/**	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
		System.out.println("Got Connection");
	}
	**/
	public int insertCourse(CourseRequestDto dto) {
		int result=0;
		Connection con =MyConnection.getConnection();
		String sql="insert into courses(id,name) values(?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getName());
			result=ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Database error insert"+e.getMessage());
		}
		return result;
	}

	public ArrayList<CourseResponseDto> selectAllCourse() {
		ArrayList<CourseResponseDto> list = new ArrayList<>();
		Connection con =MyConnection.getConnection();
		String sql = "select * from courses";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CourseResponseDto res = new CourseResponseDto();
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				list.add(res);
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	public boolean checkName(String name) {
		Connection con =MyConnection.getConnection();
		String sql="select * from courses where name=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			} catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}
	public CourseResponseDto findName(String name) {
		Connection con =MyConnection.getConnection();
		String sql="select * from courses where name=?";
		CourseResponseDto res=new CourseResponseDto();
		PreparedStatement ps;
		try {
			ps = con. prepareStatement(sql);
			ps.setString(1, name );
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	
/**	public String SearchWithStudentId(String studentId) throws SQLException {
		Connection con =MyConnection.getConnection();
		String query = "select cou.name from students as stu "
				+ "inner join students_courses as scou on scou.student_id=stu.id "
				+ "inner join courses as cou on scou.course_id=cou.id where stu.id = ? ";
		String courses = "";
		
		try {

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, studentId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (courses.isBlank()) {
					courses = rs.getString("name");
				} else {
					courses += "," + rs.getString("name");
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Courses:" + courses);
		return courses;
	}**/
		
	
	public ArrayList<CourseResponseDto> selectCoursesByStudentid(String student_id) {
		Connection con =MyConnection.getConnection();
		ArrayList<CourseResponseDto> list = new ArrayList<>();
		String sql = "select courses.name, courses.id from students_courses join courses on students_courses.course_id = courses.id where students_courses.student_id = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CourseResponseDto res = new CourseResponseDto();
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				list.add(res);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	
}

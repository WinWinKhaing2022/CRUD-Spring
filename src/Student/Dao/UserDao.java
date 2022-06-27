package Student.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import Student.Dto.UserRequestDto;
import Student.Dto.UserResponseDto;

@Service("userdao")

public class UserDao {
	

public boolean checkLogin(String email,String password) {
		Connection con =MyConnection.getConnection();
		String sql="select * from user where email=? && password=?";
		try {
			System.out.println("dao : "+email+", "+password);
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Database error");
		}
		return false;
	}
	public UserResponseDto selectLoginOneEmail(String email){
		UserResponseDto res=new UserResponseDto();
		Connection con =MyConnection.getConnection();
		String sql="select * from studentdb.user where email=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setEmail(rs.getString("email"));
				res.setPassword(rs.getString("password"));
				res.setRole(rs.getString("role"));
			}
			
		} catch (SQLException e) {
			System.out.println("Database error select email");
		}
		return res;		
	}
	
	public ArrayList<UserResponseDto> selectAllUsers(){
		Connection con =MyConnection.getConnection();
		ArrayList<UserResponseDto>list=new ArrayList<UserResponseDto>();
	
		String sql="select * from studentdb.user";		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
			
				UserResponseDto res=new UserResponseDto();
				
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setEmail(rs.getString("email"));
				res.setPassword(rs.getString("password"));
				res.setRole(rs.getString("role"));
				list.add(res);
				System.out.println("Data not found"+res);
			}
		} catch (SQLException e) {
			System.out.println("Database error in selectAll");
		}
		return list;
	
	}
	
	public boolean checkEmailExist(String email) {
		Connection con =MyConnection.getConnection();
		String sql="select * from user where email=? ";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Database error");
		}
		return false;
	}
	
	public int insertUserData(UserRequestDto dto) {
		int i=0;
		Connection con =MyConnection.getConnection();
		String sql="insert into studentdb.user(id,name,email,password,role) values(?,?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2,dto.getName());
			ps.setString(3, dto.getEmail());
			ps.setString(4,dto.getPassword());
			ps.setString(5, dto.getRole());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Database error in inserting");
		}
		return i;
	}
	
	public ArrayList<UserResponseDto> selectNameSearch(String name) {
		ArrayList<UserResponseDto >list=new ArrayList<UserResponseDto>();
		String sql="select * from user where name=?";
		Connection con =MyConnection.getConnection();
		try {
			PreparedStatement ps=con. prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				UserResponseDto res=new UserResponseDto();
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setEmail(rs.getString("email"));
				res.setPassword(rs.getString("password"));
				res.setRole(rs.getString("role"));
				list.add(res);
			}
		} catch (SQLException e) {
			System.out.println("Database error");
		}
		return list;
	}
	
	public ArrayList< UserResponseDto> selectIdSearch(String id) {
		ArrayList<UserResponseDto> list=new ArrayList<UserResponseDto>();
		Connection con =MyConnection.getConnection();
		String sql="select * from user where id=?"; 
		try {
			PreparedStatement ps=con. prepareStatement(sql);
			ps.setString(1, id );
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				UserResponseDto res=new UserResponseDto(); 
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setEmail(rs.getString("email"));
				res.setPassword(rs.getString("password"));
				res.setRole(rs.getString("role"));
				list.add(res);
			}
		} catch (SQLException e) {
			System.out.println("Database error");
		}
		return list;
	}
	
	public ArrayList< UserResponseDto> selectNameAndIdSearch(String id,String name) {
		Connection con =MyConnection.getConnection();
		ArrayList<UserResponseDto> list=new ArrayList<UserResponseDto>();
		String sql="select * from user where id=? && name=?"; 
		try {
			PreparedStatement ps=con. prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				UserResponseDto res=new UserResponseDto(); 
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setEmail(rs.getString("email"));
				res.setPassword(rs.getString("password"));
				res.setRole(rs.getString("role"));
				list.add(res);
			}
		} catch (SQLException e) {
			System.out.println("Database error");
		}
		return list;
	}
	
	public UserResponseDto selectOneId(String id) {
		Connection con =MyConnection.getConnection();
		UserResponseDto res=new UserResponseDto();
		String sql="select * from user where id=? ";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setEmail(rs.getString("email"));
				res.setPassword(rs.getString("password"));
				res.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			System.out.println("Database error");
		}
		return res; 
	}
	
public UserResponseDto selectOneEmail(String email) {
	Connection con =MyConnection.getConnection();
		UserResponseDto res=new UserResponseDto();
		String sql="select * from user where email=? ";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setEmail(rs.getString("email"));
				res.setPassword(rs.getString("password"));
				res.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			System.out.println("Database error");
		}
		return res; 
	}
	
public int UpdateUserData(UserRequestDto dto) {
	Connection con =MyConnection.getConnection();
	int i=0;
	String sql="update user set name=?,email=?,password=?,role=? where id=?";
	try {
		PreparedStatement ps=con.prepareStatement(sql);
	
		ps.setString(1,dto.getName());
		ps.setString(2, dto.getEmail());
		ps.setString(3,dto.getPassword());
		ps.setString(4, dto.getRole());
		ps.setString(5, dto.getId());
		i=ps.executeUpdate();
	} catch (SQLException e) {
		System.out.println("Database error in update");
	}
	return i;
}

public int deleteUser(UserRequestDto dto) {
	Connection con =MyConnection.getConnection();
	int result=0;
	String sql="delete from user where id=?";
	try {
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, dto.getId());
		result=ps.executeUpdate();
	} catch (SQLException e) {
		System.out.println("Database error");
	}
	return result;
}
/**public ArrayList<UserResponseDto> selectUserListByIdOrName(String id, String name) {
	ArrayList<UserResponseDto> list = new ArrayList<>();
	Connection con =MyConnection.getConnection();
	String sql = "select * from user where id like ? or name like ?";
	try {
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, "%" + id + "%");
		ps.setString(2, "%" + name + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			UserResponseDto res = new UserResponseDto();
			res.setId(rs.getString("id"));
			res.setEmail(rs.getString("email"));
			res.setName(rs.getString("name"));
			res.setPassword(rs.getString("password"));
			res.setRole(rs.getString("role"));
			list.add(res);
		}
	} catch (Exception e) {
		System.out.println(e);
	}
	return list;
}**/

}	



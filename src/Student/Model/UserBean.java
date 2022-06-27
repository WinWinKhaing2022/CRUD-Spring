package Student.Model;



public class UserBean {
	
		private String id;
		private String name;
		private String email;
	
		private String password;
	
		private String cpassword;
		private String role;
		public UserBean() {
			
		}
		public UserBean(String name,String email,String password,String cpassword,String role) {
			this.name=name;
			this.email=email;
			this.password=password;
			this.cpassword=cpassword;
			this.role=role;
		}
		
		@Override
		public String toString() {
			return "UserBean [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
					+ ", cpassword=" + cpassword + ", role=" + role + "]";
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}


		public String getCpassword() {
			return cpassword;
		}


		public void setCpassword(String cpassword) {
			this.cpassword = cpassword;
		}
		
	}



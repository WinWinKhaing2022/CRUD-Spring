package Student.Model;

import javax.validation.constraints.NotEmpty;

public class CourseBean {
	@NotEmpty
		private String id;
	@NotEmpty
		private String name;
	
		



		public CourseBean() {
		}
		public CourseBean(String name) {
			super();
			this.name=name;
		}

		public CourseBean(String name, String id) {
			super();
			this.name = name;
			this.id = id;
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

		
	}



package Student.Dto;

public class CourseRequestDto {

	private String id;
	private String name;

	public CourseRequestDto() {
		
	}
	public CourseRequestDto(String name) {
		super();
		this.name=name;
	}

	

	public CourseRequestDto(String id, String name) {
		super();
		this.id = id;
		this.name = name;
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

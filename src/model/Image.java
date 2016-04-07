package model;

public class Image {

	private int id;
	private String path;
	private String caption;
	
	public Image(){}
	
	public Image(int id, String caption){
		this.id = id;
		this.caption = caption;
		path = "images/travel_images/" + id + ".jpg";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	
	
}

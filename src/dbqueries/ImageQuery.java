package dbqueries;

import java.util.ArrayList;

import model.Image;

public class ImageQuery extends DatabaseQuery {

	public ImageQuery(){}

	public ArrayList<Image> getImages(){
		ArrayList<Image> images = new ArrayList<Image>();
		images.add(new Image(5,"Some Caption"));
		images.add(new Image(1,"Some Caption"));
		images.add(new Image(2,"Some Caption"));
		images.add(new Image(3,"Some Caption"));
		images.add(new Image(4,"Some Caption"));
		return images;
	}
}


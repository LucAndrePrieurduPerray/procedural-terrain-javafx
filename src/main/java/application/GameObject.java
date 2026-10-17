package application;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;

public class GameObject extends ImageView {
	//new Image(getClass().getResourceAsStream("/images/filename"));
	private static final Image nullImage = new Image(GameObject.class.getResourceAsStream("/images/NullImage.png"));
	private HashSet<ImageView> imageSet;
	private Collider collider;
	private Trigger trigger;
	private int layer;

	public GameObject(int layer) {
		super(nullImage);
		imageSet = new HashSet<>();
		this.layer = layer;
		
		addToScene();
	}

	public GameObject() {
		super(nullImage);
		imageSet = new HashSet<>();
		layer = 0;
		
		addToScene();
	}

	public GameObject(Image image, double x, double y, double width, double height, int layer) {
		super(image);
		setBounds(x, y, width, height);
		imageSet = new HashSet<>();
		imageSet.add(this);
		this.layer = layer;

		addToScene();
	}
	
	private void addToScene() {
		SceneManager sceneManager = SceneManager.get();
		Scene scene = sceneManager.getCurrentScene();
		Group root = (Group)scene.getRoot();
		ObservableList<Node> rootList = root.getChildren();
		rootList.add(this);
		
		ObjectHandler objectHandler = ObjectHandler.get();
		objectHandler.add(this);
	}
	
	public void setCollider(double xOffset, double yOffset, double widthOffset, double heightOffset) {
		collider = new Collider(getX() + xOffset, getY() + yOffset, 
				getFitWidth() - xOffset + widthOffset, getFitHeight() - yOffset + heightOffset);
	}
	public void setTrigger(double xOffset, double yOffset, double widthOffset, double heightOffset) {
		trigger = new Trigger(getX() + xOffset, getY() + yOffset, 
				getFitWidth() - xOffset + widthOffset, getFitHeight() - yOffset + heightOffset);
	}
	
	public int getLayer() {return layer;}
	public Rectangle getCollider() {return collider;}
	public Rectangle getTrigger() {return trigger;}
	
	public boolean isCollidingWith(GameObject obj) {
		if(collider == null || obj.getCollider() == null) {
			return false;
		}
		
		return collider.getBoundsInParent().intersects(obj.getBoundsInParent());
	}
	
	public boolean isTriggeredBy(GameObject obj) {
		if(trigger == null || obj.getTrigger() == null) {
			return false;
		}
		
		return trigger.getBoundsInParent().intersects(obj.getBoundsInParent());
	}

	public void set(Image image, double x, double y, double width, double height) {
		setImage(image);
		setBounds(x, y, width, height);

		imageSet.clear();
		imageSet.add(this);
	}

	public void add(Image image1, double x1, double y1, double width1, double height1) {
		imageSet.add(this);

		Image image2 = getImage();
		double x2 = getX();
		double y2 = getY();
		double width2 = getFitWidth();
		double height2 = getFitHeight();

		BufferedImage bImg1 = SwingFXUtils.fromFXImage(image1, null);
		BufferedImage bImg2 = SwingFXUtils.fromFXImage(image2, null);

		int width = (int)(Math.max(x1 + width1, x2 + width2) - Math.min(x1, x2));
		int height = (int)(Math.max(y1 + height1, y2 + height2) - Math.min(y1, y2));

		BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = combinedImage.createGraphics();

		double x = Math.min(x1, x2);
		double y = Math.min(y1, y2);

		g.drawImage(bImg2, (int)(x2 - x), (int)(y2 - y), (int)width2, (int)height2, null);
		g.drawImage(bImg1, (int)(x1 - x), (int)(y1 - y), (int)width1, (int)height1, null);
		g.dispose();

		WritableImage img = SwingFXUtils.toFXImage(combinedImage, null);
		setImage(img);
		setBounds(Math.min(x1, x2), Math.min(y1, y2), width, height);
	}

	public void add(GameObject obj) {
		imageSet.add(this);

		Image image1 = obj.getImage();
		double x1 = obj.getX();
		double y1 = obj.getY();
		double width1 = obj.getFitWidth();
		double height1 = obj.getFitHeight();
		
		Image image2 = getImage();
		double x2 = getX();
		double y2 = getY();
		double width2 = getFitWidth();
		double height2 = getFitHeight();

		BufferedImage bImg1 = SwingFXUtils.fromFXImage(image1, null);
		BufferedImage bImg2 = SwingFXUtils.fromFXImage(image2, null);

		int width = (int)(Math.max(x1 + width1, x2 + width2) - Math.min(x1, x2));
		int height = (int)(Math.max(y1 + height1, y2 + height2) - Math.min(y1, y2));

		BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = combinedImage.createGraphics();

		double x = Math.min(x1, x2);
		double y = Math.min(y1, y2);

		g.drawImage(bImg2, (int)(x2 - x), (int)(y2 - y), (int)width2, (int)height2, null);
		g.drawImage(bImg1, (int)(x1 - x), (int)(y1 - y), (int)width1, (int)height1, null);
		g.dispose();

		WritableImage img = SwingFXUtils.toFXImage(combinedImage, null);
		setImage(img);
		setBounds(Math.min(x1, x2), Math.min(y1, y2), width, height);

		ObjectHandler objectHandler = ObjectHandler.get();
		objectHandler.remove(obj);
		obj.setBounds(0, 0, 0.01, 0.01);
	}
	
	/*
	public void removeImgAt(double x, double y, double width, double height){
		Iterator<ImageView> imageIter = imageSet.iterator();
		
		while(imageIter.hasNext()) {
			ImageView imgView = imageIter.next();

			if(x == imgView.getX() &&
					y == imgView.getY() &&
					width == imgView.getFitWidth() &&
					height == imgView.getFitHeight()) {
				imageSet.remove(imgView);
			}
		}
		@SuppressWarnings("unchecked")
		ArrayList<ImageView> imgList = (ArrayList<ImageView>)imageSet.clone();
		if(imgList.size() == 0) {
			imgList.add(new ImageView(nullImage));
		}

		ImageView imgView = imgList.remove(0);
		setImage(imgView.getImage(), imgView.getX(), imgView.getY(), imgView.getFitWidth(), imgView.getFitHeight());

		for(ImageView img : imgList) {
			add(img.getImage(), img.getX(), img.getY(), img.getFitWidth(), img.getFitHeight());
		}
	}
	*/

	/*
	public void removePreviousImages(int x){
		if(imageSet.size() == 0 || x == 0) {
			return;
		}

		for(int i = 0; i < x; i++) {
			if(imageSet.size() != 0) {
				imageSet.remove(imageSet.size() - 1);
			}
		}
		@SuppressWarnings("unchecked")
		ArrayList<ImageView> imgList = (ArrayList<ImageView>)imageSet.clone();
		if(imgList.size() == 0) {
			imgList.add(new ImageView(nullImage));
		}

		ImageView imgView = imgList.remove(0);
		setImage(imgView.getImage(), imgView.getX(), imgView.getY(), imgView.getFitWidth(), imgView.getFitHeight());

		for(ImageView img : imgList) {
			add(img.getImage(), img.getX(), img.getY(), img.getFitWidth(), img.getFitHeight());
		}
	}
	*/
	
	public void flipImage() {
		Image image = getImage();
		double width = getFitWidth();
		double height = getFitHeight();

		BufferedImage bImg = SwingFXUtils.fromFXImage(image, null);
		BufferedImage buffImg = new BufferedImage((int)width, (int)height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = buffImg.createGraphics();
		g.drawImage(bImg, (int)(width), 0, -(int)width, (int)height, null);

		WritableImage img = SwingFXUtils.toFXImage(buffImg, null);
		setImage(img);
	}

	public void setBounds(double x, double y, double width, double height) {
		if(collider != null) {
			collider.setBounds(collider.getX() + (x - getX()), collider.getY() + (y - getY()), 
					collider.getWidth() + (width - getFitWidth()), collider.getHeight() + (height - getFitHeight()));
		}
		if(trigger != null) {
			trigger.setBounds(trigger.getX() + (x - getX()), trigger.getY() + (y - getY()), 
					trigger.getWidth() + (width - getFitWidth()), trigger.getHeight() + (height - getFitHeight()));
		}
		
		setX(x);
		setY(y);
		setFitWidth(width);
		setFitHeight(height);
	}

	public String toString() {
		return getImage() + " " + getX() + " " + getY() + " " + getFitWidth() + " " + getFitHeight();
	}
}











package application;

import java.util.HashSet;
import java.util.Iterator;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class MainLevel {
	public MainLevel() {
		new Camera(0, 0);
		Group root = new Group();
		Scene scene = new Scene(root, SceneManager.DEFAULT_WIDTH, SceneManager.DEFAULT_HEIGHT);
		SceneManager sceneManager = SceneManager.get();

		sceneManager.addScene("mainLevel", scene);
		sceneManager.loadScene("mainLevel");
		Player player = new Player();
		
		HashSet<int[][]> terrainSet = createTerrain();
		Iterator<int[][]> iter = terrainSet.iterator();
		int yOffset = 0;

		Image groundImage = new Image(getClass().getResourceAsStream("/images/PokemonGround2.png"));
		GameObject ground = new GameObject();
		GameObject groundTemp = new GameObject();
		GameObject[] groundTempArr = createGroundTempArr(groundImage, groundTemp);

		Image grassImage = new Image(getClass().getResourceAsStream("/images/PokemonTallGrass2.png"));
		GameObject grassTemp = new GameObject(1);
		GameObject[] grassTempArr = createGrassTempArr(grassImage, grassTemp);

		ObjectHandler objectHandler = ObjectHandler.get();
		objectHandler.remove(groundTemp);
		groundTemp.setBounds(0, 0, 0.01, 0.01);

		objectHandler.remove(grassTemp);
		grassTemp.setBounds(0, 0, 0.01, 0.01);
		createGround(iter, yOffset, ground, groundTempArr, grassTempArr);

		Image treeImage = new Image(getClass().getResourceAsStream("/images/PokemonTree2.png"));
		GameObject trees = new GameObject(-1);
		trees.setBounds(-2000, -8650, 0, 0);
		trees.add(treeImage, -2000, -8650, 80, 90);
		createTrees(trees);

		player.setBounds(241, 1, 78, 78);
		CameraHandler cameraHandler = CameraHandler.get();
		Camera camera = cameraHandler.getActiveCamera();
		camera.setBounds(-1920 / 2 + 40 + 240, -1080 / 2 + 40);
	}

	private HashSet<int[][]> createTerrain() {
		HashSet<int[][]> terrainMap = new HashSet<>();

		for(int i = 0; i < (int)(Math.random() * 5 + 5); i++) {
			int rows = (int)(Math.random() * 9 + 7);
			int cols = (int)(Math.random() * 9 + 7);

			int grassPatchNum = (int)(Math.random() * 3 + 3);
			int[][] terrainMat = new int[rows][cols];

			for(int j = 0; j < grassPatchNum; j++) {
				terrainMat = setGrass(rows, cols, grassPatchNum, terrainMat);
			}
			terrainMap.add(terrainMat);
		}
		return terrainMap;
	}

	private int[][] setGrass(int rows, int cols, int grassPatchNum, int[][] terrainMat) {
		int grassRows = (int)(Math.random() * (rows / (grassPatchNum - 2)) + 3);
		int grassCols = (int)(Math.random() * (cols / (grassPatchNum - 2)) + 3);

		int grassRow = (int)(Math.random() * (rows - 3));
		int grassCol = (int)(Math.random() * (cols - 3));

		for(int r = 0; r < grassRows; r++) {
			for(int c = 0; c < grassCols; c++) {
				int row = grassRow + r;
				int col = grassCol + c;

				if(row < terrainMat.length && col < terrainMat[0].length) {
					terrainMat[row][col] = 1;
				}
			}
		}
		return terrainMat;
	}

	private GameObject[] createGroundTempArr(Image groundImage, GameObject groundTemp) {
		GameObject[] groundTempArr = new GameObject[9];

		for(int c = 0; c < 15; c++) {
			groundTemp.add(groundImage, c * 80, 0, 80, 80);

			if(c > 5) {
				groundTempArr[c - 6] = new GameObject(groundTemp.getImage(), 0, 0, 0.01, 0.01, 0);

				ObjectHandler objectHandler = ObjectHandler.get();
				objectHandler.remove(groundTempArr[c - 6]);
			}
		}
		return groundTempArr;
	}

	private GameObject[] createGrassTempArr(Image grassImage, GameObject grassTemp) {
		GameObject[] grassTempArr = new GameObject[15];

		for(int c = 0; c < 15; c++) {
			grassTemp.add(grassImage, c * 80, 0, 80, 80);
			grassTempArr[c] = new GameObject(grassTemp.getImage(), 0, 0, 0.01, 0.01, 0);

			ObjectHandler objectHandler = ObjectHandler.get();
			objectHandler.remove(grassTempArr[c]);
		}
		return grassTempArr;
	}

	private void createGround(Iterator<int[][]> iter, int yOffset, GameObject ground, GameObject[] groundTempArr, GameObject[] grassTempArr) {
		int prevX = Integer.MIN_VALUE;
		int prevWidth = Integer.MAX_VALUE;
		GameObject prevBarrier1 = null;
		GameObject prevBarrier2 = null;
		int prevRandomOffset = 0;

		GameObject bottomBarrier = new GameObject();
		bottomBarrier.setBounds(-400, 80, 2000, 80);
		bottomBarrier.setCollider(0, 0, 0, 0);
		//bottomBarrier.setImage(new Image(getClass().getResourceAsStream("/images/TestingImage.png")));

		while(iter.hasNext()) {
            int[][] terrainMat = iter.next();
			int xOffset = (int)(Math.random() * 7 - 3) * 80;

			GameObject leftTreeBarrier = new GameObject();
			leftTreeBarrier.setBounds(-400, -yOffset, 80, 80);
			leftTreeBarrier.setCollider(0, 0, 0, 0);
			//leftTreeBarrier.setImage(new Image(getClass().getResourceAsStream("/images/TestingImage.png")));

			GameObject rightTreeBarrier = new GameObject(5);
			rightTreeBarrier.setBounds(1520, -yOffset, 80, 80);
			rightTreeBarrier.setCollider(0, 0, 0, 0);
			//rightTreeBarrier.setImage(new Image(getClass().getResourceAsStream("/images/TestingImage.png")));

			for(int r = 0; r < terrainMat.length; r++) {
				groundTempArr[terrainMat[0].length - 7].setBounds(xOffset, -(yOffset + r * 80), terrainMat[0].length * 80, 80);
				ground.add(groundTempArr[terrainMat[0].length - 7]);
			}
			addGrass(terrainMat, grassTempArr, xOffset, yOffset);

			for(GameObject obj : grassTempArr) {
				obj.setBounds(0, 0, 0.01, 0.01);
			}
			groundTempArr[terrainMat[0].length - 7].setBounds(0, 0, 0.01, 0.01);

			int randomOffset = (int)(Math.random() * 4) * 80;
			yOffset += terrainMat.length * 80;

			if(iter.hasNext()) {
				yOffset -= randomOffset;
			}
			leftTreeBarrier.setBounds(leftTreeBarrier.getX(), -yOffset + 80, xOffset - leftTreeBarrier.getX(), leftTreeBarrier.getY() + yOffset);
			rightTreeBarrier.setBounds(xOffset + terrainMat[0].length * 80, leftTreeBarrier.getY(), 1600 - (xOffset + terrainMat[0].length * 80), leftTreeBarrier.getFitHeight());

			if(leftTreeBarrier.getFitWidth() > prevWidth) {
				leftTreeBarrier.setBounds(leftTreeBarrier.getX(), leftTreeBarrier.getY(), leftTreeBarrier.getFitWidth(), leftTreeBarrier.getFitHeight() - prevRandomOffset);
				prevBarrier1.setBounds(prevBarrier1.getX(), prevBarrier1.getY() - prevRandomOffset, prevBarrier1.getFitWidth(), prevBarrier1.getFitHeight() + prevRandomOffset);
			}

			if(rightTreeBarrier.getX() < prevX) {
				rightTreeBarrier.setBounds(rightTreeBarrier.getX(), rightTreeBarrier.getY(), rightTreeBarrier.getFitWidth(), rightTreeBarrier.getFitHeight() - prevRandomOffset);
				prevBarrier2.setBounds(prevBarrier2.getX(), prevBarrier2.getY() - prevRandomOffset, prevBarrier2.getFitWidth(), prevBarrier2.getFitHeight() + prevRandomOffset);
			}
			prevX = (int)rightTreeBarrier.getX();
			prevWidth = (int)leftTreeBarrier.getFitWidth();
			prevBarrier1 = leftTreeBarrier;
			prevBarrier2 = rightTreeBarrier;
			prevRandomOffset = randomOffset;
		}
		GameObject topBarrier = new GameObject();
		topBarrier.setBounds(-400, -yOffset, 2000, 80);
		topBarrier.setCollider(0, 0, 0, 0);
		//topBarrier.setImage(new Image(getClass().getResourceAsStream("/images/TestingImage.png")));
	}

	private void addGrass(int[][] terrainMat, GameObject[] grassTempArr, int xOffset, int yOffset) {
		while(!isEmpty(terrainMat)) {
			for(int r = 0; r < terrainMat.length; r++) {
				for(int c = 0; c < terrainMat[0].length; c++) {
					if(terrainMat[r][c] == 1) {
						GameObject grassObj = new GameObject(1);
						grassObj.setBounds(xOffset + c * 80, -(yOffset + r * 80), 0, 0);
						grassObj.setTrigger(0, 0, 0, 0);
						int grassColNum = 0;

						for(int col = c; col < terrainMat[0].length; col++) {
							if(terrainMat[r][col] == 1) {
								grassColNum++;
							} else {
								col = terrainMat[0].length;
							}
						}

						for(int row = r; row < terrainMat.length; row++) {
							if(isFull(terrainMat, row, c, c + grassColNum)) {
								grassTempArr[grassColNum - 1].setBounds(xOffset + c * 80, -(yOffset + row * 80), grassColNum * 80, 80);
								grassObj.add(grassTempArr[grassColNum - 1]);

								setEmpty(terrainMat, row, c, c + grassColNum);
							} else {
								row = terrainMat.length;
							}
						}
						r = terrainMat.length;
						c = terrainMat[0].length;
					}
				}
			}
		}
	}

	private void createTrees(GameObject trees) {
		for(int i = 0; i < 6; i++) {
			trees.add(trees.getImage(), trees.getX() + trees.getFitWidth(), trees.getY(), trees.getFitWidth(), trees.getFitHeight());
		}

		for(int i = 0; i < 7; i++) {
			trees.add(trees.getImage(), trees.getX(), trees.getY() + trees.getFitHeight() - 10, trees.getFitWidth(), trees.getFitHeight());
		}
	}

	private boolean isEmpty(int[][] mat) {
		for(int r = 0; r < mat.length; r++) {
			for(int c = 0; c < mat[r].length; c++) {
				if(mat[r][c] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isFull(int[][] mat, int r, int initialCol, int endCol) {
		for(int c = initialCol; c < endCol; c++) {
			if(mat[r][c] != 1) {
				return false;
			}
		}
		return true;
	}

	private boolean setEmpty(int[][] mat, int r, int initialCol, int endCol) {
		for(int c = initialCol; c < endCol; c++) {
			mat[r][c] = 0;
		}
		return true;
	}
}

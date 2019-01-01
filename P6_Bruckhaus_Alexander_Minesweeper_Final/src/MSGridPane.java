import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MSGridPane extends Group implements GridListener {
	private double tileSize;
	private ImageView[][] cells;
	private P6_Bruckhaus_Alexander_MinesweeperModel model;

	public MSGridPane() {
		this.model = null;
		this.cells = null;
		this.tileSize = 30;
	}

	public void setTileSize(double size) {
		this.tileSize = size;
		resetCells();
	}

	public double getTileSize() {
		return this.tileSize;
	}

	public void setModel(P6_Bruckhaus_Alexander_MinesweeperModel model) {
		model.addListener(this);
		this.model = model;
		resetCells();
	}

	public void resetCells() {
		getChildren().remove(0, getChildren().size());
		cells = new ImageView[model.getNumRows()][model.getNumCols()];
		for (int row = 0; row < model.getNumRows(); row++) {
			for (int col = 0; col < model.getNumCols(); col++) {
				char c = model.getCellContent(row, col, false);
				ImageView imageView = getImageViewForChar(c);
				imageView.setX(xPosForCol(col)); 
				imageView.setY(yPosForRow(row));
				imageView.setFitHeight(tileSize);
				imageView.setFitWidth(tileSize);
				imageView.setPreserveRatio(true);
				getChildren().add(imageView);
				cells[row][col] = imageView;
			}
		}
	}

	private ImageView getImageViewForChar(char c) {
		if (c == model.COVER) {
			return getImageViewForGif("file:images/blank.gif");
		} else if (c == model.QUESTION_MARK) {
			return getImageViewForGif("file:images/bomb_question.gif");
		} else if (c == model.FLAG) {
			return getImageViewForGif("file:images/bomb_flagged.gif");
		} else if (c == model.BLANK) {
			return getImageViewForGif("file:images/num_0.gif");
		} else if (c == model.MINE) {
			return getImageViewForGif("file:images/bomb_revealed.gif");
		} else if (c == model.EXPLODED) {
			return getImageViewForGif("file:images/bomb_death.gif");
		} else if (c == model.CROSSED) {
			return getImageViewForGif("file:images/bomb_wrong.gif");
		} else {
			String gif = "file:images/num_" + c + ".gif";
			return getImageViewForGif(gif);
		}
	}

	private ImageView getImageViewForGif(String gif) {
		Image i;
		i = new Image(gif);
		ImageView imageView1 = new ImageView();
		imageView1.setImage(i);
		return imageView1;
	}

	public double xPosForCol(int col) {
		return col * tileSize;
	}

	public double yPosForRow(int row) {
		return row * tileSize;
	}

	public int colForXPos(double x) {
		return (int)(x / tileSize);
	}

	public int rowForYPos(double y) {
		return (int)(y / tileSize);
	}
}

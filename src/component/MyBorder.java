package component;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;

@SuppressWarnings("exports")
public class MyBorder {
	
	Border blackBorder;
	Border grayBorder;
	
	private void init() {
		blackBorder = new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))
				);
		grayBorder = new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(2))
				);
	}
	
	public MyBorder() {
		init();
	}
	
	public Border getBlackBorder() {
		return this.blackBorder;
	}
	
	public Border getGrayBorder() {
		return this.grayBorder;
	}
	
	
}

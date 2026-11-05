package pobj.tme6.extra;

import java.util.Collections;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pobj.tme6.extra.dvg.Vector;

public class AsteroidsScreen {

	private Canvas canvas;

	public AsteroidsScreen(Stage stage, AsteroidsKeyListener keys) {
		stage.setTitle("Asteroids");
		stage.setResizable(false);
		canvas = new Canvas(768, 768);
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox);
		vbox.getChildren().addAll(canvas);
		stage.setScene(scene);		
		scene.setOnKeyPressed(e -> keys.keyPressed(e));
		scene.setOnKeyReleased(e -> keys.keyReleased(e));
		stage.show();
		drawFrame(Collections.<Vector>emptyList());
	}
		
	@SuppressWarnings("exports")
	public void drawFrame(List<Vector> displayList) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		double width = canvas.getWidth();
		double height = canvas.getHeight();
		
		gc.setFill(Color.BLACK);
		gc.fillRect(0,  0,  width, height);

		gc.setLineWidth(2);
	    for (Vector v : displayList) {
	    	double c = Math.pow(v.getBrightness()/15., 0.8);
		    gc.setStroke(Color.color(1.,1.,1.,c));
		    int x0 = (int) (v.getX0() * width / 1024.);
		    int x1 = (int) (v.getX1() * width / 1024.);
		    int y0 = (int) ((1023-v.getY0()) * height / 1024.);
		    int y1 = (int) ((1023-v.getY1()) * height / 1024.);
		    gc.strokeLine(x0, y0, x1, y1);
	    }
	}
}

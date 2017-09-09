import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;

import javax.print.SimpleDoc;
import java.util.Random;

/**
 * Created by George Melissourgos on ${Date}
 * melissourgos@outlook.com
 * geomelkon@csd.auth.gr
 */
public class RainDrop extends Pane {
    private static Random rand = new Random();

    private Line drop;

    private double startY = -1*(rand.nextInt(200)+10);
    private double startX;

    private final double SIZE = (rand.nextInt(20)+1)*0.1;

    private final double  SPEED = 600;
    private double animationTime;

    private SimpleDoubleProperty endX;
    private SimpleDoubleProperty endY;


    public RainDrop(ReadOnlyDoubleProperty endX, ReadOnlyDoubleProperty endY) {
        this.endX = new SimpleDoubleProperty();
        this.endY = new SimpleDoubleProperty();

        this.endX.bind(endX);
        this.endY.bind(endY);

        startX = endX.getValue()*(rand.nextInt(100)/100.0);
        setLayoutX(startX);
        setLayoutY(startY);

        //Calculate animation time (x = u*t):
        animationTime = (((endY.getValue()+20)- startY)/SPEED) * (1+SIZE) ;

        drop= new Line(0,0,0,25-SIZE);

        drop.setOpacity(1 - SIZE/3);

        drop.setStroke(Paint.valueOf("#e0e0e0"));
        drop.setStrokeLineCap(StrokeLineCap.BUTT);
        drop.setStyle("-fx-stroke-width:"+SIZE+";");

        //drop.setEffect(new BoxBlur(0.5,0.5,1));

        getChildren().add(drop);
    }

    public void fall(){
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(drop.layoutYProperty(),endY.getValue()+Math.abs(startY)+20,Interpolator.LINEAR);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(animationTime*1000),keyValue);
        timeline.getKeyFrames().add(keyFrame);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}

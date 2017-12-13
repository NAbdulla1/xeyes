package sample;

import com.sun.javafx.geom.Vec2d;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class Main extends Application {

    private Pane root;
    private Ellipse leftEye;
    private Ellipse rightEye;
    private Circle leftBall;
    private Circle rightBall;
    private final int offset = 50;

    @Override
    public void start(Stage primaryStage) throws Exception{
        makeScene();
        root.setOnMouseMoved((mouseEvent)-> handleMouseMove(mouseEvent));

        primaryStage.setResizable(false);
        primaryStage.setTitle("xeyes");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void makeScene(){
        root = new Pane();
        root.setMinSize(1000, 700);

        double lcx = root.getMinWidth()/2-offset;
        double lcy = root.getMinHeight()/2;
        double rcx = root.getMinWidth()/2+offset;
        double rcy = root.getMinHeight()/2;
        double r = offset-10;

        Circle faceBack = new Circle(root.getMinWidth()/2, root.getMinHeight()/2+r/3, offset+r+2+10);
        Circle face = new Circle(root.getMinWidth()/2, root.getMinHeight()/2+r/3, offset+r+10);
        face.setFill(Color.WHEAT);

        Ellipse leftEyeBack = new Ellipse(lcx, lcy, r+2, r+2+10);
        Ellipse rightEyeBack = new Ellipse(rcx, rcy, r+2, r+2+10);
        Ellipse nose = new Ellipse(root.getMinWidth()/2, root.getMinHeight()/2+2*r, 20, 15);

        leftEye = new Ellipse(lcx, lcy, r, r+8);
        rightEye = new Ellipse(rcx, rcy, r, r+8);

        leftEye.setFill(Color.WHITE);
        rightEye.setFill(Color.WHITE);

        leftBall = new Circle(lcx, lcy, 10);
        rightBall = new Circle(rcx, rcy, 10);

        root.getChildren().addAll(faceBack, face, leftEyeBack, rightEyeBack, nose, leftEye, rightEye, leftBall, rightBall);
    }

    private void handleMouseMove(MouseEvent event){
        Vec2d leftV = new Vec2d(event.getX()-leftEye.getCenterX(), event.getY()-leftEye.getCenterY());
        Vec2d rightV = new Vec2d(event.getX()-rightEye.getCenterX(), event.getY()-rightEye.getCenterY());

        double distL = leftV.distance(0, 0);
        double distR = rightV.distance(0, 0);

        leftV.set(leftV.x/distL, leftV.y/distL);
        rightV.set(rightV.x/distR, rightV.y/distR);

        distL = Math.min(distL, leftEye.getRadiusX()-10);
        distR = Math.min(distR, rightEye.getRadiusX()-10);

        leftV.set(leftV.x*distL, leftV.y*distL);
        rightV.set(rightV.x*distR, rightV.y*distR);

        leftBall.setCenterX(leftEye.getCenterX()+leftV.x);
        leftBall.setCenterY(leftEye.getCenterY()+leftV.y);
        rightBall.setCenterX(rightEye.getCenterX()+rightV.x);
        rightBall.setCenterY(rightEye.getCenterY()+rightV.y);
    }
}

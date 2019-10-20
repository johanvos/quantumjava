package com.gluonhq.javaqc.ch05.maryqubit;

import com.gluonhq.strange.Program;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class Main extends Application {

    static final int SCALE = 4;
    static final int SPRITE_SIZE = 32;
    static final int CELL_SIZE = SPRITE_SIZE * SCALE;
    static final int HORIZONTAL_CELLS = 10;
    static final int VERTICAL_CELLS = 6;
    static final int BOARD_WIDTH = HORIZONTAL_CELLS * CELL_SIZE;
    static final int BOARD_HEIGHT = VERTICAL_CELLS * CELL_SIZE;
    static MapObject[][] map = new MapObject[HORIZONTAL_CELLS][VERTICAL_CELLS];

    private MapObject.Rainbow rainbow;
    private MapObject.ChickenCoop chickenCoop;
    private MapObject.Barn barn;
    private MapObject.Nest nest;

    private StrangeBridge strangeBridge;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mary Had a Little Qubit");
        strangeBridge = new StrangeBridge();

        barn = new MapObject.Barn(new Location(2, 3), strangeBridge);
        rainbow = new MapObject.Rainbow(new Location(5, 0), strangeBridge);
        chickenCoop = new MapObject.ChickenCoop(new Location(5, 4), strangeBridge);
        nest = new MapObject.Nest(new Location(3, 4), strangeBridge);
        Group root = new Group();
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT, Color.WHITE);
        primaryStage.setScene(scene);
        populateBackground(root);
        scene.getStylesheets().add(Main.class.getResource("/styles.css").toExternalForm());

        // This shows object generation:
        root.getChildren().add(barn);
        // This one shows filtering
        root.getChildren().add(rainbow);
        // This one demonstrates the additions to the List API:
        root.getChildren().add(new MapObject.Church(new Location(6, 2), strangeBridge));
        // This demonstrates Map:
        root.getChildren().add(chickenCoop);
        // And this one FlatMap:
        root.getChildren().add(nest);
        // And finally aggregation:
        MapObject.Fox fox = new MapObject.Fox(new Location(7, 4), strangeBridge);
        fox.setDirection(Direction.LEFT);
        fox.setScaleX(.5);
        fox.setScaleY(.5);
        root.getChildren().add(fox);

        SpriteView.Mary mary = new SpriteView.Mary(new Location(0, 3));
        populateCells(root, mary);
        strangeBridge.setOpacity(0.5);
        root.getChildren().add(strangeBridge);
        root.getChildren().add(mary);
        addKeyHandler(scene, mary);

        primaryStage.show();
    }

    private void populateBackground(Group root) {
        // Image by Victor Szalvay: http://www.flickr.com/photos/55502991@N00/172603855
        ImageView background = new ImageView(getClass().getResource("images/field.jpg").toString());
        background.setFitHeight(BOARD_HEIGHT);
        root.getChildren().add(background);

    }
    private void populateCells(Group root, final SpriteView.Mary mary) {
        // Gratuitous use of lambdas to do nested iteration!
        Group cells = new Group();
        IntStream.range(0, HORIZONTAL_CELLS).mapToObj(i ->
            IntStream.range(0, VERTICAL_CELLS).mapToObj(j -> {
                Rectangle rect = new Rectangle(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                rect.setFill(Color.rgb(0, 0, 0, 0));
                rect.setStrokeType(StrokeType.INSIDE);
                rect.setStroke(Color.BLACK);
                rect.setOnMousePressed(e -> mary.moveTo(new Location(i, j)));
                return rect;
            })
        ).flatMap(s -> s).forEach(cells.getChildren()::add);
        root.getChildren().add(cells);
    }

    private void addKeyHandler(Scene scene, SpriteView.Shepherd mary) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, ke -> {
            KeyCode keyCode = ke.getCode();
            switch (keyCode) {
                case W:
                case UP:
                    mary.move(Direction.UP);
                    break;
                case A:
                case LEFT:
                    mary.move(Direction.LEFT);
                    break;
                case S:
                case DOWN:
                    mary.move(Direction.DOWN);
                    break;
                case D:
                case RIGHT:
                    mary.move(Direction.RIGHT);
                    break;
                case X:
                    chickenCoop.toggleActive();
                    break;
                case H:
                    nest.toggleActive();
                    break;
                case C:
                    rainbow.toggleActive();
                    break;
                case ESCAPE:
                    Platform.exit();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static enum Direction {
        DOWN(0), LEFT(1), RIGHT(2), UP(3);
        private final int offset;
        Direction(int offset) {
            this.offset = offset;
        }
        public int getOffset() {
            return offset;
        }
        public int getXOffset() {
            switch (this) {
                case LEFT:
                    return -1;
                case RIGHT:
                    return 1;
                default:
                    return 0;
            }
        }
        public int getYOffset() {
            switch (this) {
                case UP:
                    return -1;
                case DOWN:
                    return 1;
                default:
                    return 0;
            }
        }
    }

    public static class Location {
        int cell_x;
        int cell_y;
        public Location(int cell_x, int cell_y) {
            this.cell_x = cell_x;
            this.cell_y = cell_y;
        }
        public int getX() {
            return cell_x;
        }
        public int getY() {
            return cell_y;
        }
        public Location offset(int x, int y) {
            return new Location(cell_x + x, cell_y + y);
        }
        public Direction directionTo(Location loc) {
            if (Math.abs(loc.cell_x - cell_x) > Math.abs(loc.cell_y - cell_y)) {
                return (loc.cell_x > cell_x) ? Direction.RIGHT : Direction.LEFT;
            } else {
                return (loc.cell_y > cell_y) ? Direction.DOWN : Direction.UP;
            }
        }
        @Override
        public String toString() {
            return "Location{" +
                "cell_x=" + cell_x +
                ", cell_y=" + cell_y +
                '}';
        }
    }

}

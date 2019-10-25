/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2019, Johan Vos and Stephen Chin
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.gluonhq.javaqc.ch05.maryqubit;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.stream.Stream;

public class SpriteView extends StackPane {
    final ImageView imageView;
    private Color color;
    EventHandler<ActionEvent> arrivalHandler;
    double colorOffset;
    public void setDirection(Main.Direction direction) {
        this.direction.setValue(direction);
    }

    public static class Mary extends Shepherd {
        // Image by Terra-chan: http://www.rpgmakervx.net/index.php?showtopic=29404
        static final Image MARY = loadImage("images/mary.png");
        final Main parent;
        public Mary(Main.Location loc, Main main) {
            super(MARY, loc);
            this.parent = main;
        }
    }

    public static class Shepherd extends SpriteView {
        private ObservableList<SpriteView> animals;
        public ObservableList<SpriteView> getAnimals() {
            return animals;
        }
        public Shepherd(Image spriteSheet, Main.Location loc) {
            super(spriteSheet, loc);
            animals = FXCollections.observableArrayList();
            animals.addListener((ListChangeListener) c -> {
                ObservableList<Node> children = ((Group) getParent()).getChildren();
                while (c.next()) {
                    if (c.wasAdded() || c.wasRemoved() || c.wasReplaced()) {
                        children.removeAll(c.getRemoved());
                        children.addAll(c.getAddedSubList());
                        SpriteView prev = this;
                        int number = -1;
                        for (SpriteView a : animals) {
                            a.following = prev;
                            a.number.set(++number);
                            prev.follower = a;
                            prev = a;
                        }
                    }
                }
            });

        }
        public void move(Main.Direction direction) {
            if (walking != null && walking.getStatus().equals(Animation.Status.RUNNING))
                return;
            int lx = location.getValue().getX();
            int ly = location.getValue().getY();
            int dx = direction.getXOffset();
            int dy = direction.getYOffset();
            if (animals.size() < 1) {
                Main.setHelpText("Visit the barn to get a qubitlamb");
            }
            if ((dx < 0 && lx <1) || (dy < 0 && ly <1)) return;
            if ((dx > 0 && lx > Main.HORIZONTAL_CELLS-2) || (dy > 0 && ly > Main.VERTICAL_CELLS-2)) return;
            moveTo(location.getValue().offset(direction.getXOffset(), direction.getYOffset()));
            animals.stream().reduce(location.get(),
                (loc, sprt) -> {
                    sprt.moveTo(loc);
                    return sprt.location.get();
                }, (loc1, loc2) -> loc1);
        }
    }

    public static class Lamb extends NumberedSpriteView {
        // Image by Mack: http://www.rpgmakervx.net/index.php?showtopic=15704
        static final Image LAMB = loadImage("images/lamb.png");

        private final DoubleProperty valueProperty = new SimpleDoubleProperty(0);

        private ChangeListener<Main.Direction> directionListener = (ov, o, o2) -> {
            switch (o2) {
                case RIGHT:
                    label.setTranslateX(-4 * Main.SCALE);
                    label.setTranslateY(2 * Main.SCALE);
                    break;
                case LEFT:
                    label.setTranslateX(4 * Main.SCALE);
                    label.setTranslateY(2 * Main.SCALE);
                    break;
                case UP:
                    label.setTranslateX(0);
                    label.setTranslateY(-2 * Main.SCALE);
                    break;
                case DOWN:
                    label.setTranslateX(0);
                    label.setTranslateY(-9 * Main.SCALE);
                    break;
            }
        };
        public Lamb(SpriteView following) {
            super(LAMB, following);
            direction.addListener(directionListener);
            directionListener.changed(direction, direction.getValue(), direction.getValue());
            startValueAnimation();
            valueProperty.addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    double value = valueProperty.get();
                    if (value > .5){
                        imageView.setEffect(new InnerShadow(150, Color.BLACK));
                    } else {
                        imageView.setEffect(null);
                    }
                }
            });
        }

        public void setValue(double d) {
            valueProperty.set(d);
        }
    }

    public static class NumberedSpriteView extends SpriteView {
        protected final Label label = new Label();
        public NumberedSpriteView(Image spriteSheet, SpriteView following) {
            super(spriteSheet, following);
            label.textProperty().bind(number.asString());
            label.setFont(Font.font("Impact", 12 * Main.SCALE));
            getChildren().add(label);
        }
    }

    private SpriteView following;
    IntegerProperty number = new SimpleIntegerProperty(0);

    public int getNumber() {
        return number.get();
    }

    public SpriteView(Image spriteSheet, SpriteView following) {
        this(spriteSheet, following.getLocation().offset(-following.getDirection().getXOffset(), -following.getDirection().getYOffset()));
        number.set(following.number.get() + 1);
        this.following = following;
        setDirection(following.getDirection());
        following.follower = this;
        setMouseTransparent(true);
    }
    public SpriteView getFollowing() {
        return following;
    }

    ObjectProperty<Main.Direction> direction = new SimpleObjectProperty<>();
    ObjectProperty<Main.Location> location = new SimpleObjectProperty<>();
    IntegerProperty frame = new SimpleIntegerProperty(1);
    int spriteWidth;
    int spriteHeight;
    Timeline walking;
    SpriteView follower;
    DoubleProperty value = new SimpleDoubleProperty(0);

    static Image loadImage(String url) {
        return new Image(SpriteView.class.getResource(url).toString(), Main.SPRITE_SIZE * 3 * Main.SCALE, Main.SPRITE_SIZE * 4 * Main.SCALE, true, false);
    }

    public SpriteView(Image spriteSheet, Main.Location loc) {
        imageView = new ImageView(spriteSheet);
        this.location.set(loc);
        setTranslateX(loc.getX() * Main.CELL_SIZE);
        setTranslateY(loc.getY() * Main.CELL_SIZE);
        ChangeListener<Object> updateImage = (ov, o, o2) -> imageView.setViewport(
            new Rectangle2D(frame.get() * spriteWidth,
                direction.get().getOffset() * spriteHeight,
                spriteWidth, spriteHeight));
        direction.addListener(updateImage);
        frame.addListener(updateImage);
        spriteWidth = (int) (spriteSheet.getWidth() / 3);
        spriteHeight = (int) (spriteSheet.getHeight() / 4);
        direction.set(Main.Direction.RIGHT);
        getChildren().add(imageView);
        arrivalHandler = e -> {
            MapObject object = Main.map[location.get().getX()][location.get().getY()];
            if (object != null) {
                object.visit(this);
            }
        };
    }

    public void startAnimation() {
        Timeline timeline = new Timeline(Animation.INDEFINITE,
            new KeyFrame(Duration.seconds(.25), new KeyValue(frame, 0)),
            new KeyFrame(Duration.seconds(.5), new KeyValue(frame, 1)),
            new KeyFrame(Duration.seconds(.75), new KeyValue(frame, 2)),
            new KeyFrame(Duration.seconds(1), new KeyValue(frame, 1))
        );
        timeline.onFinishedProperty().setValue(e -> timeline.play());
        timeline.play();
    }


    public void startValueAnimation() {
        Timeline timeline = new Timeline(Animation.INDEFINITE,
                new KeyFrame(Duration.seconds(.25), new KeyValue(value, 0)),
                new KeyFrame(Duration.seconds(.5), new KeyValue(value, 1)),
                new KeyFrame(Duration.seconds(.75), new KeyValue(value, 2)),
                new KeyFrame(Duration.seconds(1), new KeyValue(value, 1))
        );
        timeline.onFinishedProperty().setValue(e -> timeline.play());
        timeline.play();
    }

    public void moveTo(Main.Location loc) {
        walking = new Timeline(Animation.INDEFINITE,
            new KeyFrame(Duration.seconds(.001), new KeyValue(direction, location.getValue().directionTo(loc))),
            new KeyFrame(Duration.seconds(.002), new KeyValue(location, loc)),
            new KeyFrame(Duration.seconds(1), new KeyValue(translateXProperty(), loc.getX() * Main.CELL_SIZE)),
            new KeyFrame(Duration.seconds(1), new KeyValue(translateYProperty(), loc.getY() * Main.CELL_SIZE)),
            new KeyFrame(Duration.seconds(.25), new KeyValue(frame, 0)),
            new KeyFrame(Duration.seconds(.5), new KeyValue(frame, 1)),
            new KeyFrame(Duration.seconds(.75), new KeyValue(frame, 2)),
            new KeyFrame(Duration.seconds(1), new KeyValue(frame, 1))
        );
        walking.setOnFinished(e -> {
            if (arrivalHandler != null) {
                arrivalHandler.handle(e);
            }
        });
        Platform.runLater(walking::play);
    }

    public Main.Location getLocation() {
        return location.get();
    }

    public Main.Direction getDirection() {
        return direction.get();
    }

    public void setGlow (boolean v) {
        if (v) {
            this.imageView.setEffect(new Glow(1));
        } else {
            this.imageView.setEffect(null);
        }
    }

    public void setColor(Color color) {
        this.color = color;
        if (color == null) {
            imageView.setEffect(null);
        } else {
            imageView.setEffect(new ColorAdjust(color.getHue() / 180 - colorOffset, 0.3, 0, 0));
        }
    }

    public Color getColor() {
        return color;
    }
}

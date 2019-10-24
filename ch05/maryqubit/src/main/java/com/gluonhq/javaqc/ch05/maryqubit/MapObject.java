package com.gluonhq.javaqc.ch05.maryqubit;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.function.Predicate;

public abstract class MapObject extends SpriteView {

    boolean active = false;
    StrangeBridge strangeBridge;

    void setActive (boolean v) {
        this.active = v;
    }

    void toggleActive() {
        this.active = !this.active;
        if (active) {
            Main.setHelpText(getGateName()+" Gate activated!\nLambs visiting this gate will be affected");
            this.setGlow(true);
        } else {
            Main.setHelpText("");
            this.setGlow(false);
        }

    }

    String getGateName() {
        return "";
    }

    public static class Barn extends MapObject {
        // Image by LovelyBlue: http://l0velyblue.deviantart.com/art/barncharset-350737104
        static final Image BARN = loadImage("images/barn.png");
        public Barn(Main.Location loc, StrangeBridge s) {
            super(BARN, loc, s);
        }
        @Override
        public void visit(SpriteView s) {
            if (s instanceof Mary) {
                Mary mary = (Mary)s;
                Lamb lamb = new SpriteView.Lamb(s);
                mary.getAnimals().add(lamb);
                this.strangeBridge.addQubit(lamb);
                Main.setHelpText("Pressing X, H or C will\nactivate/deactivate the gates");

            }
        }
    }

    public static class Rainbow extends MapObject {
        static final Image RAINBOW = loadImage("images/rainbow.png");
        private int c = 0;
        private int cId = 0;
        public Rainbow(Main.Location loc, StrangeBridge strangeBridge) {
            super(RAINBOW, loc, strangeBridge);
            startAnimation();
        }
        @Override
        public void visit(SpriteView s) {

            if (!active) return;
            if (s instanceof Lamb) {
                Lamb lamb = (Lamb)s;
                int number=lamb.getNumber();
                if (c == 0) {
                    cId = number;
                    c = 1;
                } else if (number != cId){
                    this.strangeBridge.addCNot(cId, number);
                    c = 0;
                }
            }
        }

        @Override
        public String getGateName() {
            return "CNOT";
        }
    }

    public static class Church extends MapObject {
        // Image by LovelyBlue: http://l0velyblue.deviantart.com/art/Church-350736943
        static final Image CHURCH = loadImage("images/church.png");
        LongProperty mealsServed = new SimpleLongProperty();
        public Church(Main.Location loc, StrangeBridge strangeBridge) {
            super(CHURCH, loc, strangeBridge);
            Label label = new Label();
            label.textProperty().bind(mealsServed.asString());
            label.setFont(Font.font("Impact", 12 * Main.SCALE));
            label.setTranslateX(-8 * Main.SCALE);
            label.setTranslateY(3 * Main.SCALE);
            getChildren().add(label);
        }
        @Override
        public void visit(SpriteView s) {
            if (s instanceof Mary) {
                Mary mary = (Mary)s;
                long ir = strangeBridge.getLongResult();
                long total = mealsServed.get() + ir;
                mary.getAnimals().clear();
                strangeBridge.clearProgram();
                mealsServed.setValue(total);
            }
        }

    }

    public static class ChickenCoop extends MapObject {
        // Image by LovelyBlue: http://l0velyblue.deviantart.com/art/chickencoop-350736803
        static final Image CHICKEN_COOP = loadImage("images/chicken-coop.png");
        public ChickenCoop(Main.Location loc, StrangeBridge strangeBridge) {
            super(CHICKEN_COOP, loc, strangeBridge);
        }
        @Override
        public void visit(SpriteView s) {
            System.err.println("X visit by "+s+" and me active? "+active);
            if (!active) return;
            if (s instanceof Lamb) {
                Lamb lamb = (Lamb)s;
                int number=lamb.getNumber();
                this.strangeBridge.addX(number);
            }
        }

        @Override
        public String getGateName() {
            return "X";
        }
    }

    public static class Nest extends MapObject {
        // Image derived from Lokilech's Amselnest: http://commons.wikimedia.org/wiki/File:Amselnest_lokilech.jpg
        static final Image NEST = loadImage("images/nest.png");
        public Nest(Main.Location loc, StrangeBridge strangeBridge) {
            super(NEST, loc, strangeBridge);
        }
        @Override
        public void visit(SpriteView s) {
            System.err.println("H visit by "+s+" and me active? "+active);
            if (!active) return;
            if (s instanceof Lamb) {
                Lamb lamb = (Lamb)s;
                int number=lamb.getNumber();
                this.strangeBridge.addH(number);
            }
        }

        @Override
        public String getGateName() {
            return "H";
        }
    }

    public static class Fox extends MapObject {
        // Image by PinedaVX: http://www.rpgmakervx.net/index.php?showtopic=9422
        static final Image FOX = loadImage("images/fox.png");
        public  Fox(Main.Location loc, StrangeBridge strangeBridge) {
            super(FOX, loc, strangeBridge);
            startAnimation();
        }
        @Override
        public void visit(SpriteView s) {
            System.err.println("Fox visited by "+s);
            if (s instanceof Mary) {
                Mary mary = (Mary)s;
                mary.getAnimals().clear();
                this.strangeBridge.clearProgram();
            }
        }
    }

    public MapObject(Image spriteSheet, Main.Location loc, StrangeBridge strangeBridge) {
        super(spriteSheet, loc);
        Main.map[loc.getX()][loc.getY()] = this;
        this.strangeBridge = strangeBridge;
    }

    public abstract void visit(SpriteView shepherd);
}

import java.awt.*;
import java.awt.geom.*;

/**
 * The MountainBackground class represents a background with mountains.
 * It implements the DrawingObject interface.
 */
public class MountainBackground implements DrawingObject {
    private int length;

    /**
     * Constructs a MountainBackground with the specified length.
     *
     * @param length the length of the background
     */
    public MountainBackground(int length) {
        this.length = length;
    }

    /**
     * Draws the mountain background using the specified Graphics2D context.
     *
     * @param g2d the Graphics2D context to use for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();
        for (int i = -1; i <= length; i++) {
            g2d.translate(i * 800, 0);
            Mountain mountain = new Mountain(500);
            mountain.draw(g2d);
            g2d.setTransform(reset);
        }
    }

    /**
     * Updates the state of the mountain background.
     * This method is currently empty because the mountain background requires no movement.
     */
    @Override
    public void update() {
    }

    /**
     * The Mountain class represents a single mountain.
     * It implements the DrawingObject interface.
     */
    class Mountain implements DrawingObject {
        private int baseY;

        /**
         * Constructs a Mountain with the specified base Y-coordinate.
         *
         * @param baseY the base Y-coordinate of the mountain
         */
        public Mountain(int baseY) {
            this.baseY = baseY;
        }

        /**
         * Draws the mountain using the specified Graphics2D context.
         *
         * @param g2d the Graphics2D context to use for drawing
         */
        @Override
        public void draw(Graphics2D g2d) {
            AffineTransform reset = g2d.getTransform();
            drawMountain1(reset, g2d);
            drawMountain2(reset, g2d);
            drawMountain3(reset, g2d);
        }

        /**
         * Updates the state of the mountain.
         * This method is currently empty because the mountain requires no movement.
         */
        @Override
        public void update() {
        }

        /**
         * Draws the first mountain layer.
         *
         * @param reset the AffineTransform to reset the Graphics2D context
         * @param g2d the Graphics2D context to use for drawing
         */
        private void drawMountain1(AffineTransform reset, Graphics2D g2d){
            Path2D.Double mountain1 = new Path2D.Double();
            mountain1.moveTo(-10, baseY - 350);
            mountain1.lineTo(250, baseY - 250);
            mountain1.lineTo(550, baseY - 300);
            mountain1.lineTo(655, baseY - 230);
            mountain1.lineTo(800, baseY - 350);
            mountain1.lineTo(800, baseY);
            mountain1.lineTo(0, baseY);
            mountain1.closePath();

            g2d.setColor(new Color(30, 30, 30, 80));
            g2d.translate(10, -5);
            g2d.fill(mountain1);
            g2d.setTransform(reset);
            g2d.setColor(new Color(64, 77, 109));
            g2d.fill(mountain1);
        }

        /**
         * Draws the second mountain layer.
         *
         * @param reset the AffineTransform to reset the Graphics2D context
         * @param g2d the Graphics2D context to use for drawing
         */
        private void drawMountain2(AffineTransform reset, Graphics2D g2d){
            Path2D.Double mountain2 = new Path2D.Double();
            mountain2.moveTo(-10, baseY - 160);
            mountain2.lineTo(325, baseY - 190);
            mountain2.lineTo(410, baseY - 160);
            mountain2.lineTo(495, baseY - 220);
            mountain2.lineTo(700, baseY - 150);
            mountain2.lineTo(800, baseY - 160);
            mountain2.lineTo(800, baseY);
            mountain2.lineTo(0, baseY);
            mountain2.closePath();
            g2d.setColor(new Color(20, 20, 30, 80));
            g2d.translate(10, -5);
            g2d.fill(mountain2);
            g2d.setTransform(reset);
            g2d.setColor(new Color(43, 53, 78));
            g2d.fill(mountain2);
        }

        /**
         * Draws the third mountain layer.
         *
         * @param reset the AffineTransform to reset the Graphics2D context
         * @param g2d the Graphics2D context to use for drawing
         */
        private void drawMountain3(AffineTransform reset, Graphics2D g2d){
            Path2D.Double mountain3 = new Path2D.Double();
            mountain3.moveTo(-10, baseY - 130);
            mountain3.lineTo(350, baseY - 50);
            mountain3.lineTo(500, baseY - 140);
            mountain3.lineTo(550, baseY - 115);
            mountain3.lineTo(610, baseY - 135);
            mountain3.lineTo(800, baseY - 130);
            mountain3.lineTo(800, baseY);
            mountain3.lineTo(0, baseY);
            mountain3.closePath();

            g2d.setColor(new Color(10, 10, 20, 80));
            g2d.translate(10, -5);
            g2d.fill(mountain3);
            g2d.setTransform(reset);
            g2d.setColor(new Color(22, 30, 51));
            g2d.fill(mountain3);
        }
    }
}


package bridge;

// Refined Abstraction (Abstraction의 확장판)
public class RectShape implements Shape {
    private double x, y;
    private Drawing drawing;

    public RectShape(double x, double y, Drawing drawing) {
        this.x = x;
        this.y= y;
        this.drawing = drawing;
    }

    @Override
    public void draw() {
        drawing.devDraw(x, y);
    }
}

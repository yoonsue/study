package bridge;

// Concrete Implementation
public class RectDrawing implements Drawing {
    @Override
    public void devDraw(double x, double y) {
        System.out.printf("Rect width: %f, height: %f", x, y);
    }
}

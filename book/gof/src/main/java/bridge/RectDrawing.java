package bridge;

// Concrete Implementation
public class RectDrawing implements Drawing {
    @Override
    public void devDraw(double x, double y) {
        System.out.println("Rect width: %f, height: %f", x, y);
    }
}

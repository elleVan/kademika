package lessonsJD.lesson_3.shapes;

public class F_3_2_6 {

    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle();
        shapes[1] = new Rectangle();
        shapes[2] = new Triangle();

        ShapesTemplate template = new ShapesTemplate(shapes);
    }
}

package lessonsJD.lesson3.shapes;

public class F_3_2_6 {

    public static void main(String[] args) {
        AbstractShape[] shapes = new AbstractShape[3];
        shapes[0] = new Circle();
        shapes[1] = new Rectangle();
        shapes[2] = new Triangle();

        ShapesTemplate template = new ShapesTemplate(shapes);
    }
}

package lessonsJD.lesson_3.shapes;

public class F_3_2_8 {

    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(), new Rectangle(), new Square(), new Triangle()
        };

        ShapesTemplate template = new ShapesTemplate(shapes);
    }
}

package lessonsJD.lesson6.f_6_1_3.v2;

import lessonsJD.lesson6.f_6_1_3.v2.calc.Calculator;
import lessonsJD.lesson6.f_6_1_3.v2.calc.DefaultCalculator;

import java.util.Random;

public class Launcher {

	public static void main(String[] args) {
		Calculator calc = new DefaultCalculator();
		
		Operations o = new Operations();
		o.setCalc(calc);

		Random r = new Random();
		for (int i = 0; i < 5; i++) {
			System.out.println(o.createSummaryReport(r.nextInt(100)));
		}
	}
}

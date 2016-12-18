package lessonsJD.lesson6.f_6_1_3.v1;

import lessonsJD.lesson6.f_6_1_3.Arifmetika;

import java.util.Random;

public class Launcher {

	public static void main(String[] args) {
		Arifmetika calc = new Arifmetika();
		
		Operations o = new Operations();
		o.setCalc(calc);

		Random r = new Random();
		for (int i = 0; i < 5; i++) {
			System.out.println(o.createSummaryReport(r.nextInt(100)));
		}
	}
}

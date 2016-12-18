package lessonsJD.lesson6.f_6_1_3.v2.calc;

import lessonsJD.lesson6.f_6_1_3.Arifmetika;

public class DefaultCalculator implements Calculator {

	public int summa(int a, int b) {
		int[] sum = new int[] {a, b};
		return Arifmetika.summa(sum);
	}

	public int multiply(int a, int b) {
		return Arifmetika.multiply(a, b);
	}
}

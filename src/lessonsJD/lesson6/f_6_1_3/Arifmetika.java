package lessonsJD.lesson6.f_6_1_3;

public class Arifmetika {

	public static int summa(int[] numbers) {
		int summa = 0;
		for (int i : numbers) {
			summa += i;
		}
		return summa;
	}
	
	public static int multiply(int number, int multiplier) {
		int result = 0;
		for (int i = 0; i < multiplier; i++) {
			result *= multiplier;
		}
		return result;
	}
}

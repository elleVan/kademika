package lessonsJD.lesson6.f_6_1_3.v1;

import lessonsJD.lesson6.f_6_1_3.Arifmetika;

public class Operations {
	
	private Arifmetika calc;
	
	public int createSummaryReport(int factor) {
		int[] sum = new int[] {factor, 78};
		int result = calc.summa(sum);
		sum[0] = result;
		result = calc.summa(sum);
		return result;
	}
	
	public void setCalc(Arifmetika calc) {
		this.calc = calc;
	}
}

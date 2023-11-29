package EJRec;

public class SepararBotones {

	int numIntr = 0;

	public void separar(int num) {
		if(num >= 1) {
			System.out.print(num%10+" ");
			num = num / 10;
			separar(num);
		}
	}

	public static void main(String[] args) {

		SepararBotones sp = new SepararBotones();
		sp.separar(256534634);

	}

}

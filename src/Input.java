import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class Input {
	
	private static int N = 0;
	private static int L = 0;
	private static double actualTime = 0;
	
	public static void readParticles(String dPath, String sPath, Set<Particle> particles){

		try {
			
			File dFile = new File(dPath);
			File sFile = new File(sPath);
			
			try {
				Scanner dRead = new Scanner(dFile);
				Scanner sRead = new Scanner(sFile);
				actualTime = Double.parseDouble(dRead.next());
				N = Integer.parseInt(sRead.next());
				L = Integer.parseInt(sRead.next());
			
				while(dRead.hasNext() && sRead.hasNext()){
					//Aca voy creando la particula!! :) 
					particles.add(new Particle(Double.parseDouble(sRead.next()) , Double.parseDouble(sRead.next()), Double.parseDouble(dRead.next()), Double.parseDouble(dRead.next())));
				}
				dRead.close();
				sRead.close();
				
			} catch (Exception e) {
				System.out.println("Error scanning file");
			}
			
		} catch (Exception e) {
			System.out.println("Error opening or finding file");
		}
		
	}
	
	public static int getL() {
		return L;
	}
	
	public static double getActualTime() {
		return actualTime;
	}
	
	public static int getN() {
		return N;
	}


}

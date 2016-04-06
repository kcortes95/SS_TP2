import java.awt.Color;
import java.io.File;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		//Rmax 1
		int L = 20;
		int N = 1200;
		int Rc = 1;
		Map<Double,Set<Particle>> map = new TreeMap<>();
		Set<Particle> set = ParticleGenerator(N,L);
		Grid grid = new LinearGrid(L, (int)(L/(Rc+2*1)), set);
		Simulation s = new Simulation(grid, 100,1,Rc,0.1,set);
		s.run();
		Input.readParticles(N, "output.txt", map);
		
		Set<Double> times = map.keySet();
		OnScreen screen = new OnScreen(800, 600);
		Thread.sleep(500);
		for(Double t : times){
			Thread.sleep(100);
			Set<Particle> parts = map.get(t);
			screen.draw(parts);
			
			try {
				screen.captureScreen();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private static Set<Particle> ParticleGenerator(int N, int L){
		double radius = 1.0;
		double speed = 0.3;
		Set<Particle> list = new HashSet<Particle>();
		for(int i=1 ; i<=N;i++){
			list.add(new Particle(radius, Color.RED, Math.random()*L, Math.random()*L, new Velocity(speed, Math.random()*(2*Math.PI))));
		}
		return list;
	}
}

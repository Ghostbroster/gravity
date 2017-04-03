package dal.gravity;

import java.text.NumberFormat;

/** 
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {
    public static void main (String [] args) {
		NumberFormat nf = NumberFormat.getInstance ();
		nf.setMaximumFractionDigits (3);
	
		double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
		double sLen = 10, pMass = 10, theta0 = Math.PI/30;
		GravityConstant g1 = new GravityConstant(); //Defaults to earth's gravity
		GravityConstant g2 = new GravityConstant(25.0); //Jupiter's gravity
		RegularPendulum rp = new RegularPendulum (sLen, pMass, theta0, delta, g1);
		SimplePendulum sp = new SimplePendulum (sLen, pMass, theta0, g1);
	
		// print out difference in displacement in 1 second intervals
		int iterations = (int) (1/delta);
		System.out.println ("Simple and Regular Pendulums with g = " + g1.getGravitationalField());
		for (int second = 1; second <= 40; second++) {
			if (second == 20) {
				System.out.println("Changing g to " + g2.getGravitationalField());
				sp.changeGravity(g2);
				rp.changeGravity(g2);
			}
		    for (int i = 0; i < iterations; i++) rp.step ();
		    System.out.println ("t=" + second + "s: \t" + 
					nf.format (Math.toDegrees (sp.getTheta (second))) 
					+ "\t" +
					nf.format (Math.toDegrees (rp.getLastTheta ())));
		}
    }
}


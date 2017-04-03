package dal.gravity;

/**
 * Represents a pendulum
 */
public class RegularPendulum extends AbstractPendulum {
    private double delta, iterations = 0;
    private double dissipation;
    private double lastTheta, lastVel, lastAccel;

    /**
     * Creates a new Pendulum instance 
     */
    public RegularPendulum (double inLength, double inMass, double inTheta0, double inDelta, double inDiss) {
    	super (inLength, inMass, inTheta0);
    	init(inDelta, inDiss);
    }
    public RegularPendulum (double inLength, double inMass, double inTheta0, double inDelta) {
    	this (inLength, inMass, inTheta0, inDelta, 0);
    }
    public RegularPendulum (double inLength, double inMass, double inTheta0, double inDelta, double inDiss, GravityModel gModel) {
    	super (inLength, inMass, inTheta0, gModel);
    	init(inDelta, inDiss);
    }
    public RegularPendulum (double inLength, double inMass, double inTheta0, double inDelta, GravityModel gModel) {
    	this (inLength, inMass, inTheta0, inDelta, 0, gModel);
    }
    
    /**
     * Calculates some local values
     */ 
    private void init(double inDelta, double inDiss) {
		delta=inDelta;
		dissipation = inDiss;
		lastVel = 0;
		lastTheta = this.getMaxAngularDisplacement ();
		lastAccel = -(this.getGravitationalField () / this.getStringLength ())*Math.sin (lastTheta);
    }

    public void step () {
    	iterations++;
		lastTheta = lastTheta + lastVel*delta;
		lastVel = lastVel + lastAccel*delta;
		lastAccel = - dissipation*lastVel - this.getGravitationalField () / this.getStringLength () * Math.sin (lastTheta);
    }

    public double getLastTheta () { return lastTheta; }
    public double getLastVelocity () { return lastVel; }
    public double getLastAcceleration () { return lastAccel; }
    public double getLastTime () { return iterations*delta; }
    public double getDissipationConstant () { return dissipation; }
    
}

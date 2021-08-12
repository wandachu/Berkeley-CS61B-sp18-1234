/**
 *  The Planet class contains info of a certain planet including its position, velocity, mass, and image.
 */
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double NORMAL_GRAVITY = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double xDistance = xxPos - p.xxPos;
        double yDistance = yyPos - p.yyPos;
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    public double calcForceExertedBy(Planet p) {
        double distance = this.calcDistance(p);
        return NORMAL_GRAVITY * mass * p.mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p);
        return force * (-xxPos + p.xxPos) / (calcDistance(p));
    }

    public double calcForceExertedByY(Planet p) {
        double force = calcForceExertedBy(p);
        return force * (-yyPos + p.yyPos) / (calcDistance(p));
    }

    public double calcNetForceExertedByX(Planet[] allP) {
        double netForce = 0;
        for (Planet p : allP) {
            if (p.equals(this)) {
                continue;
            }
            netForce += calcForceExertedByX(p);
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Planet[] allP) {
        double netForce = 0;
        for (Planet p : allP) {
            if (p.equals(this)) {
                continue;
            }
            netForce += calcForceExertedByY(p);
        }
        return netForce;
    }

    public void update(double dt, double xForce, double yForce) {
        double acceX = xForce / mass;
        double acceY = yForce / mass;
        double newVX = xxVel + acceX * dt;
        double newVY = yyVel + acceY * dt;
        xxVel = newVX;
        yyVel = newVY;
        xxPos = xxPos + dt * newVX;
        yyPos = yyPos + dt * newVY;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}


/**
 * This class sets the background and draws the planets based on command line inputs.
 */
public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numberOfPlanets = in.readInt();
        Planet[] planets = new Planet[numberOfPlanets];
        in.readDouble();
        for (int i = 0; i < numberOfPlanets; i++) {
            double xxP = in.readDouble();
            double yyP = in.readDouble();
            double xxV = in.readDouble();
            double yyV = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxP, yyP, xxV, yyV, mass, imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Please give 3 arguments");
            return;
        }
        //Read in info we need
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);

        double radius = readRadius(filename);
        StdDraw.setScale(-radius, radius);

        // Draw the background
        String imageToDraw = "images/starfield.jpg";
        StdDraw.picture(0 ,0 ,imageToDraw);

        // Draw the planets
        for (Planet p : planets) {
            p.draw();
        }

        // Create the animation
        StdDraw.enableDoubleBuffering();
        int waitTimeMilliseconds = 10;
        int time = 0;
        for (int i = time; i < T; i += dt) {
            // Create an xForces array and yForces array of each planet
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int j = 0; j < planets.length; j++) {
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            // Call update on each planet
            for (int j = 0; j < planets.length; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }
            // Draw the background image
            StdDraw.picture(0 ,0 ,imageToDraw);
            // Draw all of the planets
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(waitTimeMilliseconds);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet p : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
        }
    }
}



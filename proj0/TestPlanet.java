public class TestPlanet {
    public static void main(String[] args) {
        System.out.println("Creating the first planet...");

        double xxPos = 1.0,
                yyPos = 0.0,
                xxVel = 3.0,
                yyVel = 5.0,
                mass = 10.0;

        String imgFileName = "jupiter.gif";

        Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

        System.out.println("Creating the second planet...");

        double xxPos2 = 3.0,
                yyPos2 = 3.0,
                xxVel2 = 3.0,
                yyVel2 = 5.0,
                mass2 = 5.0;

        String imgFileName2 = "jupiter.gif";

        Planet p2 = new Planet(xxPos2, yyPos2, xxVel2, yyVel2, mass2, imgFileName2);

        double force = p.calcForceExertedBy(p2);
        if (Math.abs(force - 2.56e-10) < 0.01) {
            System.out.println("pass");
        } else {
            System.out.println("force between them is " + force);
        }


    }


}

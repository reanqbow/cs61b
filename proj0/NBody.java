public class NBody {

    private static String background = "./images/starfield.jpg";
    public static double readRadius(String name) {
        In in = new In(name);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String name) {
        In in = new In(name);
        int num = in.readInt();
        in.readDouble();
        Planet[] all = new Planet[num];

        for (int n = 0; n < num; n++) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            all[n] = new Planet(xp, yp, xv, yv, m, img);
        }
        return all;
    }


    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]); //get first two args

        String filename = args[2];
        double radius = readRadius(filename);
        readPlanets(filename);
        Planet[] allPlanet = readPlanets(filename); // create array of planets
        int num = allPlanet.length;

        StdDraw.setScale(-radius, radius); // resize the window
        StdDraw.clear();
        StdDraw.enableDoubleBuffering(); // make animation smooth


        double[] xForces = new double[num];
        double[] yForces = new double[num];
        double timevar = 0.0;
        while (timevar < T) {
            for (int i = 0; i < num; i++) {
                double fx = allPlanet[i].calcNetForceExertedByX(allPlanet);
                double fy = allPlanet[i].calcNetForceExertedByY(allPlanet);
                xForces[i] = fx;
                yForces[i] = fy;

            }
            for (int i = 0; i < num; i++) {
                allPlanet[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, background);
            for (Planet p : allPlanet) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            timevar += dt;
        }
    }
}

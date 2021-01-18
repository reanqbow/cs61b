public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    String imgFileName;

    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet P) {
        xxPos = P.xxPos;
        yyPos = P.yyPos;
        xxVel = P.xxVel;
        yyVel = P.yyVel;
        mass = P.mass;
        imgFileName = P.imgFileName;
    }

    public double calcDistance(Planet P) {
        double Fx = this.xxPos - P.xxPos;
        double Fy = this.yyPos - P.yyPos;
        double all = Math.sqrt(Fx * Fx + Fy * Fy);
        return all;
    }

    public double calcForceExertedBy(Planet P) {
        double r = this.calcDistance(P);
        double F = (this.mass * P.mass) * G / Math.pow(r, 2);
        return F;
    }

    public double calcForceExertedByX(Planet P){
        double dx = P.xxPos - this.xxPos;
        double F = this.calcForceExertedBy(P);
        double r = this.calcDistance(P);
        double Fx = F * dx / r;
        return Fx;
    }

    public double calcForceExertedByY(Planet P){
        double dy = P.yyPos - this.yyPos;
        double F = this.calcForceExertedBy(P);
        double r = this.calcDistance(P);
        double Fy = F * dy / r;
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] all) {
        //int len = all.length;
        double Fx = 0.0;
        for (Planet p : all) {
            if (!this.equals(p)){
              Fx += this.calcForceExertedByX(p);
            }
            else {
                continue;
            }
        }
        return Fx;
    }
    public double calcNetForceExertedByY(Planet[] all) {
        //int len = all.length;
        double Fy = 0.0;
        for (Planet p : all) {
            if (!this.equals(p)){
                Fy += this.calcForceExertedByY(p);
            }
            else {
                continue;
            }
        }
        return Fy;
    }

    public void update(double dt, double fx, double fy) {
        double axNet = fx / mass;
        double ayNet = fy / mass;
        xxVel = xxVel + dt * axNet;
        yyVel = yyVel + dt * ayNet;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}

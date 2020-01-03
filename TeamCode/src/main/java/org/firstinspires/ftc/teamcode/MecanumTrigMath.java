package org.firstinspires.ftc.teamcode;

/**
 * @author Leo and Josh!
 * Last edit date: 6 December 2019
 * <p>
 * ======================================================================================
 * Math involved in converting input values (vectors / directions) to the motor values
 * required to move the robot in that direction using mecanum wheels.
 * ======================================================================================
 * <p>
 * Made by LEO (See? I'm helping)
 * <p>
 * The motor values are passed around as arrays containing 4 double values:
 * <p>
 * {motor 1, motor 2, motor 3, motor 4}
 * <p>
 * The motors correspond to the array elements like so:
 * <p>
 * [Motor 1]                    [Motor 2]
 *          [^Front of robot^]
 *          [   ROBOT base   ]
 * [Motor 3]                    [Motor 4]
 * <p>
 * (The directions of the mecanum wheels should form an X when viewed from above, NOT a diamond)
 * =======================================================================================
 * <p>
 * The magnitude of the vectors passed to the methods do NOT correspond to drive distance.
 * Instead, longer vectors will produce faster motion (but the motors can only handle values between -1 and 1)
 * <p>
 * If you are unfamiliar with vectors and how they are represented, consult the internet first.
 * This code does not use any complicated vector math, only simple conversions and some
 * addition
 * =======================================================================================
 */

public class MecanumTrigMath {

    //=============================[Trig Methods]==============================================
    /**
     * Converts vector components into magnitude and direction form. Works for 2d vectors only.
     *
     * @param components (x, y) of vector
     * @return double[] containing {magnitude, direction}
     */

    public static double[] componentsToVector(double[] components) {
        return new double[]{Math.sqrt(Math.pow(components[0], 2.0) + Math.pow(components[1], 2.0)),
                Math.atan2(components[0], components[1])};
    }

    /**
     * Converts a velocity vector and a turning constant into motor values.
     *
     * @param vector a double[] vector in the form [magnitude, direction]
     * @param turn a turn value between -1 and 1 describing the direction the robot should turn
     * @return motor values [lf, lr, rf, rr]
     */
    public static double[] vectorToMotors(double[] vector, double turn) {
        double magnitude = vector[0];
        double theta = vector[1]; //for convenience and readability

        double[] motorValues = {magnitude * Math.sin(theta + Math.PI/4) - turn, magnitude * Math.sin(theta - Math.PI/4 - turn),
                                magnitude * Math.sin(theta + Math.PI/4) + turn, magnitude * Math.sin(theta - Math.PI/4 + turn)};

        return normalize(motorValues); //optional - but it shouldn't break anything to include it
    }

    //===========================[Legacy Methods]==============================================

    /**
     * Converts controller input (x and y) to motor values.
     *
     * @param x    controller input for the x-axis
     * @param y    controller input for the y-axis
     * @param turn controller input for turning
     * @return motor values for the four motors
     */
    public static double[] vectorToMotors(double x, double y, double turn) {

        // create the input velocity components
        double[] inputComponents = new double[]{x, y};

        // transform the input velocity vector components into polar form
        double[] velocity = componentsToVector(inputComponents);

        return vectorToMotors(velocity, turn);
    }

    /**
     * Normalizes an input vector of any dimension (scales a vector so its magnitude is 1 (one) unit).
     * This math is independent of the robot itself and will work even with vectors of higher dimensions.
     *
     * @param vector the vector to normalize
     * @return the normalized vector
     */
    public static double[] normalize(double[] vector) {

        //finds magnitude of vector
        double sqrSum = 0;
        for (double d : vector) {
            sqrSum += Math.pow(d, 2);
        }

        double magnitude = Math.sqrt(sqrSum); //magnitude

        //divide the vector by its magnitude
        for (int i = 0; i < vector.length; i++) {
            vector[i] /= magnitude;
        }


        return vector;  //returns normalized vector
    }

    /**
     * Converts a rectangular vector (x and y) to motor values.
     * same as inputsToMotors, but allows the input vector to be from a traditional coordinate plane (not the weird inverted ones from the gamepad's joystick).
     *
     * @param x    x-value of the vector
     * @param y    y-value of the vector
     * @param turn turn value for the robot
     * @return motor values for the four motors
     */
    public static double[] inputsToMotors(double x, double y, double turn) {
        //converts y and x coordinates to the (inverted) values they would be on the gamepad
        y = -y; //only y is inverted (I guess?)

        return vectorToMotors(x, y, turn); //just runs the other method
    }

    /**
     * Converts a polar vector directly to motor values.
     * This method does not include a "turn" value (defaults to 0).
     *
     * @param angle  the angle of the vector
     * @param magnitude the magnitude of the vector
     * @return motor values for the four motors
     */
    public static double[] angleToMotors(double angle, double magnitude) {
        return angleToMotors(angle, magnitude, 0);
    }

    /**
     * This method is preserved for compatibility use only.
     * Converts a polar vector and turn value directly to motor values.
     * This method includes a "turn" value.
     *
     * @param angle  the angle of the vector
     * @param magnitude the magnitude of the vector
     * @param turn   turn value for the robot
     * @return motor values for the four motors
     */
    public static double[] angleToMotors(double angle, double magnitude, double turn) {

        double[] vector = new double[]{magnitude, angle};
        return vectorToMotors(vector, turn);
    }
}
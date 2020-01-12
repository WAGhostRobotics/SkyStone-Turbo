package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.component.Extender;
import org.firstinspires.ftc.teamcode.component.FoundationGrabber;
import org.firstinspires.ftc.teamcode.component.Intake;
import org.firstinspires.ftc.teamcode.component.LinearSlides;

import java.util.ArrayList;

public class Kevin {
    public static HardwareMap hardwareMap;

    // DriveStyle motors
    private static DcMotor dFrontLeft;
    private static DcMotor dFrontRight;
    private static DcMotor dBackLeft;
    private static DcMotor dBackRight;

    // Motor array [in order: lf, lr, rf, rr]
    public static ArrayList<DcMotor> driveMotors = new ArrayList<>();

    // Extender
    public static Extender extender;

    // Intake
    public static Intake intake;

    // Linear Slides
    public static LinearSlides linearSlides;

    // Foundation Grabber
    public static FoundationGrabber foundationGrabber;

    /*
    // Capstone
    public static Servo capstone;
    public static final double HOLD = 0.1;
    public static final double LIBERATE = 0.9;
    */

    // Gyro
    public static BNO055IMU imu;

    public static void init(HardwareMap hwMap) {
        // Assign HardwareMap
        hardwareMap = hwMap;

        // Assign motor information
        dFrontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        dFrontRight = hardwareMap.get(DcMotor.class, "frontRight");
        dBackLeft = hardwareMap.get(DcMotor.class, "backLeft");
        dBackRight = hardwareMap.get(DcMotor.class, "backRight");

        // Adjust motor directions - this decides which side of the robot is "front"
        // Flip the values to change the direction the robot "faces"
        // The motors turn counterclockwise looking at them head on for FORWARD; set the right ones to reverse for correct operation
        dFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        dFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        dBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        dBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        // Adjust motor stopping behavior; "BRAKE" locks the motor shaft, while "FLOAT" just stops applying power
        dFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Adds the motors to a motor array for easier reference
        // The order here must match the order used in {@link DriveStyle}
        driveMotors.add(dFrontLeft);
        driveMotors.add(dFrontRight);
        driveMotors.add(dBackLeft);
        driveMotors.add(dBackRight);

        // Extender
        extender = new Extender();
        extender.init(hardwareMap);

        // Intake
        intake = new Intake();
        intake.init(hardwareMap);

        // Linear Slides
        linearSlides = new LinearSlides();
        linearSlides.init(hardwareMap);

        // Foundation Grabber
        foundationGrabber = new FoundationGrabber();
        foundationGrabber.init(hardwareMap);

        // Gyro
        imu = hardwareMap.get(BNO055IMU.class, "imu");
    }

    /**
     * Sleeps for the given amount of milliseconds, or until the thread is interrupted. This is
     * simple shorthand for the operating-system-provided {@link Thread#sleep(long) sleep()} method.
     *
     * @param milliseconds amount of time to sleep, in milliseconds
     * @see Thread#sleep(long)
     */
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

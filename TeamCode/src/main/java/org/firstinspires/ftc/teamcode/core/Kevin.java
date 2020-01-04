package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.multimotors.MultiDcMotor;
import org.firstinspires.ftc.teamcode.library.multimotors.MultiServo;

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

    // Intake
    private static DcMotor intake1;
    private static DcMotor intake2;

    public static MultiDcMotor intakeMotors = new MultiDcMotor(intake1, intake2);

    // Linear Actuator
    public static DcMotor horizontal;

    // Linear Slide
    private static Servo rightSpool;
    private static Servo leftSpool;

    public static MultiServo linearSlides = new MultiServo(rightSpool, leftSpool);

    // Claw
    public static CRServo clawRotate;
    public static Servo clawGrab;

    // Foundation
    private static Servo foundation1;
    private static Servo foundation2;

    public static MultiServo foundation = new MultiServo(foundation1, foundation2);

    public static final double RELEASE = 0;
    public static final double GRAB = 1;

    // Capstone
    public static Servo capstone;
    public static final double HOLD = 0.1;
    public static final double LIBERATE = 0.9;

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
        dFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        dFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        dBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        dBackRight.setDirection(DcMotorSimple.Direction.FORWARD);

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

        // Init intake motors
        intake1 = hardwareMap.get(DcMotor.class, "i1");
        intake2 = hardwareMap.get(DcMotor.class, "i2");

        intakeMotors.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeMotors.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // Linear Slides
        rightSpool = hardwareMap.get(Servo.class, "rightSpool");
        leftSpool = hardwareMap.get(Servo.class, "leftSpool");

        linearSlides.setDirection(Servo.Direction.FORWARD);
        linearSlides.setPosition(0);

        // Linear Actuator
        horizontal = hardwareMap.get(DcMotor.class, "hzl");
        horizontal.setDirection(DcMotorSimple.Direction.REVERSE);
        horizontal.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Claw
        clawRotate = hardwareMap.get(CRServo.class, "rotate");
        clawRotate.setDirection(CRServo.Direction.FORWARD);

        clawGrab = hardwareMap.get(Servo.class, "grab");
        clawGrab.setDirection(Servo.Direction.FORWARD);

        // Foundation
        foundation1 = hardwareMap.get(Servo.class, "leftFND");
        foundation1.setDirection(Servo.Direction.FORWARD);

        foundation2 = hardwareMap.get(Servo.class, "leftFND");
        foundation2.setDirection(Servo.Direction.REVERSE);

        foundation.setPosition(RELEASE);

        // Gyro
        imu = hardwareMap.get(BNO055IMU.class, "imu");
    }
}

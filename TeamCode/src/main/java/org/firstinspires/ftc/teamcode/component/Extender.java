package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.core.Kevin;
import org.firstinspires.ftc.teamcode.library.multimotors.MultiServo;

public class Extender {

    private Servo right;
    private Servo left;

    private MultiServo zLift = new MultiServo(right, left);

    private CRServo claw;

    private final static double EXTEND = 0.7;
    private final static double RETRACT = 0.2;

    public void init(HardwareMap hardwareMap) {
        right = hardwareMap.get(Servo.class, "zRight");
        left = hardwareMap.get(Servo.class, "zLeft");

        right.setDirection(Servo.Direction.FORWARD);
        left.setDirection(Servo.Direction.REVERSE);

        claw = hardwareMap.get(CRServo.class, "claw");
        claw.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void extend() {
        zLift.setPosition(EXTEND);
    }

    public void retract() {
        zLift.setPosition(RETRACT);
    }

    public void openClaw() {
        claw.setPower(1);
        Kevin.sleep(250);
        claw.setPower(0);
    }

    public void closeClaw() {
        claw.setPower(-1);
        Kevin.sleep(250);
        claw.setPower(0);
    }
}

package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.core.ComponentBase;
import org.firstinspires.ftc.teamcode.library.multimotors.MultiServo;

public class Extender extends ComponentBase {

    private Servo right;
    private Servo left;

    private MultiServo zLift = new MultiServo(right, left);

    private CRServo claw;

    private final static double EXTEND = 0.7;
    private final static double RETRACT = 0.2;


    @Override
    public void initComponent() {
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
        sleep(250);
        claw.setPower(0);
    }

    public void closeClaw() {
        claw.setPower(-1);
        sleep(250);
        claw.setPower(0);
    }
}

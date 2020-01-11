package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.core.Kevin;
import org.firstinspires.ftc.teamcode.library.multimotors.MultiServo;

import static org.firstinspires.ftc.teamcode.core.Kevin.sleep;

public class Extender {

    private Servo right;
    private Servo left;

    private Servo claw;

    private final static double EXTEND = 0;
    private final static double RETRACT = 0.47;

    private final static double OPEN = 0.7;
    private final static double CLOSE = 0.2;

    public void init(HardwareMap hardwareMap) {
        right = hardwareMap.get(Servo.class, "zRight");
        left = hardwareMap.get(Servo.class, "zLeft");

        right.setDirection(Servo.Direction.FORWARD);
        left.setDirection(Servo.Direction.REVERSE);

        claw = hardwareMap.get(Servo.class, "claw");
        claw.setDirection(Servo.Direction.FORWARD);
    }

    public void extend() {
        right.setPosition(EXTEND);
        left.setPosition(EXTEND);
    }

    public void retract() {
        right.setPosition(RETRACT);
        left.setPosition(RETRACT);
    }

    public void openClaw() {
        claw.setPosition(OPEN);
    }

    public void closeClaw() {
        claw.setPosition(CLOSE);
    }
}

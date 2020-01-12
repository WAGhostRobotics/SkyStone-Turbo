package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.core.Kevin;

public class FoundationGrabber {
    // Foundation
    private Servo foundation1;
    private Servo foundation2;

    private final static double GRAB = 0.7;
    private final static double RELEASE = 0;

    public void init(HardwareMap hardwareMap) {
        // Foundation
        foundation1 = hardwareMap.get(Servo.class, "leftFND");
        foundation1.setDirection(Servo.Direction.REVERSE);

        foundation2 = hardwareMap.get(Servo.class, "rightFND");
        foundation2.setDirection(Servo.Direction.FORWARD);

        release();
    }

    public void grab() {
        foundation1.setPosition(GRAB);
        foundation2.setPosition(GRAB);
    }

    public void release() {
        foundation1.setPosition(RELEASE);
        foundation2.setPosition(RELEASE);
    }
}

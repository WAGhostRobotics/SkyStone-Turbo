package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.multimotors.MultiServo;

public class LinearSlides {

    // Linear Slide
    private static Servo rightSpool;
    private static Servo leftSpool;

    public static MultiServo linearSlides;

    public void init(HardwareMap hardwareMap) {

        // Linear Slides
        rightSpool = hardwareMap.get(Servo.class, "rightSpool");
        leftSpool = hardwareMap.get(Servo.class, "leftSpool");

        linearSlides = new MultiServo(rightSpool, leftSpool);

        linearSlides.setDirection(Servo.Direction.FORWARD);
        linearSlides.setPosition(0);

    }

    public void setPosition(double position) {
        linearSlides.setPosition(position);
    }
}

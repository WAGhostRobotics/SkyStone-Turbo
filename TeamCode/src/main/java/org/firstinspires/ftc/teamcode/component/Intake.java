package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.library.multimotors.MultiDcMotor;

public class Intake {

    // Intake
    private DcMotor intake1;
    private DcMotor intake2;

    public MultiDcMotor intakeMotors;

    public void init(HardwareMap hardwareMap) {
        // Init intake motors
        intake1 = hardwareMap.get(DcMotor.class, "i1");
        intake2 = hardwareMap.get(DcMotor.class, "i2");

        intake1.setDirection(DcMotorSimple.Direction.FORWARD);
        intake2.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeMotors = new MultiDcMotor(intake1, intake2);

        intakeMotors.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void in() {
        intakeMotors.setPower(1);
    }

    public void out() {
        intakeMotors.setPower(-1);
    }

    public void stop() {
        intakeMotors.setPower(0);
    }
}

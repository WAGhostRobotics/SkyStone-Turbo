package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.MecanumTrigMath;

import java.util.Arrays;

@TeleOp(name = "Mecanum Trig Math Test")
public class MecanumTrigMathTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        // Send diagnostics to user
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        // Send diagnostics to user
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            double[] normalized = MecanumTrigMath.inputsToMotors(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            telemetry.addData("Status", "Running");
            telemetry.addData("Normalized motor values", Arrays.toString(normalized));
            telemetry.update();
        }
    }
}

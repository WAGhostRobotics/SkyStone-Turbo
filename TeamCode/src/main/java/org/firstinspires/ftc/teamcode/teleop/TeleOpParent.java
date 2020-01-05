package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.core.Kevin;
import org.firstinspires.ftc.teamcode.library.DriveStyle;

public class TeleOpParent extends LinearOpMode {

    // Defined global constants
    // Min and max values for the winch
    private static final double WINCH_MIN = 0.0;
    private static final double WINCH_MAX = 0.6;

    // Winch variables/constants
    private double desiredPosition = 0.0;
    private boolean buttonPressed = false;

    private static double WINCH_INCREMENT = 0.05;

    private static double TRIGGER_MINIMUM = 0.1;

    // Set default DriveType
    DriveStyle.DriveType type = DriveStyle.DriveType.MECANUMARCADE;

    @Override
    public void runOpMode() throws InterruptedException {

        // Send diagnostics to user
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        // Initialize the robot hardware
        Kevin.init(hardwareMap);

        // Send diagnostics to user
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // Drivie using set drivemode (g1.ls/rs)
            DriveStyle.driveWithType(Kevin.driveMotors, gamepad1, type); //TODO: Refactor so that gears/half power winches work

            // Winch control (g2.du/dd)
            if (gamepad2.dpad_down && !buttonPressed) {
                desiredPosition = Range.clip(desiredPosition - WINCH_INCREMENT, WINCH_MIN, WINCH_MAX);
                Kevin.linearSlides.setPosition(desiredPosition);
                buttonPressed = true;
            } else if (gamepad2.dpad_up && !buttonPressed) {
                desiredPosition = Range.clip(desiredPosition + WINCH_INCREMENT, WINCH_MIN, WINCH_MAX);
                Kevin.linearSlides.setPosition(desiredPosition);
                buttonPressed = true;
            } else {
                buttonPressed = false; // TODO: does this actually work?
            }

            // Extender control (g2.dl/dr)
            if (gamepad2.dpad_right) {
                Kevin.extender.extend();
            } else if (gamepad2.dpad_left) {
                Kevin.extender.retract();
            }

            // Claw grabbing (g2.x/a)
            if (gamepad2.x) {
                Kevin.extender.closeClaw();
            } else if (gamepad2.a) {
                Kevin.extender.openClaw();
            }

            // Intake (g2.b/y)
            if (gamepad2.b) {
                Kevin.intakeMotors.setPower(1);
            } else if (gamepad2.y) {
                Kevin.intakeMotors.setPower(-1);
            } else {
                Kevin.intakeMotors.setPower(0);
            }

            // Foundation (g1.lb/rb)
            if (gamepad1.left_bumper) {
                Kevin.foundationGrabber.grab();
            } else if (gamepad1.right_bumper) {
                Kevin.foundationGrabber.release();
            }

            // Send diagnostics to user
            telemetry.addData("Status", "Running");
            telemetry.update(); //TODO: Claw servo isn't CR, lots of lag everywhere
        }
    }
}

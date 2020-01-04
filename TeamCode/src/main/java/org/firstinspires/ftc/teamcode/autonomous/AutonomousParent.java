package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.teamcode.core.CVLinearOpMode;
import org.firstinspires.ftc.teamcode.core.Kevin;
import org.firstinspires.ftc.teamcode.library.DriveAuto;

public class AutonomousParent extends CVLinearOpMode {

    // Environment variables for sub-classes (defaults to blue foundation)
    StartLocation startLocation = StartLocation.FOUNDATION;
    TeamColor teamColor = TeamColor.BLUE;

    private DriveAuto drivetrain = new DriveAuto(Kevin.driveMotors);

    @Override
    public void runOpMode() throws InterruptedException {

        // Send diagnostics to user
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        Kevin.init(hardwareMap);

        // Send diagnostics to user
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        switch (startLocation) {
            case FOUNDATION:
                moveRobotTowardsFoundation();

                sleep(300);

                grabFoundation();

                sleep(500);

                moveFoundationBack();

                sleep(300);

                releaseFoundation();

                sleep(300);

            case DEPOT:
                parkOnLine(LinePosition.WALL);
        }
    }

    void moveRobotTowardsFoundation() {
        drivetrain.move(teamColor == TeamColor.BLUE ? DriveAuto.MoveDirection.RIGHT : DriveAuto.MoveDirection.LEFT,
                0.3, 1);
        drivetrain.move(DriveAuto.MoveDirection.BACKWARD, 0.3, 2);
    }

    void grabFoundation() {
        Kevin.foundation.setPosition(Kevin.GRAB);
    }

    void moveFoundationBack() {
        drivetrain.move(DriveAuto.MoveDirection.FORWARD, 0.4, 2.45);
    }

    void releaseFoundation() {
        Kevin.foundation.setPosition(Kevin.RELEASE);
    }

    void parkOnLine(LinePosition position) {
        switch (position) {
            case CENTER_SKYBRIDGE:
                drivetrain.move(DriveAuto.MoveDirection.BACKWARD, 0.3, 2);
            case WALL:
                switch (startLocation) {
                    case FOUNDATION:
                        drivetrain.move(teamColor == TeamColor.BLUE ? DriveAuto.MoveDirection.LEFT : DriveAuto.MoveDirection.RIGHT,
                                0.7, 2.25);
                    case DEPOT:
                        drivetrain.move(teamColor == TeamColor.BLUE ? DriveAuto.MoveDirection.RIGHT : DriveAuto.MoveDirection.LEFT,
                                0.7, 2);
                }
        }
    }

    enum LinePosition {
        WALL,
        CENTER_SKYBRIDGE
    }

    enum StartLocation {
        DEPOT,
        FOUNDATION
    }

    enum TeamColor {
        RED,
        BLUE
    }
}

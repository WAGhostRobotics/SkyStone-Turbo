package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.teamcode.core.CVLinearOpMode;
import org.firstinspires.ftc.teamcode.core.Kevin;
import org.firstinspires.ftc.teamcode.library.DriveAuto;
import org.firstinspires.ftc.teamcode.library.DriveStyle;

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
        vuforiaInit();
        vuforiaActivate();

        // Send diagnostics to user
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        while (!isStarted() && !isStopRequested()) {
            vuforiaScan();
            telemetry.addData("last location?: ", getTranslation());
        }

        vuforiaDeactivate();

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

    void moveTowardsSkystone() {
        // x-distance
        while (lastLocation.getTranslation().get(0) * mmPerInch > 6) {
            DriveStyle.MecanumTank(Kevin.driveMotors, 0.5, 0, 0, 0, 1);
            vuforiaScan();
        }
        DriveStyle.stop(Kevin.driveMotors);

        // y-distance
        while (lastLocation.getTranslation().get(1) * mmPerInch > 6) {
            DriveStyle.MecanumTank(Kevin.driveMotors, 0.5, -1, -1, 0, 0);
            vuforiaScan();
        }
        DriveStyle.stop(Kevin.driveMotors);

    }

    void moveRobotTowardsFoundation() {
        drivetrain.move(teamColor == TeamColor.BLUE ? DriveAuto.MoveDirection.RIGHT : DriveAuto.MoveDirection.LEFT,
                0.3, 1);
        drivetrain.move(DriveAuto.MoveDirection.BACKWARD, 0.3, 2);
    }

    void grabFoundation() {
        Kevin.foundationGrabber.grab();
    }

    void moveFoundationBack() {
        drivetrain.move(DriveAuto.MoveDirection.FORWARD, 0.4, 2.45);
    }

    void releaseFoundation() {
        Kevin.foundationGrabber.release();
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

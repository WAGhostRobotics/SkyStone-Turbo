package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.Kevin;

@TeleOp(name = "Linear Slide Calibration", group = "calibrate")
public class LinearSlideCalibration extends LinearOpMode {

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

        Kevin.linearSlides.setPosition(0);
        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext,
                hardwareMap.appContext.getResources().getIdentifier("ss_alarm", "raw", hardwareMap.appContext.getPackageName()));
    }
}

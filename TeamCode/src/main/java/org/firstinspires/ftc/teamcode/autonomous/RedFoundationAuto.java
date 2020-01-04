package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red Foundation", group = "competition")
public class RedFoundationAuto extends AutonomousParent {

    @Override
    public void runOpMode() throws InterruptedException {
        teamColor = TeamColor.RED;
        startLocation = StartLocation.FOUNDATION;
        super.runOpMode();
    }
}

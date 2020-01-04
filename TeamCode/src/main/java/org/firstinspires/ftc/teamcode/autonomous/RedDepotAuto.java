package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red Depot", group = "competition")
public class RedDepotAuto extends AutonomousParent {

    @Override
    public void runOpMode() throws InterruptedException {
        teamColor = TeamColor.RED;
        startLocation = StartLocation.DEPOT;
        super.runOpMode();
    }
}

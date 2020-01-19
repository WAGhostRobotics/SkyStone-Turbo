package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue Skystone", group = "Auto")
public class BlueSkystoneAuto extends AutonomousParent {
    @Override
    public void runOpMode() throws InterruptedException {
        teamColor = TeamColor.BLUE;
        startLocation = StartLocation.SKYSTONE;
        super.runOpMode();
    }
}

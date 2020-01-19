package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Just Park", group = "Auto")
public class JustParkAuto extends AutonomousParent {
    @Override
    public void runOpMode() throws InterruptedException {
        teamColor = TeamColor.RED;
        startLocation = StartLocation.PARKONLY;
        super.runOpMode();
    }
}

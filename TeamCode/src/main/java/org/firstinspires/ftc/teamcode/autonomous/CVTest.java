package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.core.CVLinearOpMode;

@Autonomous(name = "CV Test")
public class CVTest extends CVLinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        vuforiaInit();
        waitForStart();
        vuforiaActivate();
        while (opModeIsActive()) {
            vuforiaScan2ElectricBoogaloo();
        }
        vuforiaDeactivate();
    }
}

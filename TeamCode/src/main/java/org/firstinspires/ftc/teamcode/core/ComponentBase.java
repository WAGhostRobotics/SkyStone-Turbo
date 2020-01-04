package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class ComponentBase extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        throw new InterruptedException("Please don't run this!");
    }

    public abstract void initComponent();
}

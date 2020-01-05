package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.core.Kevin;

public class FoundationGrabber {
    // Foundation
    private CRServo foundation1;
    private CRServo foundation2;

    private boolean grab = false;

    public void init(HardwareMap hardwareMap) {
        // Foundation
        foundation1 = hardwareMap.get(CRServo.class, "leftFND");
        foundation1.setDirection(DcMotorSimple.Direction.FORWARD);

        foundation2 = hardwareMap.get(CRServo.class, "leftFND");
        foundation2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void grab() {
        if (!grab) {
            foundation1.setPower(1);
            foundation2.setPower(1);

            Kevin.sleep(250);

            foundation1.setPower(0);
            foundation2.setPower(0);

            grab = true;
        }
    }

    public void release() {
        if (grab) {
            foundation1.setPower(-1);
            foundation2.setPower(-1);

            Kevin.sleep(250);

            foundation1.setPower(0);
            foundation2.setPower(0);

            grab = false;
        }
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Chris on 10/10/2017.
 */
@TeleOp (name = "Gripper", group = "testing")
public class GripperTest extends LinearOpMode {

    private DcMotor motor;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotor.class,"gripper_motor");
        waitForStart();
        while (opModeIsActive()){
            if (gamepad1.b){
                motor.setPower(1);
            } else if (gamepad1.y){
                motor.setPower(-1);
            } else {
                motor.setPower(0);
            }
        }
    }
}

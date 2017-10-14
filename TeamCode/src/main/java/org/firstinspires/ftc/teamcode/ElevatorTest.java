package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Chris on 10/10/2017.
 */
@TeleOp(name = "Elevator", group = "testing")
public class ElevatorTest extends LinearOpMode{

    private DcMotor motor;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotor.class,"elevator_motor");
        waitForStart();

        while (opModeIsActive()){
            if (gamepad1.a){
                motor.setPower(1);
            } else if (gamepad1.x){
                motor.setPower(-1);
            } else {
                motor.setPower(0);
            }
        }
    }
}

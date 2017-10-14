package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Chris on 10/10/2017.
 */
@TeleOp(name="RobotTest", group="testing")
public class RobotTest extends LinearOpMode {

    private DcMotor elevatorMotor;

    private DcMotor gripperMotor;

    private DcMotor leftDriveMotor;
    private DcMotor rightDriveMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        elevatorMotor = hardwareMap.get(DcMotor.class,"elevator_motor");
        gripperMotor = hardwareMap.get(DcMotor.class,"gripper_motor");
        leftDriveMotor = hardwareMap.get(DcMotor.class,"left_drive_motor");
        rightDriveMotor = hardwareMap.get(DcMotor.class,"right_drive_motor");

        waitForStart();

        boolean precisionMode = false;

        while (opModeIsActive()){
            if (gamepad1.dpad_down) {
                precisionMode = !precisionMode;
            }

            if (gamepad1.left_bumper){
                elevatorMotor.setPower(1);
            } else if (gamepad1.left_trigger > 0){
                elevatorMotor.setPower(-1);
            } else {
                elevatorMotor.setPower(0);
            }

            if (gamepad1.right_trigger > 0){
                gripperMotor.setPower(1);
            } else if (gamepad1.right_bumper){
                gripperMotor.setPower(-1);
            } else {
                gripperMotor.setPower(0);
            }

            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            double leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            double rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            if (precisionMode) {
                leftPower /= 2;
                rightPower /= 2;
            }

            leftDriveMotor.setPower(leftPower);
            rightDriveMotor.setPower(rightPower);
        }
    }
}

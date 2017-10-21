package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Chris on 10/10/2017.
 */
@TeleOp(name="ExtraRobot", group="testing")
public class ExtraRobot extends LinearOpMode {

    private DcMotor elevatorMotor;

    private Servo leftGripper;
    private Servo rightGripper;

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    static final double INCREMENT = 0.01;
    static final double MAX_POS = 1.0;
    static final double MIN_POS = 0.0;

    double position = (MAX_POS - MIN_POS) / 2;

    @Override
    public void runOpMode() throws InterruptedException {
        elevatorMotor = hardwareMap.get(DcMotor.class,"elevator_motor");
        leftGripper = hardwareMap.get(Servo.class,"left_gripper");
        rightGripper = hardwareMap.get(Servo.class,"right_gripper");
        leftMotor = hardwareMap.get(DcMotor.class,"left_motor");
        rightMotor = hardwareMap.get(DcMotor.class,"right_motor");

        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightGripper.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        boolean precisionMode = false;

        while (opModeIsActive()){
            if (gamepad1.y) {
                precisionMode = !precisionMode;
            }

            if (gamepad2.dpad_up){
                elevatorMotor.setPower(-1);
            } else if (gamepad2.dpad_down){
                elevatorMotor.setPower(1);
            } else {
                elevatorMotor.setPower(0);
            }

            if (gamepad2.right_bumper){
                position += INCREMENT;
                if (position >= MAX_POS){
                    position = MAX_POS;
                }
            } else if (gamepad2.left_bumper){
                position -= INCREMENT;
                if (position <= MIN_POS){
                    position = MIN_POS;
                }
            }

            leftGripper.setPosition(position);
            rightGripper.setPosition(position);


            double leftPower  = gamepad1.left_stick_y ;
            double rightPower = gamepad1.right_stick_y ;

            if (precisionMode) {
                leftPower /= 2;
                rightPower /= 2;
            }

            leftMotor.setPower(leftPower);
            rightMotor.setPower(rightPower);
        }
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;

/**
 * Created by Chris on 10/10/2017.
 */
@TeleOp(name="ExtraRobot", group="testing")
public class ExtraRobot extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor elevatorMotor;

    private Servo leftGripper;
    private Servo rightGripper;

    private DigitalChannel topLimitSwitch;
    private DigitalChannel bottomLimitSwitch;

    static final double INCREMENT = 0.01;
    static final double MAX_POS = 1.0;
    static final double MIN_POS = 0.0;

    double position = (MAX_POS - MIN_POS) / 2;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        ExtraRobotBase extraRobotBase = new ExtraRobotBase(hardwareMap);

        elevatorMotor = hardwareMap.get(DcMotor.class,"elevator_motor");
        leftGripper = hardwareMap.get(Servo.class,"left_gripper");
        rightGripper = hardwareMap.get(Servo.class,"right_gripper");
        topLimitSwitch = hardwareMap.get(DigitalChannel.class,"top_limit_switch");
        bottomLimitSwitch = hardwareMap.get(DigitalChannel.class,"bottom_limit_switch");

        extraRobotBase.setLeftDirection(DcMotorSimple.Direction.FORWARD);
        extraRobotBase.setRightDirection(DcMotorSimple.Direction.REVERSE);
        rightGripper.setDirection(Servo.Direction.REVERSE);

        topLimitSwitch.setMode(DigitalChannel.Mode.INPUT);
        bottomLimitSwitch.setMode(DigitalChannel.Mode.INPUT);

        waitForStart();
        runtime.reset();

        boolean precisionMode = false;

        while (opModeIsActive()){
            if (gamepad1.y) {
                precisionMode = !precisionMode;
            }

            if (gamepad1.dpad_up && !topLimitSwitch.getState()){
                elevatorMotor.setPower(-1);
            } else if (gamepad1.dpad_down && !bottomLimitSwitch.getState()){
                elevatorMotor.setPower(1);
            } else {
                elevatorMotor.setPower(0);
            }

            if (gamepad1.right_bumper){
                position += INCREMENT;
                if (position >= MAX_POS){
                    position = MAX_POS;
                }
            } else if (gamepad1.left_bumper){
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

            extraRobotBase.setLeftPower(leftPower);
            extraRobotBase.setRightPower(rightPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}

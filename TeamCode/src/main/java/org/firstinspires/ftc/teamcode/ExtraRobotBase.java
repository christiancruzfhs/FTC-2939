package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Chris on 10/24/2017.
 */

public class ExtraRobotBase {

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    public ExtraRobotBase(HardwareMap hardwareMap){
        leftMotor = hardwareMap.get(DcMotor.class,"left_motor");
        rightMotor = hardwareMap.get(DcMotor.class,"right_motor");
    }
    public void setLeftPower(double motorPower){
        leftMotor.setPower(motorPower);
    }
    public void setRightPower(double motorPower){
        rightMotor.setPower(motorPower);
    }
    public void setLeftDirection(DcMotorSimple.Direction motorDirection){
        leftMotor.setDirection(motorDirection);
    }
    public void setRightDirection(DcMotorSimple.Direction motorDirection){
        rightMotor.setDirection(motorDirection);
    }
}

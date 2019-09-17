package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;

@TeleOp(name="Main: Differential(1)", group="Iterative Opmode")
public class Differential extends OpMode {
    private DcMotor drive;
    private CRServo servo;
    
    private double speed_constant = 0.5;
    private double servo_constant = 0.25;
    
    void power() {
        double y_left = gamepad1.left_stick_y;
        telemetry.addData("Motor: y_left", y_left);
        
        double speed = speed_constant * y_left;
        telemetry.addData("Motor: speed: ", speed);
        
        drive.setPower(speed);
    }
    
    void steering() {
        double x_right = gamepad1.right_stick_x;
        telemetry.addData("Servo: x_right: ", x_right);
        
        double speed = x_right * servo_constant;
        telemetry.addData("Servo: speed: ", speed);
        
        servo.setPower(speed);
    }
    
    @Override 
    public void init() {
        drive = hardwareMap.get(DcMotor.class, "central_motor");
        drive.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        drive.setDirection(DcMotor.Direction.FORWARD);
        
        servo = hardwareMap.get(CRServo.class, "servo");
        servo.setPower(0);
    }
    
    @Override
    public void start () {
    
    }
    
    @Override
    public void loop () {
        power();
        steering();
    }
}

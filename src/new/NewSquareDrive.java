/*
    # # # # # # # # # # # # # # # # # # 
    # Masters School Robotics         #
    # Written by Matthew Nappo,       #
    #            Zach Battleman       #
    # GitHub: @xoreo, @Zanolon        #
    #                                 #
    # Class NewSquareDrive            #
    # # # # # # # # # # # # # # # # # # 
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import java.util.concurrent.TimeUnit;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;
import java.util.*;
@TeleOp(name="Main: New Square Drive", group="Iterative Opmode")
public class NewSquareDrive extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor w0 = null;
    private DcMotor w1 = null;
    private DcMotor w2 = null;
    private DcMotor w3 = null;
    
    private CRServo lift0 = null;
    private CRServo lift1 = null;
    
    private CRServo shovel0 = null;
    private CRServo shovel1 = null;

    private double max_speed;
    double STOP_VAL;

    private DcMotor init_motor(String id) {
        DcMotor m = null;
        m = hardwareMap.get(DcMotor.class, id);
        m.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        m.setDirection(DcMotor.Direction.REVERSE);
        return m;
    }

    private Servo init_servo(String id) {
        Servo s = null;
        s = hardwareMap.get(Servo.class, id);
        s.setDirection(Servo.Direction.FORWARD);
        return s;
    }
    
     private CRServo init_CRservo(String id, boolean forward) {
        CRServo s = null;
        s = hardwareMap.get(CRServo.class, id);
        if (forward) {
            s.setDirection(CRServo.Direction.FORWARD);
        } else {
            s.setDirection(CRServo.Direction.REVERSE);
        }
        return s;
    }

    @Override
    public void init() {
        // max_speed = 1;
        max_speed = 0.15;
        // max_speed = 0.125;
        
        STOP_VAL = 0;

        w0 = init_motor("w0");
        w1 = init_motor("w1");
        w2 = init_motor("w2");
        w3 = init_motor("w3");
        
        lift0 = init_CRservo("lift0", true);
        lift1 = init_CRservo("lift1", false);
        
        shovel0 = init_CRservo("shovel0", false);
        shovel1 = init_CRservo("shovel1", true);
    }

    double[] move() {
        double x_left_joy = gamepad1.left_stick_x;
        double y_left_joy = gamepad1.left_stick_y;
        
        double phi_joy = Math.atan2(y_left_joy, x_left_joy);
        
        double x_left_joy_sq = Math.pow(x_left_joy, 2);
        double y_left_joy_sq = Math.pow(y_left_joy, 2);
        
        double r_joy = Math.sqrt(x_left_joy_sq + y_left_joy_sq);
        
        double speed = max_speed * r_joy;
        
        double alpha_1 = Math.PI / 4;
        double alpha_2 = 3 * Math.PI / 4;
        double alpha_3 = 5 * Math.PI / 4;
        double alpha_4 = 7 * Math.PI / 4;
        
        double theta_1 = alpha_1 - phi_joy;
        double theta_2 = alpha_2 - phi_joy;
        double theta_3 = alpha_3 - phi_joy;
        double theta_4 = alpha_4 - phi_joy;
        
        double w0_power = -speed * Math.sin(theta_1);
        double w1_power = -speed * Math.sin(theta_2);
        double w2_power = -speed * Math.sin(theta_3);
        double w3_power = -speed * Math.sin(theta_4);
        
        telemetry.addData("w0_power", w0_power);
        telemetry.addData("w1_power", w1_power);
        telemetry.addData("w2_power", w2_power);
        telemetry.addData("w3_power", w3_power);
        
        double[] speeds = {
            w0_power,
            w1_power,
            w2_power,
            w3_power
        };

        return speeds;
    }

    double booltodouble(boolean b) {
        double pwr = 0.85;
        if (b) {
            return pwr;
        }
        return -pwr;
        
        // return b ? (double)1.0 : (double)-1.0;
        
    }
    double lift() {
        // double right_bumper = booltodouble(gamepad1.right_bumper);
        // double left_bumper = booltodouble(gamepad1.left_bumper);
        
        // double leftPower = Range.clip(left_bumper, -0.85, 0.85);
        // double rightPower = Range.clip(right_bumper, -0.85, 0.85);
        
        boolean right_bumper = gamepad1.right_bumper;
        boolean left_bumper  = gamepad1.left_bumper;
        
        
        // if (right_bumper) {
        //     lift0.setPower(booltodouble(right_bumper));
        //     lift1.setPower(booltodouble(right_bumper));
            
        // }
        
        lift0.setPower(booltodouble(right_bumper));
        lift1.setPower(booltodouble(right_bumper));
        
        // if (left_bumper) {
        //     lift0.setPower(-0.5);
        //     lift1.setPower(-0.5);
        // }
        
        telemetry.addData("bbumper right:", booltodouble(right_bumper));
        telemetry.addData("\nbumber left:", left_bumper);
        
    
        
        // if (left_bumper > 0) {
        //     lift0.setPower(leftPower);
        //     lift1.setPower(leftPower);    
        // } else if (right_bumper > 0) {
        //     lift0.setPower(-rightPower);
        //     lift1.setPower(-rightPower);
        // }
    
        
        return 0;
    }
    
    void shovel() {
        double rt = gamepad1.right_trigger;
        double lt = gamepad1.left_trigger;
        
        if (rt > 0) { // If the right trigger is being pressed
            shovel0.setPower(rt);
            shovel1.setPower(rt);
        } else if (lt > 0) {
            shovel0.setPower(-lt);
            shovel1.setPower(-lt);
        } else if (lt == 0 && rt == 0) {
            shovel0.setPower(0);
            shovel1.setPower(0);
        }
        
        telemetry.addData("right trigger", rt);
        telemetry.addData("left trigger", lt);
    }
    
    double turn() {
        double x_right_joy = gamepad1.right_stick_x;
        double speed = Range.clip(x_right_joy, -1.0, 1.0) * max_speed;

        return speed;
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        
        runtime.reset();
        init();
    }

    @Override
    public void loop() {
        double[] move = move();
        double turn = turn();

        double w0_vel = move[0] + turn;
        double w1_vel = move[1] + turn;
        double w2_vel = move[2] + turn;
        double w3_vel = move[3] + turn;

        w0.setPower(w0_vel);
        w1.setPower(w1_vel);
        w2.setPower(w2_vel);
        w3.setPower(w3_vel);
        
        telemetry.addData("w0", w0_vel);
        telemetry.addData("w1", w1_vel);
        telemetry.addData("w2", w2_vel);
        telemetry.addData("w3", w3_vel);
        
        lift();
        // lift2();
        
        telemetry.addData("right x", gamepad1.right_stick_x);
        
        shovel();
    }

    @Override
    public void stop() {
        w0 = null;
        w1 = null;
        w2 = null;
        w3 = null;

        // lift0.setPower(STOP_VAL);
        // lift1.setPower(STOP_VAL);
        
        lift0 = null;
        lift1 = null;
    }
}


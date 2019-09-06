
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;

@TeleOp(name="Main: TankDrive", group="Iterative Opmode")
public class TankDrive extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor w0 = null;
    private DcMotor w1 = null;
    
    private double max_speed;

    private DcMotor init_motor(String id) {
        DcMotor m;
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

    @Override
    public void init() {
        // max_speed = 1;
        max_speed = 1;
        // max_speed = 0.125;
        w0 = init_motor("w0");
        w1 = init_motor("w1");


       
        // init_servo("lift_0");
        // init_servo("lift_1");
    }

    double[] move() {
        double y_left = gamepad1.left_stick_y;
        
        double y_right = gamepad1.right_stick_y;
        
        
        double w0_power = -max_speed * y_right;
        double w1_power = -max_speed * y_left;
        
        telemetry.addData("w0_power", w0_power);
        telemetry.addData("w1_power", w1_power);
        
        double[] speeds = {
            w0_power,
            w1_power,
        };

        return speeds;
    }
  
    /*
        For driving, I need to find out a way to do this. Maybe i wire the motor into the servo port but deal with it 
        as if its a dcmotor class
    */
    
  
    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        telemetry.addData("ree", "ree");
        runtime.reset();
        init();
    }

    @Override
    public void loop() {
        double[] move = move();
        //telemetry.addData("Run Time", runtime.toString());
        telemetry.addData("Run Time", "runtime");
        w0.setPower(move[0]);
        w1.setPower(move[1]);
    }


}



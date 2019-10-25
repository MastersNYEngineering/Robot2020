package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.lang.Math;

// Wheels is an abstraction class for interfacing with multiple wheels on
// a single robot.
public class Wheels<T> {

    private T[] wheels;

    Robot(int wheelCount, T wheelType) {
        T wheels[] = new T[wheelCount];
        for (int i = 0; i < wheelCount; i++) {
            
        }
    }
}
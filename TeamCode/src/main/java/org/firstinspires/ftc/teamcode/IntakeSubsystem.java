package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem {
    public static final String INTAKE_SERVO = "intake-servo";

    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    public static final double SERVO_SPEED = 0.5;

    private CRServo servo;

    public IntakeSubsystem(HardwareMap hm, Telemetry telemetry){
        this.hardwareMap = hm;
        this.telemetry = telemetry;

        servo = hardwareMap.get(CRServo.class, INTAKE_SERVO);
    }

    public void spinForward(){
        servo.setDirection(DcMotorSimple.Direction.FORWARD);
        servo.setPower(SERVO_SPEED);
    }

    public void  spinBackward(){
        servo.setDirection(DcMotorSimple.Direction.REVERSE);
        servo.setPower(SERVO_SPEED);
    }

    public void stop(){
        servo.setPower(0);
    }

}

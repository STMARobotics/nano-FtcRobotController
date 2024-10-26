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

    private Servo servo;

    public IntakeSubsystem(HardwareMap hm, Telemetry telemetry){
        this.hardwareMap = hm;
        this.telemetry = telemetry;

        servo = hardwareMap.get(Servo.class, INTAKE_SERVO);
    }

    public void spinForward(){
        servo.setDirection(Servo.Direction.FORWARD);
        servo.setPosition(.75);
    }

    public void  spinBackward(){
        servo.setPosition(-.75);
    }

    public void stop(){
        servo.setPosition(0);
    }

    public void addTelemetry() {
        telemetry.addData("intake direction", servo.getDirection());
        telemetry.addData("intake power", servo.getPosition());
    }
}

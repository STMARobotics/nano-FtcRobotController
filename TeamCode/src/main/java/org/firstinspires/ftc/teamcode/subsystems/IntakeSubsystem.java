package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem {
    public static final String INTAKE_SERVO = "intake-servo";

    private final Telemetry telemetry;

    public static final double SERVO_SPEED = 0.5;

    private final CRServo servo;

    public IntakeSubsystem(HardwareMap hm, Telemetry telemetry){
        this.telemetry = telemetry;

        servo = hm.get(CRServo.class, INTAKE_SERVO);
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

    public void addTelemetry() {
        telemetry.addData("intake direction", servo.getDirection());
        telemetry.addData("intake power", servo.getPower());
    }
}

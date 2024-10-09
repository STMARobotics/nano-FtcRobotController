package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem {

    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    private CRServo servo;

    public static final String INTAKE = "intake";
    public static final double INTAKE_COLLECT = -1.0;
    public static final double INTAKE_OFF = 0.0;
    public static final double INTAKE_DEPOSIT = .5;

    public IntakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry, CRServo servo) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.servo = servo;

        this.servo = this.hardwareMap.get(CRServo.class, INTAKE);
        this.servo.setPower(INTAKE_OFF);
    }

    public void collect(){
        servo.setPower(INTAKE_COLLECT);
    }

    public void stop(){
        servo.setPower(INTAKE_OFF);
    }

    public void deposit() {
        servo.setPower(INTAKE_DEPOSIT);
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class WristSubsystem {
    public static final String WRIST_SERVO = "wrist-servo";
    public static final double PICKUP_POSITION = .75;
    public static final double DROP_OFF_POSITION = .27;
    public static final double REST_POSITION = .2;


    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    private Servo servo;

    public WristSubsystem(HardwareMap hm, Telemetry telemetry){
        this.hardwareMap = hm;
        this.telemetry = telemetry;

        servo = hardwareMap.get(Servo.class, WRIST_SERVO);
    }

    public void moveToPosition(double position){
        servo.setPosition(position);
    }

    public void addTelemetry() {
        telemetry.addData("wrist position", servo.getPosition());
    }
}

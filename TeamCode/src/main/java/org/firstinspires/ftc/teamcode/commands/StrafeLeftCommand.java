package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.HashMap;
import java.util.Map;

public class StrafeLeftCommand extends DriveCommand{
    private double inchesForward;
    private final double speed;

    public StrafeLeftCommand(DriveSubsystem driveSystem, double inchesForward, double speed, int timeout) {
        this.driveSystem = driveSystem;
        this.inchesForward = inchesForward;
        this.speed = speed;
        this.timeout = timeout;
    }

    @Override
    public void execute() {
        driveSystem.strafeLeft(speed, inchesForward);
    }

    @Override
    public String getName() {
        return "Strafe";
    }

    @Override
    public Map<String, String> getValues() {
        HashMap<String, String> map = new HashMap<>();
        map.put("inches", Double.toString(inchesForward));
        return map;
    }

    @Override
    public void increment() {
        inchesForward = inchesForward + 1;
    }

    @Override
    public void decrement() {
        inchesForward = inchesForward - 1;

    }
}

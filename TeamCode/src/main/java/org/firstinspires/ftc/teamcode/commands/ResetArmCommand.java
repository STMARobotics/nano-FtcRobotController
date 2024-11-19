package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

import java.util.Collections;
import java.util.Map;

public class ResetArmCommand implements ConfigurableCommand {
    private final ArmSubsystem arm;

    public ResetArmCommand(ArmSubsystem arm) {
        this.arm = arm;
    }

    @Override
    public void execute() {
        arm.resetArmEncoder();
    }

    @Override
    public void init() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void logMessage() {
        arm.addTelemetry();
    }

    @Override
    public void onComplete() {

    }

    @Override
    public int getTimeout() {
        return 1;
    }

    @Override
    public String getName() {
        return "Reset Arm";
    }

    @Override
    public Map<String, String> getValues() {
        return Collections.emptyMap();
    }

    @Override
    public void increment() {

    }

    @Override
    public void decrement() {

    }
}

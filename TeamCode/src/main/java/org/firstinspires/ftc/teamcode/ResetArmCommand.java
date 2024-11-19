package org.firstinspires.ftc.teamcode;

public class ResetArmCommand implements Command {
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
}

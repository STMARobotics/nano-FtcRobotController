package org.firstinspires.ftc.teamcode;

public class IntakeStopCommand implements Command{
    private final IntakeSubsystem intakeSystem;
    private final int timeout;

    public IntakeStopCommand(IntakeSubsystem intakeSubsystem, int timeout) {
        this.intakeSystem = intakeSubsystem;
        this.timeout = timeout;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void logMessage() {
        intakeSystem.addTelemetry();
    }

    @Override
    public void onComplete() {
    }

    @Override
    public int getTimeout() {
        return this.timeout;
    }

    @Override
    public void execute() {
        intakeSystem.stop();
    }

    @Override
    public void init(){
    }
}

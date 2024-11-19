package org.firstinspires.ftc.teamcode;

public class SlideToPositionCommand implements Command {
    private final SlideSubsystem slideSubsystem;
    private final int position;
    private final int timeout;

    public SlideToPositionCommand(SlideSubsystem slideSubsystem, int position, int timeout) {
        this.slideSubsystem = slideSubsystem;
        this.position = position;
        this.timeout = timeout;
    }

    @Override
    public void execute() {
        slideSubsystem.setPosition(position);
    }

    @Override
    public void init() {

    }

    @Override
    public boolean isFinished() {
        return ! slideSubsystem.isMoving();
    }

    @Override
    public void logMessage() {
        slideSubsystem.addTelemetry();
    }

    @Override
    public void onComplete() {
        slideSubsystem.holdPosition();
    }

    @Override
    public int getTimeout() {
        return timeout;
    }
}

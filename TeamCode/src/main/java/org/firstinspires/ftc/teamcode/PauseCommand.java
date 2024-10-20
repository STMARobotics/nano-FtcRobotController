package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PauseCommand implements Command {
    private final double seconds;
    private long startTime;


    public PauseCommand(double seconds) {
        this.seconds = seconds;
    }

    @Override
    public void execute() {

    }

    @Override
    public void init() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public boolean isFinished() {
        long currentTimeMillis = System.currentTimeMillis();
        long elapsedTimMillis = currentTimeMillis - startTime;

        double elapsedTimeSeconds = (double) elapsedTimMillis /1000;
        return elapsedTimeSeconds >= this.seconds;
    }

    @Override
    public void logMessage() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public int getTimeout() {
        return (int) this.seconds + 1;
    }
}

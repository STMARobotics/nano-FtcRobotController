package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandRunner {

    private final LinearOpMode opMode;
    private ArrayList<Command> commands;

    public CommandRunner(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public CommandRunner commands(Command... commands) {
        this.commands = new ArrayList<Command>(Arrays.asList(commands));
        return this;
    }

    public static CommandRunner OpMode(LinearOpMode opMode) {
        return new CommandRunner(opMode);
    }

    public void run (){
        System.out.println("**********************  RUNNING  *****************");
        for (Command command : commands) {
            command.init();
            command.execute();
            ElapsedTime runtime = new ElapsedTime();
            while (opMode.opModeIsActive() &&
                    (runtime.seconds() < command.getTimeout()) && ! command.isFinished()){
                command.logMessage();
            }
            command.onComplete();
        }
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void addCommandAtIndex(Command command, int index){
        commands.add(index, command);
    }
}

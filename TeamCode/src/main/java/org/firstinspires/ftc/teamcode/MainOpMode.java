/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


/*
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name="Main OpMode", group="Linear OpMode")
//@Disabled
public class MainOpMode extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    DriveSubsystem driveSubsystem;
    SlideSubsystem slideSubsystem;
    ArmSubsystem arm;
    WristSubsystem wrist;
    IntakeSubsystem intake;

    @Override
    public void runOpMode() {
        double cycleTime = 0;
        double loopTime = 0;
        double oldTime = 0;

        DriveSubsystem driveSubsystem = new DriveSubsystem(hardwareMap, telemetry);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        slideSubsystem = new SlideSubsystem(hardwareMap, telemetry);
        arm = new ArmSubsystem(hardwareMap, telemetry);
        wrist = new WristSubsystem(hardwareMap, telemetry);
        intake = new IntakeSubsystem(hardwareMap,telemetry);
  //       Wait for the game to start (driver presses START)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)

        boolean armIsHeld = true;
        boolean slideIsHeld = true;
        while (opModeIsActive()) {

            float forward = - gamepad1.left_stick_y;
            float strafe = gamepad1.left_stick_x;
            float turn = gamepad1.right_stick_x;

            float reductionFactor = 2;
            if (gamepad1.right_bumper) {
                reductionFactor = 6;
            }

            if (gamepad1.dpad_up) {
                slideIsHeld = false;
                slideSubsystem.changePosition((float) (cycleTime * SlideSubsystem.ROTATIONS_PER_SECOND));
            } else if (gamepad1.dpad_down){
                slideIsHeld = false;
                slideSubsystem.changePosition((float) -cycleTime * SlideSubsystem.ROTATIONS_PER_SECOND);
            } else if (!slideIsHeld){
                slideSubsystem.holdPosition();
                slideIsHeld = true;
            }

            if (gamepad2.right_trigger > 0) {
                armIsHeld = false;
                arm.moveUpTicks(cycleTime * ArmSubsystem.ROTATIONS_PER_SECOND);
            } else if (gamepad2.left_trigger > 0) {
                armIsHeld = false;
                arm.moveDownTicks(cycleTime * ArmSubsystem.ROTATIONS_PER_SECOND);
            }else if (! armIsHeld){
                arm.holdPosition();
                armIsHeld = true;
            }

           if (gamepad2.a){
                wrist.moveToPosition(.75);
            } else if (gamepad2.y){
                wrist.moveToPosition(.2);
            } else if (gamepad2.x){
               wrist.moveToPosition(.4);
           }

            if (gamepad2.dpad_left){
               intake.spinForward();
            }
            else if (gamepad2.dpad_right) {
                intake.spinBackward();
            } else {
                intake.stop();
            }

            driveSubsystem.moveRobotCentric(forward, strafe, turn, reductionFactor);

            loopTime = getRuntime();
            cycleTime = loopTime - oldTime;
            oldTime = loopTime;

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            arm.addTelemetry();
            slideSubsystem.addTelemetry();
            wrist.addTelemetry();
            intake.addTelemetry();
            telemetry.update();
        }
    }
}

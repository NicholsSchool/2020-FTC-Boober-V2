package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.Robot;

@TeleOp(name="Test", group="Iterative Opmode")
public class Test extends OpMode {
    private Robot robot;
    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry, gamepad1, gamepad2);
    }

    @Override
    public void loop() {
        if(gamepad1.a)
            robot.driveTrain.test();
        else
            robot.driveTrain.stop();
    }
}

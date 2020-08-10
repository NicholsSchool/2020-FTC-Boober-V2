package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.Robot;
import org.firstinspires.ftc.teamcode.util.RobotMap;

public class CapstoneDropper extends Subsystem {

    private Servo capstoneServo;
    public CapstoneDropper()
    {
        super("CapstoneDropper");
        capstoneServo = Robot.hw.get(Servo.class, RobotMap.CAPSTONE_SERVO_ID);
    }

    public void drop()
    {
        move(Constants.CAPSTONE_DROPPER_DROP_POSITION);
    }

    public void rest()
    {
        move(Constants.CAPSTONE_DROPPER_REST_POSITION);
    }

    private void move(double position)
    {
        capstoneServo.setPosition(position);
    }

    @Override
    public void run() {
        if(Robot.g1.dpad_down)
            drop();
        else if(Robot.g1.dpad_up)
            rest();
    }

    @Override
    public void stop() {

    }
}

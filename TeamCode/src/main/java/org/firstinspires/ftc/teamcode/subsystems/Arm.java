package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Robot;
import org.firstinspires.ftc.teamcode.util.Constants;

public class Arm extends Subsystem {

    private Servo armServo;
    public Arm()
    {
        super("Arm");
        armServo = Robot.hw.get(Servo.class, "ArmServo");
    }

    public void moveUp()
    {
        move(Constants.ARM_UP_POSITION);
    }

    public void moveDown()
    {
        move(Constants.ARM_DOWN_POSITION);
    }

    private void move(double position)
    {
        armServo.setPosition(position);
    }

    @Override
    public void run() {
        if(Robot.g2.y)
            moveUp();
        else if(Robot.g2.a)
            moveDown();
    }

    @Override
    public void stop() {

    }
}

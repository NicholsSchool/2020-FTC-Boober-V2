package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.Robot;

public class TapeExtender extends Subsystem {

    private CRServo extender;
    public TapeExtender()
    {
        super("TapeExtender");
        extender = Robot.hw.get(CRServo.class, "TapeServo");
    }

    public void extend()
    {
        move(Constants.TAPE_EXTEND_SPEED);
    }

    public void retract()
    {
        move(Constants.TAPE_RETRACT_SPEED);
    }

    public void move(double speed) {
        extender.setPower(speed);
    }

    @Override
    public void run() {
        if(Robot.g1.x)
            retract();
        else if(Robot.g1.b)
            extend();
        else
            stop();
    }

    @Override
    public void stop() {
        move(0);
    }
}

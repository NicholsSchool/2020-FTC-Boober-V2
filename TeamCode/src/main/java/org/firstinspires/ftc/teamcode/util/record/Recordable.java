package org.firstinspires.ftc.teamcode.util.record;

public interface Recordable {
    /**
     * Returns the values the subsystem uses to move. Such as motor power or encoder values
     * @return the values the subsystem uses to move
     */
    double[] getValues();

    /**
     * Sets the given values into the the motors or servos in the necessary way
     * @param vals the values to insert
     */
    void setValues(double[] vals);
}

package CupertinoRobotics.support.Hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class Gyro {

    private final BNO055IMU imu;

    private AngleUnit unit;

    enum Axis { X, Y, Z }

    private Axis axis;

    public Gyro(HardwareMap hardwareMap) {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(new BNO055IMU.Parameters());
        imu.getParameters().angleUnit = BNO055IMU.AngleUnit.DEGREES;
        setUnit(AngleUnit.RADIANS);
        setAxis(Axis.Z);
    }

    public Gyro(HardwareMap hardwareMap, AngleUnit unit) {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(new BNO055IMU.Parameters());
        setUnit(unit);
        setAxis(Axis.Z);
    }

    public Gyro(HardwareMap hardwareMap, Axis axis) {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(new BNO055IMU.Parameters());
        setUnit(AngleUnit.RADIANS);
        setAxis(axis);
    }

    public Gyro(HardwareMap hardwareMap, AngleUnit unit, Axis axis) {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(new BNO055IMU.Parameters());
        setUnit(unit);
        setAxis(axis);
    }

    public double getAngle() {
        if (axis == Axis.X)
            return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, unit).firstAngle;
        if (axis == Axis.Y)
            return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.YXZ, unit).firstAngle;
        return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, unit).firstAngle;
    }

    public AngleUnit getUnit() {
        return unit;
    }

    public void setUnit(AngleUnit unit) {
        this.unit = unit;
    }

    public void setAxis(Axis axis) {
        this.axis = axis;
    }

    public Axis getAxis() {
        return axis;
    }

}

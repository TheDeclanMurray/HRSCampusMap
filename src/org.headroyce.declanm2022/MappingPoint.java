package org.headroyce.declanm2022;

public class MappingPoint extends Point{

    public int Level;

    public MappingPoint(double x, double y, int level) {
        super(x, y);
        Level = level;
    }

    @Override
    public String toString() {
        String rtn = Level+"(" + this.x +", "+this.y+")";
        return rtn;
    }
}

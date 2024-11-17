import java.io.Serializable;

public class MyObject implements Serializable {
    private int length;
    private int height;
    private int breadth;

    public MyObject() {
        this.length = 4;
        this.height = 5;
        this.breadth = 3;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBreadth() {
        return this.breadth;
    }

    public void setBreadth(int breadth) {
        this.breadth = breadth;
    }
}

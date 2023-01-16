package model;

public interface IRoom {
    // 接口只定义方法的签名和返回值
    public String getRoomNumber();

    public double getRoomPrice();

    public RoomType getRoomType();

    public boolean isFree();

}

package com.winchester.retrofit.example.http.responseBody;

public class RespCarrier {

    public final String id;
    public final String name;
    public final String tel;

    public RespCarrier(String _id, String _name, String _tel) {
        this.id = _id;
        this.name = _name;
        this.tel = _tel;
    }

    public String toString() {
        return "RespCarrier{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}

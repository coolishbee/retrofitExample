package com.winchester.retrofit.example.http.responseBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RespCarrierTracks {


    @SerializedName("from")
    @Expose
    public Position from = new Position();

    @SerializedName("to")
    @Expose
    public Position to = new Position();

    @SerializedName("state")
    @Expose
    public State state = new State();

    @SerializedName("carrier")
    @Expose
    public Carrier carrier = new Carrier();


    @SerializedName("progresses")
    @Expose
    public List<Progress> progresses = null;

    public class Progress {
        @SerializedName("time")
        @Expose
        public String time;

        @SerializedName("location")
        @Expose
        public Location location;

        @SerializedName("status")
        @Expose
        public State status;

        @SerializedName("description")
        @Expose
        public String description;
    }

    public class Location {
        @SerializedName("name")
        @Expose
        public String name;
    }

    public class Position {
        @SerializedName("name")
        @Expose
        public String name;

        @SerializedName("time")
        @Expose
        public String time;

        @NotNull
        public String toString() {
            return "Position{" +
                    "name='" + name + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }
    }

    public class State {
        @SerializedName("id")
        @Expose
        public String id;

        @SerializedName("text")
        @Expose
        public String text;

        @NotNull
        public String toString() {
            return "State{" +
                    "id='" + id + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }
    }

    public class Carrier {
        @SerializedName("id")
        @Expose
        public String id;

        @SerializedName("name")
        @Expose
        public String name;

        @SerializedName("tel")
        @Expose
        public String tel;

        @NotNull
        public String toString() {
            return "Carrier{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", tel='" + tel + '\'' +
                    '}';
        }
    }

}

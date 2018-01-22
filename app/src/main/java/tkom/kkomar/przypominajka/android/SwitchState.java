package tkom.kkomar.przypominajka.android;


import android.os.Parcel;
import android.os.Parcelable;


public class SwitchState implements Parcelable {


    public boolean isOn = false;


    public SwitchState(Parcel source) {
        this.isOn = source.readInt()==1?true:false;
    }

    public boolean getIsOn() {
        return isOn;
    }

    @Override
    public int describeContents() {
        return 0;
    } //doesn't really matter in this project


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(isOn==true?1:0);
    }

    public static final Creator<SwitchState> CREATOR = new Creator<SwitchState>() {
        @Override
        public SwitchState[] newArray(int size) {
            return new SwitchState[size];
        }
        @Override
        public SwitchState createFromParcel(Parcel source) {
            return new SwitchState(source);
        }
    };
}
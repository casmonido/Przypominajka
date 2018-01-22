package tkom.kkomar.przypominajka;


import android.os.Parcel;
import android.os.Parcelable;

public class AlarmIntentParams implements Parcelable {

    public static String fileNameStr = "fileName";
    public static String repeatEveryStr = "repeatEvery";
    public static String startMillisStr = "startMillis";
    public String  filename;
    public int  repeatEvery;
    public long startMillis;

    public AlarmIntentParams(String fn, int re, long sm) {
        filename = fn;
        repeatEvery = re;
        startMillis = sm;
    }

    public AlarmIntentParams(Parcel source) {
        filename = source.readString();
        repeatEvery = source.readInt();
        startMillis = source.readLong();
    }


    @Override
    public int describeContents() {
        return 0;
    } //doesn't really matter in this project

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(filename);
        dest.writeInt(repeatEvery);
        dest.writeLong(startMillis);
    }

    public static final Creator<AlarmIntentParams> CREATOR = new Creator<AlarmIntentParams>() {
        @Override
        public AlarmIntentParams[] newArray(int size) {
            return new AlarmIntentParams[size];
        }

        @Override
        public AlarmIntentParams createFromParcel(Parcel source) {
            return new AlarmIntentParams(source);
        }
    };
}

package tkom.kkomar.przypominajka.android;

import android.os.Parcel;
import android.os.Parcelable;


public class NotesBuilder implements Parcelable {

    private String title,
            content;
    public boolean isOn = false;

    public static String fileNameStr = "fileName";
    public static String repeatEveryStr = "repeatEvery";
    public static String startMillisStr = "startMillis";
    public String  filename;
    public int  repeatEvery;
    public long startMillis;


    public void setData(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NotesBuilder(String title, String content) {
        this.title = title;
        this.content = content;
        //this.isOn = isOn;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public NotesBuilder(Parcel source) {
        this.isOn = source.readInt()==1?true:false;
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
        dest.writeInt(isOn==true?1:0);
        dest.writeString(filename);
        dest.writeInt(repeatEvery);
        dest.writeLong(startMillis);
    }

    public static final Parcelable.Creator<NotesBuilder> CREATOR = new Parcelable.Creator<NotesBuilder>() {
        @Override
        public NotesBuilder[] newArray(int size) {
            return new NotesBuilder[size];
        }

        @Override
        public NotesBuilder createFromParcel(Parcel source) {
            return new NotesBuilder(source);
        }
    };
}
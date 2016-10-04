package example.abe.com.android_framework.activity.ipc.aidl;
import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    public String name;
    public String describe;
    public Person(String name, String gameDescribe){
        this.name = name;
        this.describe =gameDescribe;
    }

    protected Person(Parcel in) {
        name =in.readString();
        describe =in.readString();
    }

    public String toString(){
        return "name:" + name + "\tdescribe:" + describe;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(describe);
    }
}

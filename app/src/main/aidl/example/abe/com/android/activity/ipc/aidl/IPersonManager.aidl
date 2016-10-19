package example.abe.com.android.activity.ipc.aidl;
import example.abe.com.android.activity.ipc.aidl.Person;
interface IPersonManager {
	List<Person> getPersonList();
	void addPerson(in Person person);
}

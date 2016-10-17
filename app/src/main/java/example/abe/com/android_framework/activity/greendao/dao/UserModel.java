package example.abe.com.android_framework.activity.greendao.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by abe on 16/10/17.
 */
@Entity
public class UserModel {

    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String name;
    private int age;

    @Generated(hash = 896926980)
    public UserModel(Long id, @NotNull String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 782181818)
    public UserModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

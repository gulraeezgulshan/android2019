package pk.com.aptech.childeventlistener.model;

import androidx.annotation.Nullable;

public class User {

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    String uid;
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {
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

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            return this.uid.equals(user.getUid());

        }
        else {
            return  false;
        }
    }
}

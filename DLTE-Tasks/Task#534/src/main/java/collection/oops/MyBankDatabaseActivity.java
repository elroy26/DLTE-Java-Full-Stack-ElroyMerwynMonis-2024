package collection.oops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MyBankDatabaseActivity<T> {
    List<T> myObjects = new ArrayList<>();

    public abstract String insertNewRecord(T objects);
    public void viewAll(){

        System.out.println(Arrays.toString(new List[]{myObjects}));
    }
    public abstract T read(int index);
    public abstract String delete(int index);
    public abstract void update(int index,T object);
}

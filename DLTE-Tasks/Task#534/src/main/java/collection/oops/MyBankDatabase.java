package collection.oops;

public class MyBankDatabase<T> extends MyBankDatabaseActivity<T> {


    @Override
    public String insertNewRecord(T objects) {
        for (int index=0; index < myObjects.size();index++){
            for (T existingRecord:myObjects){
                if (myObjects.get(index).getClass().equals(objects.getClass())){
                    myObjects.set(index,objects);
                    return objects.toString() + " has been inserted";
                }

            }
        }
        return objects.toString() + " hasn't been inserted";
    }

    @Override
    public T read(int index) {
        if (index>=0&& index<myObjects.size())
            return myObjects.get(index);
        return null;
    }

    @Override
    public String delete(int index) {
        if (index>=0 && index < myObjects.size() && myObjects.get(index)!=null){
            T object=myObjects.get(index);
            myObjects.set(index,null);
            return object+"has deleted";
        }
        return null;
    }

    @Override
    public void update(int index, T object) {
        if (index>=0 && index <myObjects.size()){
            myObjects.set(index,object);
            System.out.println(object+"has updated @ "+ index);
        }else
            System.out.println(object+"hasn't updated @"+index);
    }
}

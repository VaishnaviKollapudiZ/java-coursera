package course4.linkedlist1;

public class MyLinkedListTester {

    public static void main(String[] args){

        MyLinkedList<Integer> ll = new MyLinkedList<>();
        //ll.add(2,10); throws exception
        ll.add(10);
        ll.add(20);
        ll.add(1,30);
        ll.remove(0);
        //ll.set(9,0); throws exception
        ll.set(0,5); //30 replaced with 5

        for(Integer element:ll){
            System.out.println(element);
        }
    }


}

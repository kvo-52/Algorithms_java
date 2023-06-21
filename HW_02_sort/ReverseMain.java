//Необходимо реализовать метод разворота связного списка (двухсвязного или односвязного на выбор)

package Algorithms_HW.HW_02_sort;
import java.util.Iterator;

public class ReverseMain {
    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(111, "Семенов Семен Семенович", "+79201111111"));
        contactList.addToEnd(new Contact(222, "Петров Петр Петрович", "+79100002222"));
        contactList.addToEnd(new Contact(333, "Сидоров Сергей Сергеевич", "+79632587845"));
        contactList.addToEnd(new Contact(444, "Александрова Анна Александровна", "+79285556363"));
        contactList.addToEnd(new Contact(555, "Иванов Иван Иванович", "+79101112233"));

        for (Object contact : contactList) {
            System.out.println(contact);
        }

        contactList.reverse();

        System.out.println("********************************************************************");

        for (Object contact : contactList) {
            System.out.println(contact);
        }
    }

    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    /**
     * Класс списка
     *
     * @param <T>
     */
    public static class SingleLinkList<T> implements Iterable {
        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        /**
         * Класс отдельного элемента
         *
         * @param <T>
         */
        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        // Проверка head на пустоту
        public boolean isEmpty() {
            return head == null;
        }

        // Заполнение списка
        public void addToEnd(T item) {

            // Выделение памяти для списка
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;

            // Если голова и хвост пустая, то присваеваем newItem
            if (isEmpty()) {
                head = newItem;
                tail = newItem;

            // Если не пустая, то передаём элементу адрес и ставим его в хвост
            } else {
                tail.next = newItem;
                tail = newItem;
            }
        }

        // Метод разворота списка
        public void reverse() {
            if (!isEmpty() && head.next != null) { 
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}

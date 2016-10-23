package lessonsJD.lesson4;

public class Person {

    private String name;
    private int age;
    private long salary;
    private Address address;

    public Person(String name, int age, long salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        address = new Address("City", "Street", 10);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person p = (Person) obj;
            if (name != null && name.equals(p.name) && age == p.age && salary == p.salary &&
                    address != null && address.equals(p.address)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 34;

        result = result * 37 + name.hashCode();
        result = result * 37 + age;
        result = result * 37 + new Long(salary).hashCode();
        result = result * 37 + address.hashCode();

        return result;
    }

    private class Address {
        String city;
        String street;
        int house;

        public Address(String city, String street, int house) {
            this.city = city;
            this.street = street;
            this.house = house;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Address) {
                Address a = (Address) obj;
                if (city != null && city.equals(a.city) && street != null && street.equals(a.street)
                        && house == a.house) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result = 23;

            result = result * 37 + city.hashCode();
            result = result * 37 + street.hashCode();
            result = result * 37 + house;

            return result;
        }
    }
}

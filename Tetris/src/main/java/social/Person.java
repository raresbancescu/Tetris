package social;

public class Person
{
    private int id;
    private String name, firstName, username;
    private boolean isLoggedIn;

    public Person(int id, String name, String firstName, String username)
    {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.username = username;
        this.isLoggedIn = false;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public boolean isLoggedIn()
    {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn)
    {
        isLoggedIn = loggedIn;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }
}

package Models;


import java.util.Date;

/**
 * Created by Guillaume on 9/6/16.
 */

public class Computer {


    public Computer(){
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduced=" + introduced +
                ", discontinued=" + discontinued +
                ", company=" + company +
                '}';
    }

    /**
     * Constructeur de Computer
     * @param id -> identifiant de l'ordinateur
     * @param name -> nom de l'ordinateur
     * @param introduced -> date à partir de laquelle l'ordinateur a été mis en production
     * @param discontinued -> date à partir de laquelle la production de l'ordinateur a été stoppée
     * @param company -> Entreprise fabriquant l'ordinateur
     */
    public Computer(long id, String name, Date introduced, Date discontinued, Company company) {
        this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getIntroduced() {
        return introduced;
    }

    public void setIntroduced(Date introduced) {
        this.introduced = introduced;
    }

    public Date getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Date discontinued) {
        this.discontinued = discontinued;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    private long id;
    private String name ;
    private Date introduced;
    private Date discontinued;
    private Company company;









}

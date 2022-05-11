public class diakok {
    private String name;
    private String jegyek;
    private int átment;

    public diakok(String name,String jegyek ,int átment) {
        this.name = name;
        this.jegyek=jegyek;
        this.átment = átment;
    }

    public String getName() {
        return name;
    }

    public int getÁtment() {
        return átment;
    }

    public String getJegyek() {
        return jegyek;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setÁtment(int átment) {
        this.átment = átment;
    }

    public void setJegyek(String jegyek) {
        this.jegyek = jegyek;
    }
}

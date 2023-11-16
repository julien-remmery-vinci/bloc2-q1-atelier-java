package domaine;

public interface Query {
    String getAdresse();

    void setAdresse(String adresse);

    void setMethod(QueryMethod method);

    QueryMethod getMethod();
}

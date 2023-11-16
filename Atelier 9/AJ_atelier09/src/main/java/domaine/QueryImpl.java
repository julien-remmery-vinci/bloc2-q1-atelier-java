package domaine;

class QueryImpl implements Query {
    private String adresse;
    private QueryMethod method;

    @Override
    public String getAdresse() {
        return adresse;
    }

    @Override
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public void setMethod(QueryMethod method) {
        this.method = method;
    }

    @Override
    public QueryMethod getMethod() {
        return method;
    }
}

enum QueryMethod {
    GET, POST
}


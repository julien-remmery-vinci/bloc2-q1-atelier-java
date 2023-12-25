package domaine;

/**
 * @author Remmery Julien
 * @version 1.0
 */
public interface Query {
	/**
	 * Get the url of the query
	 * @return url of the query
	 */
	String getUrl();

	/**
	 * Set the url of the query
	 * @param url url of the query
	 */
	void setUrl(String url);

	/**
	 *Get the method of the query
	 * @return method of the query
	 */
	QueryMethod getMethod();

	/**
	 *	Set the method of the query
	 * @param method method of the query
	 */
	void setMethod(QueryMethod method);

	/**
	 * Useable query methods
	 */
	public enum QueryMethod {
		GET, POST
	}

}
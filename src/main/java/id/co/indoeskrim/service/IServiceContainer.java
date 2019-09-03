package id.co.indoeskrim.service;

/**
 * 
 * @author Cipta.Mahdiar
 *
 */

public interface IServiceContainer<T, P> {
	public P createContext();

	public Boolean validateBeforeProces(P processContext);

	public void process(P processContext);

	public Boolean validateAfterProcess(P processContext);

	public T getResponse();
}

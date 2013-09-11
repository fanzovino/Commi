package ar.com.telefonica.ws.amdocs.engine;

public interface IRecordStream {

	public void open(Object context) throws Exception;
	public void close() throws Exception;
	public void update(Object context) throws Exception;
	
}

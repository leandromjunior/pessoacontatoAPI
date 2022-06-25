package br.com.pessoacontato.pwc.domain.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -1445413222150253743L;
	
	public NegocioException(String msg) {
		super(msg);
	}

}
